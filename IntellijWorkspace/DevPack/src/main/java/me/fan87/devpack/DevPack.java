package me.fan87.devpack;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.devpack.cmd.ViewPath;
import me.fan87.devpack.features.PathRenderer;
import org.bukkit.plugin.java.JavaPlugin;

public class DevPack extends JavaPlugin {


    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("Dev Pack", "devpack", this);
        skyBlock.getFeaturesManager().registerFeature(new PathRenderer());
        skyBlock.getCommandsManager().registerCommand("devpack", new ViewPath());
    }
}
