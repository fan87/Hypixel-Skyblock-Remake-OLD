package me.fan87.commonplugin.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import org.bukkit.ChatColor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
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
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .create();

    @SneakyThrows
    public void registerFeature(SBFeature feature) {
        for (SBFeature sbFeature : features) {
            if (sbFeature.getName().equals(feature.getName())) {
                throw new IllegalArgumentException("Feature name already taken: " + feature.getName());
            }
        }
        SBFeature feature1 = gson.fromJson(new FileReader(getConfigFile(feature)), feature.getClass());
        if (feature1 == null) {
            feature1 = feature;
            feature1.toggled = true;
        }
        feature1.skyBlock = skyBlock;
        feature1.featuresManager = this;
        features.add(feature1);
        if (feature1.isToggled()) {
            feature1.toggled = false;
            feature1.setToggled(true);
        }
        saveConfigFile();
    }

    @SneakyThrows
    public void saveConfigFile() {
        for (SBFeature feature : features) {
            String s = gson.toJson(feature);
            FileOutputStream outputStream = new FileOutputStream(getConfigFile(feature));
            outputStream.write(s.getBytes(StandardCharsets.UTF_8));
            outputStream.close();
        }
    }

    @SneakyThrows
    public File getConfigFile(SBFeature feature) {
        File features = new File(skyBlock.getDataFolder(), "features");
        features.mkdirs();
        File configFile = new File(features, feature.getName() + ".json");
        if (!configFile.exists()) configFile.createNewFile();
        return configFile;
    }

    public <T extends SBFeature> T getFeature(Class<T> clazz) {
        for (SBFeature feature : getFeatures()) {
            if (feature.getClass() == clazz) return (T) feature;
        }
        return null;
    }

}
