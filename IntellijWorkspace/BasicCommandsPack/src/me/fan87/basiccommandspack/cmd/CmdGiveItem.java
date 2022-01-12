package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdGiveItem extends SBCommand {


    public CmdGiveItem() {
        super("giveitem", "Give item to target player", "skyblock.giveitem", "/giveitem <player name> <item name>");
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
                sender.sendMessage(ChatColor.GREEN + "Successfully gave " + item.getRarity().getColor() + item.getDisplayName() + " to" + ChatColor.YELLOW + loadedPlayer.getPlayer().getName());
                return true;
            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid player name");
        return true;
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

            for (SBNamespace sbNamespace : SBItems.getRegisteredItems().keySet()) {

                String e = sbNamespace.toString();
                if (e.startsWith(args[1])) {

                    out.add(e);

                }

            }

        }

        return out;
    }
}
