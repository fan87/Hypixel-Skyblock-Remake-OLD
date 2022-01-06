package me.fan87.commonplugin.events.registerers;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.events.impl.WorldTickEvent;
import me.fan87.commonplugin.world.SBWorld;

public class TickEventRegisterer {

    public TickEventRegisterer(SkyBlock skyBlock) {
        skyBlock.getServer().getScheduler().runTaskTimer(skyBlock, new Runnable() {
            @Override
            public void run() {
                EventManager.EVENT_BUS.post(new ServerTickEvent());
                for (SBWorld world : skyBlock.getWorldsManager().getWorlds()) {
                    EventManager.EVENT_BUS.post(new WorldTickEvent(world.getWorldType(), skyBlock.getServer().getWorld(world.getWorldName()), world));
                }
            }
        }, 0, 0);
    }

}
