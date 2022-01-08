package me.fan87.commonplugin.features.impl;

import com.sun.scenario.effect.Crop;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.greenrobot.eventbus.Subscribe;

public class CropTramplingDisabler extends SBFeature {
    public CropTramplingDisabler() {
        super("Crop trampling disabler", "Disables the crop trampling mechanism", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }
    @Subscribe
    public void onCropDestroy(PlayerInteractEvent event) {

        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType().equals(Material.SOIL)) {
            event.setCancelled(true);
        }

    }
}
