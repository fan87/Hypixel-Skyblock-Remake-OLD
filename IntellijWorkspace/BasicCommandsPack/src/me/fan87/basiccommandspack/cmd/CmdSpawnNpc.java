package me.fan87.basiccommandspack.cmd;

import me.fan87.basiccommandspack.gui.GuiNpcMenu;
import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSpawnNpc extends SBCommand {
    public CmdSpawnNpc() {
        super("spawnnpc", "Spawn a NPC for testing", "skyblock.build", "/spawnnpc");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        new GuiNpcMenu(skyBlock.getPlayersManager().getPlayer(((Player) sender)), 1).open(((Player) sender));
        return true;
    }
}
