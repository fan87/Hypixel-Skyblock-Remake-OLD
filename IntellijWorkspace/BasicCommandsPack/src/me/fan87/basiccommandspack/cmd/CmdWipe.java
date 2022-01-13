package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.database.DatabaseManager;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdWipe extends SBCommand {
    public CmdWipe() {
        super("wipe", "Wipe traget player's all data in data base.", "skyblock.wipe", "/wipe <player>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                loadedPlayer.getPlayer().setExp(0);
                loadedPlayer.getPlayer().getInventory().clear();
                loadedPlayer.getPlayer().getEnderChest().clear();
                loadedPlayer.getPlayer().kickPlayer(ChatColor.RED + "You have been wiped by an administrator! Please rejoin the server.");
                DatabaseManager data = new DatabaseManager(skyBlock);
                org.jongo.MongoCollection players = data.getCollection("players");
                players.remove(String.format("{\"uuid\": \"%s\"}", loadedPlayer.getPlayer().getUniqueId().toString()));
                sender.sendMessage(ChatColor.GREEN + "Successfully wiped!");
                return true;


            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player!");
        return true;
    }

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
        return out;
    }
}
