package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import me.fan87.commonplugin.events.Subscribe;

public class HungerDisabler extends SBFeature {
    public HungerDisabler() {
        super("Hunger Disabler", "Disables the hunger mechanic", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onHunger(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
    }
}
