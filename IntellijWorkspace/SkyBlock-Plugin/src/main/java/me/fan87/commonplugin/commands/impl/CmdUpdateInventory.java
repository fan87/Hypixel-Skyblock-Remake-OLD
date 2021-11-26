package me.fan87.commonplugin.commands.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdUpdateInventory implements CommandExecutor {

    final SkyBlock instance;

    public CmdUpdateInventory(SkyBlock instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            SBPlayer sbPlayer = instance.getPlayersManager().getPlayer(player);
            if (sbPlayer == null) {
                System.out.println("Error: Couldn't find the player");
                return false;
            }
            sbPlayer.updateInventory();
            player.sendMessage(ChatColor.GREEN + "Updated your inventory!");
            return true;
        }
        return false;
    }

}
