package me.fan87.commonplugin.features.impl;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.WorldTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.Difficulty;
import org.bukkit.entity.Monster;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PrivateIslandDifficultyModifier extends SBFeature {
    public PrivateIslandDifficultyModifier() {
        super("Private Island Difficulty Modifier", "Modifies the difficult by time, so you won't get killed in the first moment of joining SkyBlock.", false);
    }

    @Expose
    @SerializedName("difficultyChangeTicks")
    private long difficultyChangeTicks = 120000;

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onWorldTick(WorldTickEvent event) {
        SBWorld sbWorld = event.getSbWorld();
        if (sbWorld.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) {
            int index = (int) (event.getWorld().getFullTime() / difficultyChangeTicks);
            if (index == 0) {
                index = 1;
            }
            sbWorld.getWorld().setDifficulty(Difficulty.values()[Math.min(Difficulty.values().length, index)]);
        }
    }

    @Subscribe
    public void onSpwan(CreatureSpawnEvent event) {
        for (SBWorld world : skyBlock.getWorldsManager().getWorlds()) {
            if (world.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) {
                int index = (int) (world.getWorld().getFullTime() / difficultyChangeTicks);
                if (index == 0) {
                    if (event.getEntity() instanceof Monster && event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.CHUNK_GEN || event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.NATURAL) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
