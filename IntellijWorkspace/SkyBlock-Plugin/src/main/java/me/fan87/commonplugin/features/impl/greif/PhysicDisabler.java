package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.event.block.BlockPhysicsEvent;
import me.fan87.commonplugin.events.Subscribe;

public class PhysicDisabler extends SBFeature {
    public PhysicDisabler() {
        super("Physics Disabler", "Disables the block physics so you can.. you know", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onPhysics(BlockPhysicsEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getBlock().getWorld().getName());
        if (world != null && world.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) return;
        event.setCancelled(true);
    }

}
