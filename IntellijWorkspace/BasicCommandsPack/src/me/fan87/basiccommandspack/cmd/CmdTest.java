package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(((Player) sender).getWorld().getName());
        if (world == null) sender.sendMessage("null");
        sender.sendMessage(world.getWorldType().getName());
        return true;
    }
}
