package me.fan87.basiccommandspack;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.collections.SBPlayerCollections;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdMaxAllCollections extends SBCommand {


    public CmdMaxAllCollections() {
        super("maxallcollections", "Max all collections", "skyblock.admin", "/maxallcollections <player name>", "mac");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length !=1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])){

                for (SBCollection collection : loadedPlayer.getCollections().getCollections()) {

                    collection.setCollected(collection.getRequiredAmount(collection.getMaxLevel()));
                    sender.sendMessage(ChatColor.GREEN + "Successfully set " + ChatColor.YELLOW + loadedPlayer.getPlayer().getName() + ChatColor.GREEN + "'s collection to max");
                    return true;

                }

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid player");
        return true;
    }
}
