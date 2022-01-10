package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdItemDebug extends SBCommand {
    public CmdItemDebug() {
        super("itemdebug", "Toggle item debug mode", "", "/itemdebug");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) sender));
            player.setDebugging(player.isDebugging());
            if (player.isDebugging()) {
                sender.sendMessage(ChatColor.GREEN + "Item Debug has been enabled!");
            } else {
                sender.sendMessage(ChatColor.RED + "Item Debug has been disabled!");
            }
        }
        return true;
    }
}
