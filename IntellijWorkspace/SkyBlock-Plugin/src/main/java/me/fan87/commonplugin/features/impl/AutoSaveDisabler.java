package me.fan87.commonplugin.features.impl;

import me.fan87.commonplugin.events.impl.WorldTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.WorldsManager;
import org.greenrobot.eventbus.Subscribe;

public class AutoSaveDisabler extends SBFeature {
    public AutoSaveDisabler() {
        super("Auto Save Disabler", "Disables auto save", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onTick(WorldTickEvent event) {
        if (event.getWorldType() != WorldsManager.WorldType.NONE && event.getWorld() != null) {
            event.getWorld().setAutoSave(false);
        }
    }

}
