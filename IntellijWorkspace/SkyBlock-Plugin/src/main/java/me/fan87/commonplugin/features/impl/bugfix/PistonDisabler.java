package me.fan87.commonplugin.features.impl.bugfix;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.event.block.BlockPistonExtendEvent;


public class PistonDisabler extends SBFeature {
    public PistonDisabler() {
        super("Piston Disabler", "Fixes the piston collection duplication glitch by disabling piston.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onPiston(BlockPistonExtendEvent event) {
        event.setCancelled(true);
    }

}
