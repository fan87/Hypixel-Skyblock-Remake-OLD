package me.fan87.basiccommandspack;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetPlayer extends SBCommand {

    public CmdSetPlayer(){

        super("setStat", "Set player stats.", "skyblock.admin", "/setStat <player name> <stat name> <base value>");

    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 3) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                for (SBStat stat : loadedPlayer.getStats().getStats()) {

                    if (stat.getNamespace().equalsIgnoreCase(args[1])){

                       stat.setBaseValue(Double.valueOf(args[2]));
                       sender.sendMessage(ChatColor.GREEN + "Successfully set " + ChatColor.YELLOW + loadedPlayer.getPlayer().getName() + ChatColor.GREEN + "'s" + stat.getDisplayName() + ChatColor.GREEN + " to " + stat.getBaseValue());
                       return true;

                    }

                }
                sender.sendMessage(ChatColor.RED + "Invalid Stat name");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "Invalid player name");
            return true;

        }

        return false;
    }
}
