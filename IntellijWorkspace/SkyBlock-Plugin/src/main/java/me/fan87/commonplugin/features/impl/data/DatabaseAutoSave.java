package me.fan87.commonplugin.features.impl.data;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.ChatColor;
import me.fan87.commonplugin.events.Subscribe;

public class DatabaseAutoSave extends SBFeature {
    public DatabaseAutoSave() {
        super("Database Saver", "Saves database data", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    private int ticks = 0;

    @Subscribe()
    public void onTick(ServerTickEvent event) {
        ticks++;
        if (ticks % skyBlock.getConfigsManager().config.autoSaveTick == 0) {
            if (!skyBlock.getConfigsManager().config.noDatabaseSavingMessage) {
                skyBlock.sendMessage(ChatColor.YELLOW + "Saving data...");
            }
            skyBlock.getDatabaseManager().saveAll();
            if (!skyBlock.getConfigsManager().config.noDatabaseSavingMessage) {
                skyBlock.sendMessage(ChatColor.GREEN + "Data saved to database!...");
            }
        }
    }
}
