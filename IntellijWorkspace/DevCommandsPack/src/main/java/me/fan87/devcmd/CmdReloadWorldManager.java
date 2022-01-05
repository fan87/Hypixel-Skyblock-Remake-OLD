package me.fan87.devcmd;

import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CmdReloadWorldManager extends SBCommand {
    public CmdReloadWorldManager() {
        super("reload-world-manager", "Reloads the world manager", "skyblock.admin", "/reload-world-manager", "rwm");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(ChatColor.YELLOW + "Reloading world manager...");
        skyBlock.getWorldsManager().reload();
        sender.sendMessage(ChatColor.GREEN + "Finished reloading world manager!");
        return true;
    }
}
