package me.fan87.commonplugin.entity;

import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.entity.impl.EntityVanilla;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.PostEntityUpdateEvent;
import me.fan87.commonplugin.events.impl.PreEntityUpdateEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.npc.Hologram;
import me.fan87.commonplugin.utils.ReflectionUtils;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
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

    @Subscribe
    @SneakyThrows
    public void onPacket(PacketPlaySendEvent event) {
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayOutEntity) {
            try {
                PacketPlayOutEntity packet = (PacketPlayOutEntity) event.getNMSPacket().getRawNMSPacket();
                int entityId = ReflectionUtils.get(packet, "a", int.class);
                byte x = ReflectionUtils.get(packet, "b", byte.class);
                byte y = ReflectionUtils.get(packet, "c", byte.class);
                byte z = ReflectionUtils.get(packet, "d", byte.class);
                byte yaw = ReflectionUtils.get(packet, "e", byte.class);
                byte pitch = ReflectionUtils.get(packet, "f", byte.class);
                boolean onGround = ReflectionUtils.get(packet, "g", boolean.class);
                CraftPlayer player = (CraftPlayer) event.getPlayer();
                ISBEntity entity = getEntity(entityId);
                if (entity instanceof SBEntity) {
                    Hologram nameTag = ((SBEntity) entity).nameTag;
                    List<EntityArmorStand> entityArmorStands = nameTag.loreEntities.get(player);
                    for (EntityArmorStand entityArmorStand : entityArmorStands) {
                        int id = entityArmorStand.getId();
                        if (packet instanceof PacketPlayOutEntity.PacketPlayOutEntityLook) {
                            player.getHandle().playerConnection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(id, yaw, pitch, onGround));
                        }
                        if (packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMove) {
                            player.getHandle().playerConnection.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMove(id, x, y, z, onGround));
                        }
                        if (packet instanceof PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook) {
                            player.getHandle().playerConnection.sendPacket(new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(id, x, y, z, yaw, pitch, onGround));
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayOutEntityTeleport) {
            PacketPlayOutEntityTeleport packet = (PacketPlayOutEntityTeleport) event.getNMSPacket().getRawNMSPacket();
            int entityId = ReflectionUtils.get(packet, "a", int.class);
            int x = ReflectionUtils.get(packet, "b", int.class);
            int y = ReflectionUtils.get(packet, "c", int.class);
            int z = ReflectionUtils.get(packet, "d", int.class);
            byte yaw = ReflectionUtils.get(packet, "e", byte.class);
            byte pitch = ReflectionUtils.get(packet, "f", byte.class);
            boolean onGround = ReflectionUtils.get(packet, "g", boolean.class);
            ISBEntity entity = getEntity(entityId);
            if (entity instanceof SBEntity) {
                Hologram nameTag = ((SBEntity) entity).nameTag;
                CraftPlayer player = (CraftPlayer) event.getPlayer();
                List<EntityArmorStand> entityArmorStands = nameTag.loreEntities.get(player);
                for (EntityArmorStand entityArmorStand : entityArmorStands) {
                    player.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entityArmorStand.getId(), x, (int) (y + (((CraftLivingEntity) entity.getEntity()).getHandle().getHeadHeight() + 0.25)*32), z, yaw, pitch, onGround));
                }
            }
        }
    }

    private ISBEntity asSBEntityMirror(LivingEntity entity) {
        return new EntityVanilla(skyBlock, entity);
    }

    public ISBEntity getEntity(Entity entity) {
        return entities.stream().filter(e -> e.getEntity().getEntityId() == entity.getEntityId()).findFirst().orElse(null);
    }

    public ISBEntity getEntity(int entityId) {
        return entities.stream().filter(e -> e.getEntity().getEntityId() == entityId).findFirst().orElse(null);
    }

    public void addEntity(LivingEntity entity) {

    }

}
