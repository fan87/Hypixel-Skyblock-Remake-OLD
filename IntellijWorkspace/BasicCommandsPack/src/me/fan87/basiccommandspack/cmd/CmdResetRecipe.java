package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.recipes.SBRecipe;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdResetRecipe extends SBCommand {
    public CmdResetRecipe() {
        super("resetrecipe", "Reset the target player's Recipe", "skyblock.recipes", "resetrecipe <player>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                for (SBCustomItem allUnlockedRecipe : loadedPlayer.getAllUnlockedRecipes()) {

                    loadedPlayer.lockRecipe(allUnlockedRecipe);

                }
                sender.sendMessage(ChatColor.GREEN + "Reset!");
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player!");
        return true;
    }
}