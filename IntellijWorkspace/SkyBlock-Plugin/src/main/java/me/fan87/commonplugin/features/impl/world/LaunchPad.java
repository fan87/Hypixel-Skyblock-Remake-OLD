package me.fan87.commonplugin.features.impl.world;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.PlayerPostPortalEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBLocation;
import me.fan87.commonplugin.utils.TransportUtils;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaunchPad extends SBFeature {

    private final List<Pad> pads = new ArrayList<>();

    private final Map<SBPlayer, Integer> quota = new HashMap<>();

    @Getter
    @AllArgsConstructor
    public static class Pad {
        private Location location;
        private WorldsManager.WorldType from;
        private WorldsManager.WorldType to;
    }

    public LaunchPad() {
        super("LaunchPad", "Process launch pads", false);
    }

    @Override
    protected void onEnable() {
        setToggled(false);
        skyBlock.sendMessage(ChatColor.RED + "LaunchPad is bugged for now! Disabled for performance.");
    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onTileEntity(ServerTickEvent event) {

    }

    public void animatedSend(SBPlayer sbPlayer, Pad pad) {
        Player player = sbPlayer.getPlayer();
        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        armorStand.setPassenger(player);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        player.playEffect(player.getLocation(), Effect.EXPLOSION_LARGE, 0);
        player.playSound(player.getLocation(), Sound.EXPLODE, 1f, 1f);

        new BukkitRunnable() {
            @Override
            public void run() {
                armorStand.setPassenger(player);
                SBLocation location = TransportUtils.getLocation(player.getWorld(), pad.getFrom(), pad.getTo(), armorStand.getTicksLived());
                if (location == null) {
                    this.cancel();
                    armorStand.remove();
                    sbPlayer.send(pad.getTo());
                    return;
                }
                ((CraftArmorStand) armorStand).getHandle().setPosition(location.getX(), location.getY(), location.getZ());
            }
        }.runTaskTimer(skyBlock, 0, 0);
    }


    @Subscribe()
    public void onPortal(PlayerPostPortalEvent event) {
        setQuota(event.getPlayer(), 50);

        for (Pad pad : pads) {
            SBWorld from = event.getFrom();
            SBWorld to = event.getTo();
            if (from == null || to == null) return;
            if (pad.to == from.getWorldType() && pad.from == to.getWorldType()) {
                Location location = pad.getLocation();
                location.setWorld(event.getPlayer().getPlayer().getWorld());
                double deltaX = location.getWorld().getSpawnLocation().getX() - location.getX();
                double deltaY = location.getWorld().getSpawnLocation().getY() - location.getY();
                double deltaZ = location.getWorld().getSpawnLocation().getZ() - location.getZ();
                if (Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ) > 10) {
                    event.getPlayer().getPlayer().teleport(location);
                }
            }
        }
    }

    @Subscribe()
    public void onPacket(PacketPlayReceiveEvent event) {
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayInFlying) {
            PacketPlayInFlying c03 = (PacketPlayInFlying) event.getNMSPacket().getRawNMSPacket();
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
            if (c03.g()) {
                setQuota(player, getQuota(player) - 1);
            }
        }
    }

    public void setQuota(SBPlayer player, int quota) {
        this.quota.put(player, quota);
    }

    public int getQuota(SBPlayer player) {
        return this.quota.get(player) == null?0:this.quota.get(player);
    }

}
