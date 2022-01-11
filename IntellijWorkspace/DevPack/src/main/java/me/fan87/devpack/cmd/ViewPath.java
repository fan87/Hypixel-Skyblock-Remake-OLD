package me.fan87.devpack.cmd;

import me.fan87.commonplugin.commands.SBCommand;
import me.fan87.devpack.features.PathRenderer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ViewPath extends SBCommand {
    public ViewPath() {
        super("viewpath", "View the path finding debug info", "skyblock.viewpath", "/viewpath");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        UUID uniqueId = ((Player) sender).getUniqueId();
        if (PathRenderer.pathViewers.contains(uniqueId)) {
            PathRenderer.pathViewers.remove(uniqueId);
            sender.sendMessage(ChatColor.RED + "Stopped showing path finding debug info.");
        } else {
            PathRenderer.pathViewers.add(uniqueId);
            sender.sendMessage(ChatColor.GREEN + "Started showing path finding debug info.");
        }

        return true;
    }
}
