package me.fan87.commonplugin.world;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.players.PlayersManager;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.Vec3d;
import me.fan87.commonplugin.utils.world.VoidGenerator;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.world.WorldLoadEvent;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorldsManager {

    @Getter
    private final SkyBlock skyBlock;
    @Getter
    private FileConfiguration config;

    @SneakyThrows
    public WorldsManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.register(this);
    }

    @Subscribe
    public void onWorldLoad(WorldLoadEvent event) {
        Bukkit.getScheduler().runTaskLater(skyBlock, () -> {
            SBWorld world = getWorld(event.getWorld().getName());
            world.init();
        }, 2);

    }

    @SneakyThrows
    public File getConfigFile() {
        File dataFolder = skyBlock.getDataFolder();
        if (!dataFolder.exists()) dataFolder.mkdirs();
        File file = new File(dataFolder, "worlds.yml");
        if (!file.exists()) file.createNewFile();
        return file;
    }

    @SneakyThrows
    protected void reloadWorlds() {
        skyBlock.sendMessage(ChatColor.YELLOW + "Loading worlds manager...");
        List<WorldType> missingTypes = Arrays.asList(WorldType.values()).stream().filter(worldType -> !worldType.isHidden()).collect(Collectors.toList());
        for (World world : skyBlock.getServer().getWorlds()) {
            if (world.getName().startsWith("PI-")) continue;
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
            World world = skyBlock.getServer().getWorld(worldName);
            if (world != null) {
                WorldType worldType = getWorld(worldName).getWorldType();
                if (worldType != WorldType.NONE) {
                    Bukkit.unloadWorld(world, false);
                    skyBlock.sendMessage(ChatColor.GREEN + " - Unloading map: " + worldName + " (World already loaded) (Type: " + worldType.getName() + ")");
                }
            }
            File file = new File(worldName);
            SBWorld type = getWorld(worldName);
            if (file.exists() && type != null && type.getWorldType() != WorldType.NONE) {
                File backupFile = new File("backups");
                if (!backupFile.exists()) backupFile.mkdirs();
                File backup = new File(backupFile, worldName);
                if (!backup.exists()) {
                    backup.mkdirs();
                    skyBlock.sendMessage(ChatColor.RED + "Backup not found! Creating one..");
                    FileUtils.copyDirectory(file, backup);
                } else {
                    skyBlock.sendMessage(ChatColor.GREEN + "Backup found! Using it...");
                    if (file.exists()) {
                        file.delete();
                    }
                    FileUtils.copyDirectory(backup, file);
                }
            }
            World world1 = new WorldCreator(worldName).environment(World.Environment.NORMAL).generator(new VoidGenerator()).generateStructures(false).createWorld();
            WorldType worldType = getWorld(worldName).getWorldType();
            SBWorld sbWorld = new SBWorld(skyBlock, worldName, this);
            Vec3d spawn = getWorld(worldName).getSpawn();
            if (spawn != null) {
                world1.setSpawnLocation((int) spawn.getX(), (int) spawn.getY(), (int) spawn.getZ());
            }
            skyBlock.sendMessage(ChatColor.GREEN + " - Prepared map: " + worldName + "(Type: " + worldType.getName() + ")");
            sbWorld.init();
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
            worlds.add(new SBWorld(skyBlock, worldName, this));
        }
        PlayersManager playersManager = skyBlock.getPlayersManager();
        if (playersManager != null) {
            for (SBPlayer loadedPlayer : playersManager.getLoadedPlayers()) {
                worlds.add(new SBPrivateIslandWorld(skyBlock, loadedPlayer.getPrivateIsland(), this));
            }
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

        public static WorldType fromString(String name) {
            for (WorldType value : WorldType.values()) {
                if (value.toString().equals(name)) return value;
            }
            if (name.equals("BLAZING")) return BLAZING_FORTRESS;
            return null;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum AreaType {
        ;

        private String name;
        private String icon;
        private ChatColor color;
        private Predicate<Location> matcher;
    }
}
