package me.fan87.commonplugin.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.*;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.utils.StringUtils;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.lang.reflect.Field;
import java.util.UUID;

public class NPCPlayer extends AbstractNPC<EntityPlayer> {

    @Getter
    @Setter
    private UUID uuid = UUID.randomUUID();
    @Getter
    @Setter
    private String playerName = StringUtils.generateRandomString(StringUtils.NPC_NAME_PROVIDER, 10);
    @Getter
    private String skinTexture;
    @Getter
    private String skinSignature;
    @Getter
    @Setter
    private SkinCustomization skinCustomization = new SkinCustomization(true, true, true, true, true, true, true);

    private EntityPlayer npcEntity;





    public NPCPlayer(SkyBlock skyBlock) {
        super(skyBlock);
    }

    public NPCPlayer setSkin(String texture, String signature) {
        this.skinTexture = texture;
        this.skinSignature = signature;
        return this;
    }



    public NPCPlayer create(World world) {
        GameProfile gameprofile = new GameProfile(getUuid(), getPlayerName());
        if (skinTexture != null && skinSignature != null) {
            gameprofile.getProperties().put("textures", new Property("textures", skinTexture, skinSignature));
        }
        DemoPlayerInteractManager playerinteractmanager = new DemoPlayerInteractManager(((CraftWorld) world).getHandle());
        npcEntity = new EntityPlayer(((CraftServer) skyBlock.getServer()).getServer(), ((CraftWorld) world).getHandle(), gameprofile, playerinteractmanager);
        skyBlock.getNpcManager().addNpc(this);
        onCreate();
        if (displayToAll()) {
            for (Player player : world.getPlayers()) {
                display(player);
            }
        }
        return this;
    }


    @SneakyThrows
    public NPCPlayer display(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        for (Player player : players) {
            viewers.add(player);
            EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
            entityPlayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npcEntity));
            PacketPlayOutNamedEntitySpawn packet = new PacketPlayOutNamedEntitySpawn(npcEntity);
            DataWatcher watcher = new DataWatcher(null);
            watcher.a(10, (byte) 127);
            Field i = PacketPlayOutNamedEntitySpawn.class.getDeclaredField("i");
            i.setAccessible(true);
            i.set(packet, watcher);
            entityPlayer.playerConnection.sendPacket(packet);
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> {
                try {
                    entityPlayer.playerConnection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npcEntity));
                } catch (Exception ignored) {}
            }, 10);
            Scoreboard scoreboard = player.getScoreboard();
            Team team;
            if ((team = scoreboard.getTeam("NPC-" + getPlayerName())) == null) {
                team = scoreboard.registerNewTeam("NPC-" + getPlayerName());
            }
            team.setNameTagVisibility(NameTagVisibility.NEVER);
            team.setPrefix(ChatColor.GRAY + "[NPC] ");
            team.addEntry(getPlayerName());
            displayLore(getNpcEntity(), player);
        }
        updatePosition();
        return this;
    }


    public NPCPlayer updatePosition(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        for (Player viewer : players) {
            byte yaw = (byte) (npcEntity.yaw * 256 / 360);
            byte pitch = (byte) (npcEntity.pitch * 256 / 360);
            updateLorePosition(viewer, npcEntity);
            ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityHeadRotation(npcEntity, yaw));
            ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(npcEntity));
        }
        return this;
    }


    @Override
    public EntityPlayer getNpcEntity() {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        return npcEntity;
    }

    public NPCPlayer hide(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        try {
            for (Player player : players) {
                if (viewers.contains(player)) {
                    viewers.remove(player);
                    hideLore(player);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(npcEntity.getId()));
                    onHide(player);
                }
            }
        } catch (Exception ignored) {}
        return this;
    }




    public Entity asCraftCopy() {
        return CraftEntity.getEntity(((CraftServer) skyBlock.getServer()), npcEntity);
    }



    @Getter
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class SkinCustomization {
        private boolean showCape;
        private boolean jacket;
        private boolean leftSteeve;
        private boolean rightSteeve;
        private boolean leftPantsLeg;
        private boolean rightPantLeg;
        private boolean hat;

        protected byte toByte() {
            byte data = 0x00;
            if (showCape) data = (byte) (data | 0x01);
            if (jacket) data = (byte) (data | 0x02);
            if (leftSteeve) data = (byte) (data | 0x04);
            if (rightSteeve) data = (byte) (data | 0x08);
            if (leftPantsLeg) data = (byte) (data | 0x10);
            if (rightPantLeg) data = (byte) (data | 0x20);
            if (hat) data = (byte) (data | 0x30);
            return data;
        }
    }


}
