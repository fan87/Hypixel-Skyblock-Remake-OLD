package me.fan87.basiccommandspack;

import me.fan87.basiccommandspack.cmd.*;
import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicCommandsPack extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("Basic Commands Pack", "basiccmdpack", this);
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdCraft());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdSetPlayer());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdGiveItem());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdEnderChest());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdDmgPlayer());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdMaxAllCollections());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdTradeMenu());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmsUnlockAllRecipe());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdWorldType());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdItemDebug());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdBuild());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdHub());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdSetSpawn());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdForceTp());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdView());
        skyBlock.getCommandsManager().registerCommand("basiccmdpack", new CmdTest());
    }

}
