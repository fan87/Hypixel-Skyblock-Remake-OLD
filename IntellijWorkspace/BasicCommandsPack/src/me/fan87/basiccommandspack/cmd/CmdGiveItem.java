package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdGiveItem extends SBCommand {


    public CmdGiveItem() {
        super("getitem", "Give item to target player", "skyblock.admin", "/giveitem <player name> <item name>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 2) return false;

        SBCustomItem item = SBItems.getItem(args[1]);
        if (item == null){

            sender.sendMessage(ChatColor.RED + "Invalid Item name");
            return true;

        }
        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])){

                loadedPlayer.getPlayer().getInventory().addItem(new SBItemStack(item).getItemStack());
                sender.sendMessage(ChatColor.GREEN + "Successfully give " + item.getRarity().getColor() + item.getDisplayName() + " to" + ChatColor.YELLOW + loadedPlayer.getPlayer().getName());
                return true;
            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid player neme");
        return true;
    }
}
