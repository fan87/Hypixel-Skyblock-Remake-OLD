package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmsUnlockAllRecipe extends SBCommand {
    public CmsUnlockAllRecipe() {
        super("unlockallrecipe", "Unlock all recipe", "skyblock.recipe", "/unlockallrecipe <player>", "uar");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SBPlayer player = null;

        if (args.length !=1 && args.length !=0) return false;

        if (args.length == 1){

            for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

                if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                    player = loadedPlayer;


                }

            }

        }else {

            player = skyBlock.getPlayersManager().getPlayer(((Player) sender));

        }

        if (player == null){

            sender.sendMessage(ChatColor.RED + "Invalid player");
            return true;

        }

        for (SBCustomItem allUnlockableCraftingRecipe : skyBlock.getRecipesManager().getAllUnlockableCraftingRecipes()) {

            player.unlockRecipe(allUnlockableCraftingRecipe);
            return true;

        }
        sender.sendMessage(ChatColor.GREEN + "Unlocked all recipe for " + ChatColor.YELLOW + player.getPlayer().getDisplayName());
        return true;

    }

    @Override
    protected List<String> onTabComplete(CommandSender sender, String alias, String[] args) {

        List<String> out = new ArrayList<>();
        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            String name = loadedPlayer.getPlayer().getName();
            if (name.startsWith(args[0])) {

                out.add(name);

            }
        }

        return super.onTabComplete(sender, alias, args);
    }
}
