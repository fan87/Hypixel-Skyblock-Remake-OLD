package me.fan87.commonplugin.features;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FeaturesManager {

    @Getter
    private final List<SBFeature> features = new ArrayList<>();

    @Getter
    private final SkyBlock skyBlock;

    public FeaturesManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        skyBlock.sendMessage(ChatColor.YELLOW + "Started initializing features");
        for (Class<? extends SBFeature> clazz : SkyBlock.reflections.getSubTypesOf(SBFeature.class)) {
            try {
                SBFeature feature = clazz.newInstance();
                registerFeature(feature);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        skyBlock.sendMessage(ChatColor.GREEN + "Finished initializing features");
    }

    @SneakyThrows
    public void registerFeature(SBFeature feature) {
        for (SBFeature sbFeature : features) {
            if (sbFeature.getName().equals(feature.getName())) {
                throw new IllegalArgumentException("Feature name already taken: " + feature.getName());
            }
        }
        features.add(feature);
        feature.skyBlock = this.skyBlock;
        feature.featuresManager = this;
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(new FileReader(getConfigFile()), JsonObject.class);
        if (object == null) object = new JsonObject();
        if (object.has(feature.getName())) {
            if (object.get(feature.getName()).getAsBoolean()) {
                feature.setToggled(true);
            } else {
                feature.setToggled(false);
            }
        } else {
            feature.setToggled(true);
        }
    }

    @SneakyThrows
    public File getConfigFile() {
        skyBlock.getDataFolder().mkdirs();
        File configFile = new File(skyBlock.getDataFolder(), "features.json");
        if (!configFile.exists()) configFile.createNewFile();
        return configFile;
    }



}
