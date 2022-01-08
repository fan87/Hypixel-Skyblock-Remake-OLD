package me.fan87.basiccommandspack;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicCommandsPack extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("Basic Commands Pack", "basiccmdpack", this);
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdCraft());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdSetPlayer());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdGiveItem());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdDmgPlayer());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdMaxAllCollections());
    }

}
