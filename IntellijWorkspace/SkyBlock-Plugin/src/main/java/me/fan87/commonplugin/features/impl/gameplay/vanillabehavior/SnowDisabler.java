package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.Material;
import org.bukkit.event.block.BlockFromToEvent;
import me.fan87.commonplugin.events.Subscribe;

public class SnowDisabler extends SBFeature {
    public SnowDisabler() {
        super("Snow Disabler", "Disable snow on the ground", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onSnow(BlockFromToEvent event) {
        if (event.getToBlock().getType() == Material.SNOW || event.getBlock().getType() == Material.SNOW) {
            event.setCancelled(true);
        }
    }

}
