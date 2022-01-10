package me.fan87.commonplugin.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.ChatColor;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;

/**
 * SBFeature works like daemon in linux (or service if you want to name it), it will keep running and listen for events
 * Once it's enabled, it's automatically registered in EventsManager, and you are allowed to listen to any event you want
 */
public abstract class SBFeature {

    @Getter
    private final String name;
    @Getter
    private final String description;
    @Getter
    private final boolean beta;
    protected SkyBlock skyBlock;
    protected FeaturesManager featuresManager;

    @Getter
    private boolean toggled = false;

    public SBFeature(String name, String description, boolean beta) {
        this.name = name;
        this.description = description;
        this.beta = beta;
    }

    protected abstract void onEnable();
    protected abstract void onDisable();

    @SneakyThrows
    public void setToggled(boolean value) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject object = gson.fromJson(new FileReader(featuresManager.getConfigFile()), JsonObject.class);
        if (object == null) object = new JsonObject();
        object.remove(getName());
        object.addProperty(getName(), value);
        new FileOutputStream(featuresManager.getConfigFile()).write(gson.toJson(object).getBytes(StandardCharsets.UTF_8));
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
