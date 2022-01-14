package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

public class ExplosionDisabler extends SBFeature {

    public ExplosionDisabler() {
        super("Explosion Block Break Disabler", "Disables the explosion block break so blocks won't break from them", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onExplode(BlockExplodeEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getBlock().getWorld().getName());
        if (world.getWorldType() != WorldsManager.WorldType.PRIVATE_ISLAND) {
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onExplode(EntityExplodeEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getEntity().getWorld().getName());
        if (world.getWorldType() != WorldsManager.WorldType.PRIVATE_ISLAND) {
            event.setCancelled(true);
        }
    }
}
