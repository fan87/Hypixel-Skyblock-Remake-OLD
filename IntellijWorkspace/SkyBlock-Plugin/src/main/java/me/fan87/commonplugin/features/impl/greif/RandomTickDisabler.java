package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.impl.WorldTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.WorldsManager;
import org.greenrobot.eventbus.Subscribe;

public class RandomTickDisabler extends SBFeature {
    public RandomTickDisabler() {
        super("Random Tick Disabler", "Disables the grass growing and prevents the minecraft mechanics ruins your map", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onWorldTick(WorldTickEvent event) {
        if (event.getWorldType() != WorldsManager.WorldType.PRIVATE_ISLAND && event.getWorld() != null) {
            event.getWorld().setGameRuleValue("randomTickSpeed", "0");
        }
    }
}
