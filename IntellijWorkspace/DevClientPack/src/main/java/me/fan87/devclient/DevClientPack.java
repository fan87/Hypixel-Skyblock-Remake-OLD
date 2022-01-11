package me.fan87.devclient;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class DevClientPack extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("Dev Client Pack", "devclientpack", this);
    }
}
