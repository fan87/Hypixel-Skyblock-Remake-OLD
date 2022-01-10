package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdSetPlayer extends SBCommand {

    public CmdSetPlayer(){

        super("setStat", "Set player stats.", "skyblock.stats", "/setStat <player name> <stat name> <base value>");

    }

    @Override
    protected List<String> onTabComplete(CommandSender sender, String alias, String[] args) {

        List<String> out = new ArrayList<>();
        if (args.length == 1) {

            for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

                String name = loadedPlayer.getPlayer().getName();
                if (name.startsWith(args[0])) {

                    out.add(name);

                }

            }

        }
        if (args.length == 2) {

            for (SBStat stat : skyBlock.getPlayersManager().getPlayer(((Player) sender)).getStats().getStats()) {

                String e = stat.getNamespace();
                if (e.startsWith(args[1])){

                    out.add(e);

                }

            }

        }

        return out;
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

        }
        sender.sendMessage(ChatColor.RED + "Invalid player name");
        return true;
    }
}
