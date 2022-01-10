package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdView extends SBCommand {
    public CmdView() {
        super("view", "View player's profile", "skybloc.view", "/view <player>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])){

                new GuiYourProfile(loadedPlayer).open(((Player) sender));
                return true;
                

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player");
        return true;
    }
}
