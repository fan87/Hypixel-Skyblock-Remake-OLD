package me.fan87.commonplugin.world;

import lombok.EqualsAndHashCode;
import lombok.Getter;

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

    public char getWorldID() {
        return "ABCDEFGHIKLMNOPQRSTVXYZ".charAt(worldsManager.getWorlds().indexOf(this));
    }

}
