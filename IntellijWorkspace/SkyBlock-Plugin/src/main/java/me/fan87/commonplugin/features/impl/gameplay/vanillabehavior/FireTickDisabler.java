package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.World;

public class FireTickDisabler extends SBFeature {
    public FireTickDisabler() {
        super("Fire Tick Disabler", "Disables the fire ticking behavior", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {
        for (World world : skyBlock.getServer().getWorlds()) {
            SBWorld world1 = skyBlock.getWorldsManager().getWorld(world.getName());
            if (world1 != null) {
                if (world1.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) {
                    world.setGameRuleValue("doFireTick", "true");
                } else {
                    world.setGameRuleValue("doFireTick", "false");
                }
            }
        }
    }
}
