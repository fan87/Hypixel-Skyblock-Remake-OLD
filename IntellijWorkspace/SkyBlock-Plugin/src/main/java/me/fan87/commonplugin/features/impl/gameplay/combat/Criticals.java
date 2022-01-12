package me.fan87.commonplugin.features.impl.gameplay.combat;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.PlayerCriticalEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.impl.gameplay.rendering.EntityDamageIndicator;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.fan87.commonplugin.events.Subscribe;

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
                EventManager.post(criticalEvent);
                if (criticalEvent.isCancelled()) return;
                EntityDamageIndicator.setCritical(event, true);
            }
        }
    }
}
