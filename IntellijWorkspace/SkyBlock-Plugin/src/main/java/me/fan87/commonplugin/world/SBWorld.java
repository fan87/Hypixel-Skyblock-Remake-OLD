package me.fan87.commonplugin.world;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.Vec3d;
import org.bukkit.World;

@EqualsAndHashCode
public class SBWorld {

    @Getter
    private final String worldName;
    private final WorldsManager worldsManager;

    protected SBWorld(String worldName, WorldsManager worldsManager) {
        this.worldName = worldName;
        this.worldsManager = worldsManager;
    }

    public WorldsManager.WorldType getWorldType() {
        try {
            return WorldsManager.WorldType.valueOf(worldsManager.getConfig().getString(worldName + ".type"));
        } catch (Exception ignored) {
            return WorldsManager.WorldType.NONE;
        }
    }

    public void setWorldType(WorldsManager.WorldType worldType) {
        worldsManager.getConfig().set(worldName + ".type", worldType.getName());
    }

    public Vec3d getSpawn() {
        if (!worldsManager.getConfig().contains(worldName + ".spawn")) {
            return null;
        }
        Vec3d vec3d = Vec3d.fromString(worldsManager.getConfig().getString(worldName + ".spawn"));
        return vec3d;
    }

    public float getSpawnYaw() {
        boolean contains = worldsManager.getConfig().contains(worldName + ".spawnYaw");
        if (contains) {
            return ((float) worldsManager.getConfig().getDouble(worldName + ".spawnYaw"));
        } else {
            setSpawnYaw(180);
            return 180;
        }
    }

    public void setSpawnYaw(float spawnYaw) {
        worldsManager.getConfig().set(worldName + ".spawnYaw", spawnYaw);
        worldsManager.saveConfig();
    }

    public int getPreScanArea() {
        return worldsManager.getConfig().contains(worldName + ".scanSize")?worldsManager.getConfig().getInt(worldName + ".scanSize"):20;
    }

    public void setSpawn(Vec3d spawn) {
        worldsManager.getConfig().set(worldName + ".spawn", spawn.toString());
        worldsManager.saveConfig();
    }



    public char getWorldID() {
        return "ABCDEFGHIKLMNOPQRSTVXYZ".charAt(worldsManager.getWorlds().indexOf(this));
    }

    public World getWorld() {
        return worldsManager.getSkyBlock().getServer().getWorld(getWorldName());
    }

    public boolean canPlayerBuild(SBPlayer player) {
        if (getWorldName().equals(player.getPrivateIsland().getWorldName())) {
            return true;
        }
        return false;
    }

}
