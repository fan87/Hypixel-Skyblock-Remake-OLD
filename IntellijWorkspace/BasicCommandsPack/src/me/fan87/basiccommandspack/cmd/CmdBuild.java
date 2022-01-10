package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdBuild extends SBCommand {
    public CmdBuild() {
        super("build", "Toggle the build mode", "", "/build");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (sender instanceof Player) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) sender));
            player.setBuilding(!player.isBuilding());
            if (player.isBuilding()) {
                sender.sendMessage(ChatColor.GREEN + "You can now build!");
            } else {
                sender.sendMessage(ChatColor.RED + "You can no longer build!");
            }
        }
        return true;
    }
}
