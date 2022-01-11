package me.fan87.devpack;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class DevPack extends JavaPlugin {


    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("Dev Pack", "devpack", this);
    }
}
