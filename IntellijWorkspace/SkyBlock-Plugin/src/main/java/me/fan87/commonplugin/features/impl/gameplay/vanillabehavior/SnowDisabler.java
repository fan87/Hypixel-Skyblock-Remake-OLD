package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
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
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getBlock().getWorld().getName());
        if (world.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) return;
        if (event.getToBlock().getType() == Material.SNOW || event.getBlock().getType() == Material.SNOW) {
            event.setCancelled(true);
        }
    }

}
