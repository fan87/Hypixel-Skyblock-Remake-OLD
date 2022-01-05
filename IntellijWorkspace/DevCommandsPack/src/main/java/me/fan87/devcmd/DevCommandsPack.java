package me.fan87.devcmd;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class DevCommandsPack extends JavaPlugin {

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        try {
            SkyBlock skyBlock = SkyBlock.registerPlugin("Development Commands Pack", "devcmdpack", this);
            skyBlock.getCommandsManager().registerCommand("devcmdpack", new CmdGiveItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
