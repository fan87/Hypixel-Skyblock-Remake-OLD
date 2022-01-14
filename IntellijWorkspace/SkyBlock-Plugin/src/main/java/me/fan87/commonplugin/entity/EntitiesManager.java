package me.fan87.commonplugin.entity;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.entity.impl.EntityVanilla;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.PostEntityUpdateEvent;
import me.fan87.commonplugin.events.impl.PreEntityUpdateEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class EntitiesManager {

    private final SkyBlock skyBlock;

    private final List<ISBEntity> entities = new ArrayList<>();

    public EntitiesManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.register(this);
    }

    @Subscribe(priority = 1000)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            ISBEntity entity = getEntity(event.getEntity());
            if (entity != null) return;
            entity = asSBEntityMirror((LivingEntity) event.getEntity());
            entities.add(entity);
        }
    }

    @Subscribe(priority = 0)
    public void tickEntity(ServerTickEvent event) {
        for (ISBEntity entity : new ArrayList<>(entities)) {
            PreEntityUpdateEvent entityUpdateEvent = new PreEntityUpdateEvent(entity);
            EventManager.post(entityUpdateEvent);
            ISBEntity.EntityUpdateResult entityUpdateResult = entity.updateEntity();
            PostEntityUpdateEvent postEntityUpdateEvent = new PostEntityUpdateEvent(entity, entityUpdateResult);
            EventManager.post(postEntityUpdateEvent);
            entityUpdateResult = postEntityUpdateEvent.getResult();
            if (entityUpdateResult == ISBEntity.EntityUpdateResult.CONTINUE) continue;
            if (entityUpdateResult == ISBEntity.EntityUpdateResult.REMOVE) entities.remove(entity);
        }
    }

    private ISBEntity asSBEntityMirror(LivingEntity entity) {
        return new EntityVanilla(skyBlock, entity);
    }

    public ISBEntity getEntity(Entity entity) {
        return entities.stream().filter(e -> e.getEntity() == entity).findFirst().orElse(null);
    }

    public void addEntity(LivingEntity entity) {

    }

}
