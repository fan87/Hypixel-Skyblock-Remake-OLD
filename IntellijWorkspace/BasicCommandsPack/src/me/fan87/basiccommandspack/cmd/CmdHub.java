package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdHub extends SBCommand {

    public CmdHub() {
        super("hub", "Teleports you to hub", "", "/hub", "lobby", "l");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;
        SBPlayer sbPlayer = skyBlock.getPlayersManager().getPlayer(player);
        player.sendMessage(ChatColor.GRAY + "Wrapping...");
        sbPlayer.send(WorldsManager.WorldType.DEEP_CAVERNS);
        return true;
    }
}
