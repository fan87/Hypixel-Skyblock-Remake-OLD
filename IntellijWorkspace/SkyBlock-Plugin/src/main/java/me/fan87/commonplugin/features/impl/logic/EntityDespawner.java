package me.fan87.commonplugin.features.impl.logic;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerShutdownEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityDespawner extends SBFeature {
    public EntityDespawner() {
        super("Entity Despawn", "Despawn mobs automatically when their \"ticksLeft\" ran out. DO NOT TURN THIS OFF OR YOU'LL F*CK UP YOUR WORLD!", false);
    }

    private static final Map<Entity, Long> despawns = new HashMap<>();

    @Override
    protected void onEnable() {
    }

    @Override
    protected void onDisable() {
        System.err.println("ENTITY DESPAWN IS NOW OFF! THIS IS SUPER DANGEROUS! PLEASE TURN IT BACK ON AS SOON AS POSSIBLE!");
    }

    public static void planDespawnTime(Entity entity, long ticksLeft) {
        despawns.put(entity, ticksLeft);
    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {
        for (Entity entity : new ArrayList<>(despawns.keySet())) {
            Long aLong = despawns.get(entity);
            aLong -= 1;
            if (aLong < 0) {
                entity.remove();
                despawns.remove(entity);
            } else {
                despawns.put(entity, aLong);
            }
        }
    }

    @Subscribe()
    public void onShutdown(ServerShutdownEvent event) {
        for (Entity entity : despawns.keySet()) {
            entity.remove();
        }
        despawns.clear();
    }
}
