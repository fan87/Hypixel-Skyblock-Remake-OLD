package me.fan87.commonplugin.commands;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.Arrays;

public abstract class SBCommand extends BukkitCommand {
    protected SkyBlock skyBlock;

    public SBCommand(String name, String description, String permission, String usage, String... aliases) {
        super(name);
        this.description = description;
        this.setPermission(permission);
        this.setUsage(usage);
        this.setAliases(Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!this.testPermissionSilent(sender)) {
            sender.sendMessage(ChatColor.RED + "You must be admin or higher to use this command!");
            return true;
        } else {
            if (!onCommand(sender, label, args)) {
                sender.sendMessage(ChatColor.RED + "Missing arguments! Usage: " + getUsage());
            }
            return true;
        }
    }


    /**
     * What will happen if command is executed
     * Don't treat it as built in bukkit command system, make the best of return value, command usage etc.
     * There's no point of returning true but send a wrong usage message instead, like what's the point?
     * Unless you want a custom usage message, you don't need to return true and send a wrong usage message
     * @param sender Who typed the command
     * @param label The original command line
     * @param args All arguments
     * @return Returns true if command executed successfully, false if wrong usage
     */
    protected abstract boolean onCommand(CommandSender sender, String label, String[] args);
}
