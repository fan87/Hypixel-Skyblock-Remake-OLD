package me.fan87.basiccommandspack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.commonplugin.features.impl.api.SignInputAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdTest extends SBCommand {

    public CmdTest() {
        super("a", "Just a test command", "skyblock.debug", "/testa");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        SignInputAPI.showSignEditor(skyBlock.getPlayersManager().getPlayer(((Player) sender)), content -> {
            sender.sendMessage(content[0]);
        }, "This is line 1", "This is line 2", "Line 3", "Yay");
        return true;
    }
}
