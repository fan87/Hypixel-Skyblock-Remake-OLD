package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdForceTp extends SBCommand {
    public CmdForceTp() {
        super("forcetp", "Force tp to a world", "skyblock.forcetp", "/forcetp <world type>");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        if (args.length != 1) return false;
        if (sender instanceof Player) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) sender).getPlayer());
            for (WorldsManager.WorldType value : WorldsManager.WorldType.values()) {
                if (value.toString().equals(args[0])) {
                    player.send(value);
                }
            }
        }
        return true;
    }

    @Override
    protected List<String> onTabComplete(CommandSender sender, String alias, String[] args) {
        List<String> out = new ArrayList<>();
        for (WorldsManager.WorldType value : WorldsManager.WorldType.values()) {
            if (value.toString().startsWith(args[0])) {
                out.add(value.toString());
            }
        }
        return out;
    }
}
