package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdResetSkill extends SBCommand {
    public CmdResetSkill() {
        super("resetskill", "Reset the target player's skill level", "skyblock.skill", "/resetskill <player>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {

        if (args.length != 1) return false;

        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {

            if (loadedPlayer.getPlayer().getName().equals(args[0])) {

                for (SBSkill skill : loadedPlayer.getSkills().getSkills()) {

                    skill.setExp(0, loadedPlayer);

                }
                sender.sendMessage(ChatColor.GREEN + "Reset!");
                return true;

            }

        }
        sender.sendMessage(ChatColor.RED + "Invalid Player!");
        return true;
    }
}
