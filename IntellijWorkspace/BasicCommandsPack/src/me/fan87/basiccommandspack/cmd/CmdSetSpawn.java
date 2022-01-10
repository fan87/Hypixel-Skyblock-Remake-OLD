package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetSpawn extends SBCommand {
    public CmdSetSpawn() {
        super("setspawn", "Set the spawnpoint of a world", "skyblock.admin", "/setspawn");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) sender).getPlayer());
            Location location = player.getPlayer().getLocation();
            player.getPlayer().getWorld().setSpawnLocation(location.getBlockX(), location.getBlockY(), location.getBlockZ());
            sender.sendMessage(ChatColor.GREEN + "Spawn has been set to " + location.getBlockX() + " / " + location.getBlockY() + " / " + location.getBlockZ());
        }
        return true;
    }
}
