package me.fan87.lpskyblock;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.lpskyblock.features.LPChatMessage;
import me.fan87.lpskyblock.features.LPDisplayNameChanger;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class LPSkyBlock extends JavaPlugin {

    @Override
    public void onEnable() {
        SkyBlock skyBlock = SkyBlock.registerAddon("LP SkyBlock", "lpskyblock", this);
        skyBlock.getFeaturesManager().registerFeature(new LPDisplayNameChanger());
        skyBlock.getFeaturesManager().registerFeature(new LPChatMessage());
    }

    public static LuckPerms getLuckPerms() {
        return LuckPermsProvider.get();
    }

}
