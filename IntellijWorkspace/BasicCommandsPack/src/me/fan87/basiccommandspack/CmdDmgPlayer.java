package me.fan87.basiccommandspack;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdDmgPlayer extends SBCommand {
    public CmdDmgPlayer() {
        super("damage", "Damage the target player", "skyblock.admin", "/damage <player name> <amount>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])){

                loadedPlayer.getPlayer().damage(Double.valueOf(args[1])/5d);
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid player");
        return true;
    }
}
