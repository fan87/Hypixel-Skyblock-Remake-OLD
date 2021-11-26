package me.fan87.commonplugin.events.registerers;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;

public class TickEventRegisterer {

    public TickEventRegisterer(SkyBlock skyBlock) {
        skyBlock.getServer().getScheduler().runTaskTimer(skyBlock, new Runnable() {
            @Override
            public void run() {
                EventManager.EVENT_BUS.post(new ServerTickEvent());
            }
        }, 0, 0);
    }

}
