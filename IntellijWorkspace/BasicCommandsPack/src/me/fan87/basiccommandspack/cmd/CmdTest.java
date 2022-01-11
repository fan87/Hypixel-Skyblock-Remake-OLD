package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.npc.impl.NPCTest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        NPCTest npc = new NPCTest(skyBlock);
        npc.create(((Player) sender).getWorld());
        npc.display(((Player) sender).getPlayer());
        npc.updatePosition(((Player) sender).getPlayer());
        return true;
    }
}
