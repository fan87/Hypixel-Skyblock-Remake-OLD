package me.fan87.commonplugin.features.impl.performance;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.event.world.ChunkUnloadEvent;
import me.fan87.commonplugin.events.Subscribe;

public class AntiUnload extends SBFeature {
    public AntiUnload() {
        super("Unloading Disabler", "Disables the chunk unload", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onChunkUnload(ChunkUnloadEvent event) {
//        event.setCancelled(true);
    }
}
