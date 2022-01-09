package me.fan87.commonplugin.commands;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.addon.exceptions.UnknownAddonError;
import me.fan87.commonplugin.utils.ReflectionUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;

import java.util.HashMap;
import java.util.Map;

public class CommandsManager {

    @Getter
    private final Map<SBCommand, String> commands = new HashMap<>();
    @Getter
    private final SkyBlock skyBlock;

    public CommandsManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        skyBlock.sendMessage(ChatColor.YELLOW + "Preparing commands system...");
        for (Class<? extends SBCommand> clazz : SkyBlock.reflections.getSubTypesOf(SBCommand.class)) {
            try {
                SBCommand command = clazz.newInstance();
                bypassedRegisterCommand("skyblock", command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        skyBlock.sendMessage(ChatColor.GREEN + "Finished the initialization of commands system!");
    }


    private void bypassedRegisterCommand(String addonNamespace, SBCommand command) {
        CommandMap serverCommandMap = ReflectionUtils.getServerCommandMap(skyBlock.getServer());
        command.skyBlock = skyBlock;
        if (serverCommandMap.register(addonNamespace, command)) {
            commands.put(command, addonNamespace);
            skyBlock.sendMessage(ChatColor.GREEN + " - Registered " + command.getName());
        } else {
            skyBlock.sendMessage(ChatColor.RED + " - Failed to register " + command.getName());
        }
    }

    /**
     * Register a command with an instance and the addon name
     * @param addonNamespace Addon name / Command namespace, whatever you want to call it, for example: devcmdpack
     * @param command The command instance itself, you basically create a class that extends SBCommand
     */
    public void registerCommand(String addonNamespace, SBCommand command) {
        for (SBAddon addon : skyBlock.getAddons()) {
            if (addon.getNamespace().equals(addonNamespace)) {
                bypassedRegisterCommand(addonNamespace, command);
                return;
            }
        }
        throw new UnknownAddonError(addonNamespace);
    }


}
