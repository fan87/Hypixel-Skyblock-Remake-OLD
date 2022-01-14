package me.fan87.commonplugin.features.impl.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.ChatColor;

public class DatabaseAutoSave extends SBFeature {
    public DatabaseAutoSave() {
        super("Database Saver", "Saves database data", false);
    }

    @Expose
    @SerializedName("waitTime-MS")
    private long waitTimeMs = 10000;

    @Expose
    @SerializedName("sendSavingMessage")
    private boolean sendSavingMessage = false;

    @Override
    protected void onEnable() {
        new Thread(() -> {
            while (true) {
                try {
                    if (sendSavingMessage) {
                        skyBlock.sendMessage(ChatColor.YELLOW + "Saving data...");
                    }
                    skyBlock.getDatabaseManager().saveAll();
                    if (sendSavingMessage) {
                        skyBlock.sendMessage(ChatColor.GREEN + "Data saved to database!...");
                    }
                    Thread.sleep(waitTimeMs);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDisable() {

    }

    private int ticks = 0;

    @Subscribe()
    public void onTick(ServerTickEvent event) {

    }
}
