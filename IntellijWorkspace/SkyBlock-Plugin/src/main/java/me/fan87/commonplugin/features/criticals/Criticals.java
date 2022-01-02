package me.fan87.commonplugin.features.criticals;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.entityindicator.EntityDamageIndicator;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.greenrobot.eventbus.Subscribe;

public class Criticals extends SBFeature {

    public Criticals() {
        super("Critical", "Takes more damage, but the value depends on player stat's chance and multiplier.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -20)
    public void onDamage(EntityDamageByEntityEvent event) {
        EntityDamageIndicator.setCritical(event, true);
    }
}
