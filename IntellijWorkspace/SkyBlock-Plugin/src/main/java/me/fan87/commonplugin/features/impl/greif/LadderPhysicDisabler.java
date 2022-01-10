package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.greenrobot.eventbus.Subscribe;

public class LadderPhysicDisabler extends SBFeature {
    public LadderPhysicDisabler() {
        super("Ladder Physics Disabler", "Disables the ladder physics so you can.. you know", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onPhysics(BlockPhysicsEvent event) {
        if (event.getBlock().getType() == Material.LADDER) {
            event.setCancelled(true);
        }
    }

}
