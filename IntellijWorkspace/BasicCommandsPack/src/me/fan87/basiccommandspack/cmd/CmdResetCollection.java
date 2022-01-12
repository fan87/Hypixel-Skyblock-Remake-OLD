package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdResetCollection extends SBCommand {
    public CmdResetCollection() {
        super("resetcollection", "Reset the target player's collection", "skyblock.collections", "/resetcollection <player>", "resetcol");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                for (SBCollection collection : loadedPlayer.getCollections().getCollections()) {

                    collection.setCollected(0, loadedPlayer);

                }
                sender.sendMessage(ChatColor.GREEN + "Reset!");
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player!");
        return true;
    }
}