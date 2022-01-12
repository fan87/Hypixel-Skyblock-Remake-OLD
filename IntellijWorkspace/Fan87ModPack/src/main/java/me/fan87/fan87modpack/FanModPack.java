package me.fan87.fan87modpack;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class FanModPack extends JavaPlugin {

    public static SkyBlock skyblock;

    @Override
    public void onEnable() {
        skyblock = SkyBlock.registerAddon("fan87's Mod Pack", "fan87smodpack", this);
    }
}
