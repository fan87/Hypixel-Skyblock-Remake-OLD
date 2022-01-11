package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdView extends SBCommand {
    public CmdView() {
        super("view", "View player's profile", "skybloc.view", "/view <player>");
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

        return out;
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equalsIgnoreCase(args[0])){

                if (loadedPlayer.getPlayer().getName().equals(sender.getName())) {

                    GuiYourProfile gui = new GuiYourProfile(loadedPlayer);
                    gui.setTitle("Viewing " + loadedPlayer.getPlayer().getName() + "'s Profile");
                    gui.open(((Player) sender));
                    return true;

                }
                GuiYourProfile gui = new GuiYourProfile(loadedPlayer, false);
                gui.setTitle("Viewing " + loadedPlayer.getPlayer().getName() + "'s Profile");
                gui.open(((Player) sender));
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player");
        return true;
    }
}
