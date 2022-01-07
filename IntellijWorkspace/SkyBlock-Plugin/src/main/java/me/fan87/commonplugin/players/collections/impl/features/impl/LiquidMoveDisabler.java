package me.fan87.commonplugin.players.collections.impl.features.impl;

import me.fan87.commonplugin.players.collections.impl.features.SBFeature;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.greenrobot.eventbus.Subscribe;

public class LiquidMoveDisabler extends SBFeature {
    public LiquidMoveDisabler() {
        super("Liquid Flow Disabler", "Disables liquid flow, so liquids won't cause the server lag AND won't ruin your world. Turn this on.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Subscribe
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Material mat = event.getBlock().getType();
        if (mat == Material.STATIONARY_WATER) {
            event.setCancelled(true);
        }
        if (mat == Material.WATER) {
            event.setCancelled(true);
        }
        if (mat == Material.STATIONARY_LAVA) {
            event.setCancelled(true);
        }
        if (mat == Material.LAVA) {
            event.setCancelled(true);
        }
    }

    @Subscribe
    public void onBlockChange(BlockEvent event) {

    }

    @Subscribe
    public void onSpread(BlockSpreadEvent event) {
    }

    @Subscribe
    public void onBlockFromTo(BlockFromToEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.WATER) {
            event.setCancelled(true);
        }
        if (block.getType() == Material.STATIONARY_WATER) {
            event.setCancelled(true);
        }
        if (block.getType() == Material.LAVA) {
            event.setCancelled(true);
        }
        if (block.getType() == Material.STATIONARY_LAVA) {
            event.setCancelled(true);
        }
    }


    @Override
    protected void onDisable() {
        setToggled(true);
        System.err.println("You can not enable Liquid Flow!");
    }
}
