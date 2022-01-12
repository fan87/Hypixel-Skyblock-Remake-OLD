package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.World;
import me.fan87.commonplugin.events.Subscribe;

public class WeatherChanger extends SBFeature {
    public WeatherChanger() {
        super("Weather Changer", "Changes the vanilla weather behavior", true);
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
            SBWorld sbWorld = skyBlock.getWorldsManager().getWorld(world.getName());
            if (sbWorld == null) continue;
            world.setStorm(false);
        }
    }


}
