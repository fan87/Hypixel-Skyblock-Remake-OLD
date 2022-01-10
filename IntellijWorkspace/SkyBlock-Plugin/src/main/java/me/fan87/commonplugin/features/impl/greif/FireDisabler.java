package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.World;
import org.greenrobot.eventbus.Subscribe;

public class FireDisabler extends SBFeature {
    public FireDisabler() {
        super("Fire Disabler", "Disable fire spread and block burn, basically doFireTick = false", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
        setToggled(true);
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        for (World world : skyBlock.getServer().getWorlds()) {
            world.setGameRuleValue("doFireTick", "false");
        }
    }
}
