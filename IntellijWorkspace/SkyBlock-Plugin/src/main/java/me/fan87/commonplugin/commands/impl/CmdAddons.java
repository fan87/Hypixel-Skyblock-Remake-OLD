package me.fan87.commonplugin.commands.impl;

import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.commands.SBCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class CmdAddons extends SBCommand {
    public CmdAddons() {
        super("addons", "List all loaded SkyBlock addons", "skyblock.admin", "/addons [Show commands (true | false)] [Addon namespace]", "ad");
    }

    @Override
    protected boolean onCommand(CommandSender sender, String label, String[] args) {
        for (SBAddon addon : skyBlock.getAddons()) {
            if (args.length < 2 || args[1].equals(addon.getNamespace())) {
                sender.sendMessage(ChatColor.GREEN + "✔ " + addon.getName() + " (Namespace: " + addon.getNamespace() + ")");
                if (args.length < 1 || args[0].equals("true")) {
                    Map<SBCommand, String> commands = skyBlock.getCommandsManager().getCommands();
                    for (SBCommand command : commands.keySet()) {
                        if (commands.get(command).equals(addon.getNamespace())) {
                            sender.sendMessage(ChatColor.GRAY + " " + command.getUsage() + " - " + command.getDescription());
                        }
                    }
                }
            }
        }
        return true;
    }
}
