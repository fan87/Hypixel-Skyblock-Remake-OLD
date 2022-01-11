package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.utils.TransportUtils;
import me.fan87.commonplugin.utils.SBLocation;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        double z = Double.parseDouble(args[2]);
        Player player = (Player) sender;
        ArmorStand armorStand = player.getWorld().spawn(player.getLocation(), ArmorStand.class);
        armorStand.setPassenger(player);
        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setMarker(true);
        new BukkitRunnable() {
            @Override
            public void run() {
                SBLocation location = TransportUtils.getLocation(player.getWorld(), WorldsManager.WorldType.GOLD_MINE, WorldsManager.WorldType.SKYBLOCK_HUB, armorStand.getTicksLived());
                if (location == null) {
                    if (skyBlock.getPlayersManager().getPlayer(player) == null) {
                        this.cancel();
                        armorStand.remove();
                    }
                    return;
                }
                ((CraftArmorStand) armorStand).getHandle().setPosition(location.getX(), location.getY(), location.getZ());
            }
        }.runTaskTimer(skyBlock, 0, 0);
        return true;
    }
}
