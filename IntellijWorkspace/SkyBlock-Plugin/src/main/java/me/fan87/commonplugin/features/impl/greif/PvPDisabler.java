package me.fan87.commonplugin.features.impl.greif;

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
    public void onPlayerDamagePlayer(EntityDamageByEntityEvent event){

        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player){

            event.setCancelled(true);

        }

    }


}
