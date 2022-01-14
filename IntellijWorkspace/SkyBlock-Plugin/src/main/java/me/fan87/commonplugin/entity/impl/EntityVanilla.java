package me.fan87.commonplugin.entity.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.entity.SBEntity;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class EntityVanilla extends SBEntity {

    private final LivingEntity entity1;

    public EntityVanilla(SkyBlock skyBlock, LivingEntity entity) {
        super(skyBlock);
        this.entity1 = entity;
        Bukkit.broadcastMessage("Spawned!");
        spawn(entity.getLocation());
    }


    @Override
    public double getMaxHealth() {
        return entity1.getMaxHealth() * 5d;
    }

    @Override
    public int getLevel() {
        return 1;
    }

    @Override
    public String getName() {
        return entity1.getName();
    }

    @Override
    protected LivingEntity spawnEntity() {
        return entity1;
    }
}
