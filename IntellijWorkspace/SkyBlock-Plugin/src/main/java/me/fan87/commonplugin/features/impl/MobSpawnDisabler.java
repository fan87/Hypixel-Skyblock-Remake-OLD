package me.fan87.commonplugin.features.impl;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.World;
import org.greenrobot.eventbus.Subscribe;

public class MobSpawnDisabler extends SBFeature {
    public MobSpawnDisabler() {
        super("Mob Spawn Disabler", "Disable vanilla mob spawning behavior", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
        setToggled(true);
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        for (World world : skyBlock.getServer().getWorlds()) {
            world.setGameRuleValue("doMobSpawning", "false");
        }
    }
}
