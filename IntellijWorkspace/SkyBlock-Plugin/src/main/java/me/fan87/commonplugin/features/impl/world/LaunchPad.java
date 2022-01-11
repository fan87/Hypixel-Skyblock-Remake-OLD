package me.fan87.commonplugin.features.impl.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBLocation;
import me.fan87.commonplugin.utils.TransportUtils;
import me.fan87.commonplugin.utils.Vec3d;
import me.fan87.commonplugin.world.WorldsManager;
import net.minecraft.server.v1_8_R3.TileEntity;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class LaunchPad extends SBFeature {

    private final List<Pad> pads = new ArrayList<>();

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

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onTileEntity(ServerTickEvent event) {
        for (World world : skyBlock.getServer().getWorlds()) {
            WorldServer nmsWorld = ((CraftWorld) world).getHandle();
            for (TileEntity tileEntity : nmsWorld.tileEntityList) {
                Block blockAt = world.getBlockAt(tileEntity.getPosition().getX(), tileEntity.getPosition().getY(), tileEntity.getPosition().getZ());
                if (blockAt.getType() != Material.CHEST)
                System.out.println(blockAt.getType());
                if (blockAt instanceof Sign) {
                    Sign sign = (Sign) blockAt;
                    System.out.println(sign.getLines().length);
                    if (sign.getLines().length >= 3) {
                        System.out.println(sign.getLine(0));
                        if (sign.getLine(0).contains("[LAUNCHPAD]")) {
                            WorldsManager.WorldType from = WorldsManager.WorldType.fromString(sign.getLine(1));
                            WorldsManager.WorldType to = WorldsManager.WorldType.fromString(sign.getLine(2));
                            if (from != null && to != null) {
                                if (from == skyBlock.getWorldsManager().getWorld(world.getName()).getWorldType()) {
                                    pads.add(new Pad(blockAt.getLocation(), from ,to));
                                    skyBlock.sendMessage(ChatColor.GREEN + "Launch pad detected! From " + from + " to " + to);
                                }
                                blockAt.setType(Material.AIR);
                            } else {
                                skyBlock.sendMessage(ChatColor.RED + "Invalid launch pad ( " + new Vec3d(blockAt.getLocation()) + " )");
                            }
                        }
                    }
                }
            }
        }
        for (Pad pad : pads) {
            for (Entity nearbyEntity : pad.getLocation().getWorld().getNearbyEntities(pad.getLocation(), 2, 2, 2)) {
                if (nearbyEntity instanceof Player && !nearbyEntity.isInsideVehicle()) {
                    animatedSend(skyBlock.getPlayersManager().getPlayer(((Player) nearbyEntity)), pad);
                }
            }
        }
    }

    public void animatedSend(SBPlayer sbPlayer, Pad pad) {
        Player player = sbPlayer.getPlayer();
        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        armorStand.setPassenger(player);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        new BukkitRunnable() {
            @Override
            public void run() {
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

}
