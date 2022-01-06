package me.fan87.commonplugin.features.impl;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.greenrobot.eventbus.Subscribe;

public class RegenController extends SBFeature {

    public RegenController() {
        super("Regen Controller", "Control player's regen speed by its Health Stat", false);
    }

    @Subscribe
    public void onRegen(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.REGEN) {
                event.setAmount(skyBlock.getPlayersManager().getPlayer(((Player) event.getEntity())).getStats().getHealth().getRegenAmount(skyBlock.getPlayersManager().getPlayer(((Player) event.getEntity()))));
            }
        }

    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
}
