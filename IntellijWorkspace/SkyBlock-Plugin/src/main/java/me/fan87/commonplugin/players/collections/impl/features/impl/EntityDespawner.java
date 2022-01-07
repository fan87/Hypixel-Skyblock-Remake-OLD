package me.fan87.commonplugin.players.collections.impl.features.impl;

import me.fan87.commonplugin.events.impl.ServerShutdownEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.players.collections.impl.features.SBFeature;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

public class EntityDespawner extends SBFeature {
    public EntityDespawner() {
        super("Entity Despawn", "Despawn mobs automatically when their \"ticksLeft\" ran out. DO NOT TURN THIS OFF OR YOU'LL F*CK UP YOUR WORLD!", false);
    }

    private static final Map<Integer, Long> despawns = new HashMap<>();

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {
        System.err.println("ENTITY DESPAWN IS NOW OFF! THIS IS SUPER DANGEROUS! PLEASE TURN IT BACK ON AS SOON AS POSSIBLE!");
    }

    public static void planDespawnTime(Entity entity, long ticksLeft) {
        despawns.put(entity.getEntityId(), ticksLeft);
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        for (World world : skyBlock.getServer().getWorlds()) {
            for (Entity entity : world.getEntities()) {
                Long left = despawns.get(entity.getEntityId());
                if (left == null) continue;
                if (left <= 0) {
                    entity.remove();
                }
                despawns.put(entity.getEntityId(), left-1);
            }
        }
    }

    @Subscribe
    public void onShutdown(ServerShutdownEvent event) {
        for (Integer integer : despawns.keySet()) {
            for (World world : skyBlock.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity.getEntityId() == integer) {
                        entity.remove();
                    }
                }
            }
        }
        despawns.clear();
    }
}
