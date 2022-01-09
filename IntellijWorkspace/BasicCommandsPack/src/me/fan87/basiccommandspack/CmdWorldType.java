package me.fan87.basiccommandspack;

import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdWorldType extends SBCommand {
    public CmdWorldType() {
        super("worldtype", "Shows the type of this world", "skyblock.admin", "/worldtype");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "You are currently in " + skyBlock.getWorldsManager().getWorld(((Player) sender).getWorld().getName()).getWorldType().getName());
        return true;
    }
}
