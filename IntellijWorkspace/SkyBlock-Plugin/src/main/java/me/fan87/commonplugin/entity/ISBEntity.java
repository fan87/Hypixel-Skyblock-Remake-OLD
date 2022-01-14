package me.fan87.commonplugin.entity;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class ISBEntity {

    public abstract void spawn(Location location);
    public abstract LivingEntity getEntity();
    public abstract double getMaxHealth();
    public abstract double getCurrentHealth();
    public abstract boolean isDead();
    public abstract int getLevel();
    public abstract String getName();
    public abstract void onDestroy();
    protected abstract boolean shouldBeRemoved();

    public enum EntityUpdateResult {
        REMOVE,
        CONTINUE;
    }

    public EntityUpdateResult updateEntity() {
        if (shouldBeRemoved()) {
            getEntity().remove();
            onDestroy();
            return EntityUpdateResult.REMOVE;
        }
        return EntityUpdateResult.CONTINUE;
    }

}
