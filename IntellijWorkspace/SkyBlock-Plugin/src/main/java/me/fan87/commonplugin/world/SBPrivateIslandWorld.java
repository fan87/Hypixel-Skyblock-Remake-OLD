package me.fan87.commonplugin.world;

import me.fan87.commonplugin.utils.Vec3d;
import me.fan87.commonplugin.world.privateisland.PrivateIsland;

public class SBPrivateIslandWorld extends SBWorld {

    @Override
    public float getSpawnYaw() {
        return privateIsland.getSpawnYaw();
    }

    @Override
    public void setSpawnYaw(float spawnYaw) {
        privateIsland.setSpawnYaw(spawnYaw);
    }

    private PrivateIsland privateIsland;

    protected SBPrivateIslandWorld(PrivateIsland privateIsland, WorldsManager worldsManager) {
        super(privateIsland.getWorldName(), worldsManager);
        this.privateIsland = privateIsland;
    }

    @Override
    public WorldsManager.WorldType getWorldType() {
        return WorldsManager.WorldType.PRIVATE_ISLAND;
    }

    @Override
    public String getWorldName() {
        return privateIsland.getWorldName();
    }

    @Override
    public Vec3d getSpawn() {
        return new Vec3d(privateIsland.getSpawnX() + 0.5, privateIsland.getSpawnY(), privateIsland.getSpawnZ() + 0.5);
    }

    @Override
    public int getPreScanArea() {
        return 0;
    }

    @Override
    public void setSpawn(Vec3d spawn) {
        privateIsland.setSpawnX((int) spawn.getX());
        privateIsland.setSpawnY((int) spawn.getY());
        privateIsland.setSpawnZ((int) spawn.getZ());
    }

    @Override
    public String getWorldID() {
        return super.getWorldID();
    }
}
