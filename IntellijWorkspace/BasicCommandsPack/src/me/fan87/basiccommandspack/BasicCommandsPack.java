package me.fan87.basiccommandspack;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.plugin.java.JavaPlugin;

public class BasicCommandsPack extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerPlugin("Basic Commands Pack", "basiccmdpack", this);
    }

}
