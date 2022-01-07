package me.fan87.commonplugin.players.collections.impl.features.impl;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.PlayerCriticalEvent;
import me.fan87.commonplugin.players.collections.impl.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.greenrobot.eventbus.Subscribe;

public class Criticals extends SBFeature {

    public Criticals() {
        super("Critical", "Takes more damage, but the value depends on player stat's chance and multiplier. Note that it doesn't affect the damage calculation", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -20)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(damager);
            if (player.getStats().getCritChance().nextValue(player)) {
                PlayerCriticalEvent criticalEvent = new PlayerCriticalEvent(event);
                EventManager.EVENT_BUS.post(criticalEvent);
                if (criticalEvent.isCancelled()) return;
                EntityDamageIndicator.setCritical(event, true);
            }
        }
    }
}
