package me.fan87.commonplugin.events.registerers;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.events.impl.WorldTickEvent;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.Bukkit;

public class TickEventRegisterer {

    public TickEventRegisterer(SkyBlock skyBlock) {
        skyBlock.getServer().getScheduler().runTaskTimer(skyBlock, new Runnable() {
            @Override
            public void run() {
                EventManager.post(new ServerTickEvent());
                for (SBWorld world : skyBlock.getWorldsManager().getWorlds()) {
                    if (Bukkit.getWorld(world.getWorldName()) != null)
                        EventManager.post(new WorldTickEvent(world.getWorldType(), skyBlock.getServer().getWorld(world.getWorldName()), world));
                }
            }
        }, 0, 0);
    }

}
