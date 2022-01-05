package me.fan87.commonplugin.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.utils.world.VoidGenerator;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WorldsManager {

    @Getter
    private final SkyBlock skyBlock;
    @Getter
    private FileConfiguration config;

    @SneakyThrows
    public WorldsManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;

        reload();
    }

    @SneakyThrows
    public File getConfigFile() {
        File dataFolder = skyBlock.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdirs();
        File file = new File(dataFolder, "worlds.yml");
        if (!file.exists()) file.createNewFile();
        return file;
    }

    protected void reloadWorlds() {
        skyBlock.sendMessage(ChatColor.YELLOW + "Loading worlds manager...");
        List<WorldType> missingTypes = Arrays.asList(WorldType.values()).stream().filter(worldType -> !worldType.isHidden()).collect(Collectors.toList());
        for (World world : skyBlock.getServer().getWorlds()) {
            boolean found = false;
            for (String allConfiguredWorld : getAllConfiguredWorlds()) {
                if (world.getName().equals(allConfiguredWorld)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                getConfig().set(world.getName() + ".type", "NONE");
            }
        }
        for (String worldName : getAllConfiguredWorlds()) {
            if (skyBlock.getServer().getWorld(worldName) != null) {
                WorldType worldType = getWorld(worldName).getWorldType();
                skyBlock.sendMessage(ChatColor.GREEN + " - Prepared map: " + worldName + " (World already loaded) (Type: " + worldType.getName() + ")");
                continue;
            }
            new WorldCreator(worldName).environment(World.Environment.NORMAL).generator(new VoidGenerator()).generateStructures(false).createWorld();
            WorldType worldType = getWorld(worldName).getWorldType();
            skyBlock.sendMessage(ChatColor.GREEN + " - Prepared map: " + worldName + "(Type: " + worldType.getName() + ")");
            missingTypes.remove(worldType);
        }
        saveConfig();
        if (missingTypes.size() > 0) {
            skyBlock.sendMessage(ChatColor.RED + "There are some worlds that's missing! Please correct it.");
        }
        for (WorldType missingType : missingTypes) {
            skyBlock.sendMessage(ChatColor.RED + " - " + missingType.getName() + " (Namespace: " + missingType + ")");
        }
        skyBlock.sendMessage(ChatColor.GREEN + "Finished loading worlds manager...");
    }

    @SneakyThrows
    public void saveConfig() {
        config.save(getConfigFile());
    }

    /**
     * Reload config and generate/load all missing worlds
     */
    @SneakyThrows
    public void reload() {
        config = new YamlConfiguration();
        config.load(getConfigFile());
        reloadWorlds();
    }

    private Set<String> getAllConfiguredWorlds() {
        return config.getKeys(false);
    }

    public List<SBWorld> getWorlds() {
        List<SBWorld> worlds = new ArrayList<>();
        for (String worldName : getAllConfiguredWorlds()) {
            worlds.add(new SBWorld(worldName, this));
        }
        return worlds;
    }

    /**
     * Get the world by its name
     * @param worldName name of the world
     * @return Returns a SBWorld object, null if world not found
     */
    @Nullable
    public SBWorld getWorld(String worldName) {
        for (SBWorld world : getWorlds()) {
            if (world.getWorldName().equals(worldName)) {
                return world;
            }
        }
        return null;
    }

    public void setWorldType(World world, WorldType type) {
        config.set(world.getName() + ".type", type.getName());
    }

    @Getter
    @AllArgsConstructor
    public enum WorldType {
        NONE("Unknown", true),
        PRIVATE_ISLAND("Private Island", false),
        SKYBLOCK_HUB("SkyBlock Hub", false),
        GOLD_MINE("Gold Mine", false),
        DEEP_CAVERNS("Deep Caverns", false),
        SPIDERS_DEN("Spider's Den", false),
        END("The End", false),
        BLAZING_FORTRESS("Blazing Fortress", false),
        PARK("Park", false),
        BARN("Barn", false),
        MUSHROOM_DESERT("Mushroom Desert", false);

        private String name;
        private boolean hidden;
    }
}