package me.fan87.commonplugin.npc;

import me.fan87.commonplugin.SkyBlock;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NPCVillager extends AbstractNPC<EntityVillager> {

    private EntityVillager npcEntity;

    public NPCVillager(SkyBlock skyBlock) {
        super(skyBlock);
    }


    @Override
    public EntityVillager getNpcEntity() {
        return npcEntity;
    }

    @Override
    public NPCVillager create(World world) {
        npcEntity = new EntityVillager(((CraftWorld) world).getHandle());
        skyBlock.getNpcManager().addNpc(this);
        return this;
    }

    @Override
    public NPCVillager display(Player... players) {
        if (npcEntity == null) throw new IllegalStateException("NPC is not created! Please use NPC.create() first!");
        for (Player player : players) {
            viewers.add(player);
            EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
            PlayerConnection networkManager = entityPlayer.playerConnection;
            networkManager.sendPacket(new PacketPlayOutSpawnEntityLiving(npcEntity));
            displayLore(npcEntity, player);

        }
        updatePosition();
        return this;
    }

    @Override
    public NPCVillager updatePosition(Player... players) {
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
    public NPCVillager hide(Player... players) {
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



}
