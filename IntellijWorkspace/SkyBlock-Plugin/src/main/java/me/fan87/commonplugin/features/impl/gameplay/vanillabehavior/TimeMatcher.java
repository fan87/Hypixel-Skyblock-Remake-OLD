package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.Bukkit;
import org.bukkit.World;
import me.fan87.commonplugin.events.Subscribe;

public class TimeMatcher extends SBFeature {
    public TimeMatcher() {
        super("Time Matcher", "Make the game time matches the time on scoreboard", false);
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
            long time = ((System.currentTimeMillis() - skyBlock.getDatabaseManager().getServerData().dayZero)/50 - 3000) % 24000;
            world.setFullTime((world.getFullTime() - world.getFullTime()%24000) + time);
        }
    }
}
