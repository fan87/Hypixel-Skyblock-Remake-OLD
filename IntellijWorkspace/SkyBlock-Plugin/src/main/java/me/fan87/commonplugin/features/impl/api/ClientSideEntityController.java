package me.fan87.commonplugin.features.impl.api;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.SneakyThrows;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClientSideEntityController extends SBFeature {
    public ClientSideEntityController() {
        super("Client Side Entity Controller", "Removes the client side entity", false);
    }


    private final List<Integer> hiddenEntities = new ArrayList<>();

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public void reAddEntity(int entityId) {
        hiddenEntities.remove(entityId);
        if (isToggled()) {
            for (World world : skyBlock.getServer().getWorlds()) {
                for (LivingEntity livingEntity : world.getLivingEntities()) {
                    if (livingEntity.getEntityId() == entityId) {
                        PacketPlayOutSpawnEntityLiving packet = new PacketPlayOutSpawnEntityLiving(((CraftLivingEntity) livingEntity).getHandle());
                        if (skyBlock.getPlayersManager() != null) {
                            List<SBPlayer> loadedPlayers = skyBlock.getPlayersManager().getLoadedPlayers();
                            for (SBPlayer loadedPlayer : loadedPlayers) {
                                loadedPlayer.getCraftPlayer().getHandle().playerConnection.sendPacket(packet);
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeEntity(int entityId) {
        if (isToggled()) {
            PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(entityId);
            if (skyBlock.getPlayersManager() != null) {
                List<SBPlayer> loadedPlayers = skyBlock.getPlayersManager().getLoadedPlayers();
                for (SBPlayer loadedPlayer : loadedPlayers) {
                    loadedPlayer.getCraftPlayer().getHandle().playerConnection.sendPacket(packet);
                }
            }
        }
        hiddenEntities.add(entityId);
    }

    @Subscribe
    @SneakyThrows
    public void onPacketReceive(PacketPlayReceiveEvent event) {
        Object rawNMSPacket = event.getNMSPacket().getRawNMSPacket();
        if (rawNMSPacket instanceof PacketPlayInUseEntity) {
            PacketPlayInUseEntity packet = (PacketPlayInUseEntity) rawNMSPacket;
            Field a = PacketPlayInUseEntity.class.getDeclaredField("a");
            a.setAccessible(true);
            int entityId = ((int) a.get(packet));
            if (hiddenEntities.contains(entityId)) { // Avoid illegal interact
                event.setCancelled(true);
            }
        }
    }

    @Subscribe
    @SneakyThrows
    public void onPacketSent(PacketPlaySendEvent event) {
        Object rawNMSPacket = event.getNMSPacket().getRawNMSPacket();
        if (rawNMSPacket instanceof PacketPlayOutSpawnEntityLiving) {
            PacketPlayOutSpawnEntityLiving packet = ((PacketPlayOutSpawnEntityLiving) rawNMSPacket);
            Field a = PacketPlayOutSpawnEntityLiving.class.getDeclaredField("a");
            a.setAccessible(true);
            int entityId = ((int) a.get(packet));
            if (hiddenEntities.contains(entityId)) {
                event.setCancelled(true);
            }
        }
    }

}
