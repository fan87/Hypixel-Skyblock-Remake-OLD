package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.impl.DamageIndicatorEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.greenrobot.eventbus.Subscribe;

public class PvPDisabler extends SBFeature {
    public PvPDisabler() {
        super("PvP Disabler", "Disable player damage player", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onPlayerDamagePlayer(DamageIndicatorEvent event){
        if (event.getEvent() instanceof EntityDamageByEntityEvent){

            if (event.getEvent().getEntity() instanceof Player && ((EntityDamageByEntityEvent) event.getEvent()).getDamager() instanceof Player){

                event.setCancelled(true);

            }

        }

    }


}
