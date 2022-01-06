package me.fan87.commonplugin.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ConfigsManager {

    private final SkyBlock skyBlock;
    private final Gson gson;

    public SBConfig config;

    @SneakyThrows
    public ConfigsManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        reloadConfig();
    }

    @SneakyThrows
    public void reloadConfig() {
        config = gson.fromJson(new FileReader(getConfigFile()), SBConfig.class);
        if (config == null) {
            config = new SBConfig();
            saveConfig();
        }
    }

    @SneakyThrows
    public void saveConfig() {
        FileOutputStream outputStream = new FileOutputStream(getConfigFile());
        outputStream.write(gson.toJson(config, SBConfig.class).getBytes());
        outputStream.close();
    }

    @SneakyThrows
    public File getConfigFile() {
        File dataFolder = skyBlock.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdirs();
        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        return configFile;
    }

}
