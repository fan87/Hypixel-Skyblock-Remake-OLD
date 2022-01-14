package me.fan87.commonplugin.features.impl.bugfix;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBPrivateIslandWorld;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.privateisland.PrivateIsland;
import org.bukkit.event.block.BlockGrowEvent;

public class BlockLocationFixer extends SBFeature {


    public BlockLocationFixer() {
        super("Placed Block Location Fixer", "Fixes the placed block checker", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onGrow(BlockGrowEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getBlock().getWorld().getName());
        if (world instanceof SBPrivateIslandWorld) {
            SBPrivateIslandWorld privateIsland = (SBPrivateIslandWorld) world;
            PrivateIsland island = privateIsland.getPrivateIsland();
//            if (event.getNewState() instanceof Stagab)
        }
    }

}
