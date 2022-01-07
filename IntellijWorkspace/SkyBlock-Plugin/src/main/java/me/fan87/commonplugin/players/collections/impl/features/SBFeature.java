package me.fan87.commonplugin.players.collections.impl.features;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.ChatColor;

public abstract class SBFeature {

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final boolean beta;
    protected SkyBlock skyBlock;

    @Getter
    private boolean toggled = false;

    public SBFeature(String name, String description, boolean beta) {
        this.name = name;
        this.description = description;
        this.beta = beta;
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    public void setToggled(boolean value) {
        if (value) {
            if (!toggled) {
                skyBlock.getServer().getConsoleSender().sendMessage(" - " + ChatColor.GREEN + getName() + " has been enabled!");
                EventManager.EVENT_BUS.register(this);
                this.toggled = true;
                onEnable();
            }
        } else {
            if (toggled) {
                skyBlock.getServer().getConsoleSender().sendMessage(" - " + ChatColor.RED + getName() + " has been disabled!");
                EventManager.EVENT_BUS.unregister(this);
                this.toggled = false;
                onDisable();
            }
        }
    }

}
