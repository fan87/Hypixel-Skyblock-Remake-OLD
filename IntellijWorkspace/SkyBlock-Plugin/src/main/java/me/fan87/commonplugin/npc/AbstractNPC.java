package me.fan87.commonplugin.npc;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.RotationUtils;
import me.fan87.commonplugin.world.WorldsManager;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractNPC<EntityType extends EntityLiving> {

    public AbstractNPC(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }

    protected final SkyBlock skyBlock;


    protected final Map<Player, List<EntityArmorStand>> loreEntities = new HashMap<>();
    @Getter
    protected final List<Player> viewers = new ArrayList<>();
    @Getter
    @Setter
    private int appearDistance = 64;

    public Map<Player, List<EntityArmorStand>> getLoreEntities() {
        return loreEntities;
    }

    public abstract EntityType getNpcEntity();
    public abstract AbstractNPC<EntityType> create(World world);
    public abstract AbstractNPC<EntityType> display(Player... players);
    public abstract AbstractNPC<EntityType> updatePosition(Player... players);
    public AbstractNPC<EntityType> updatePosition() {
        return updatePosition(viewers.toArray(new Player[0]));
    }
    public abstract AbstractNPC<EntityType> hide(Player... players);
    public AbstractNPC<EntityType> destroy() {
        for (Player viewer : new ArrayList<>(viewers)) {
            hide(viewer);
        }
        onDestroy();
        skyBlock.getNpcManager().removeNpc(this);
        return this;
    }

    public Entity asCraftCopy() {
        return CraftEntity.getEntity(((CraftServer) skyBlock.getServer()), getNpcEntity());
    }

    public List<String> getLore(Player player) {
        return new ArrayList<>();
    }

    protected void onInteract(SBPlayer player) {}
    protected void onTick() {}
    protected void onCreate() {}
    protected void onDestroy() {}
    protected void onHide(Player player) {}

    public AbstractNPC<EntityType> rotate(float yaw, float pitch) {
        byte y = (byte) (yaw * 256 / 360);
        byte p = (byte) (pitch * 256 / 360);
        getNpcEntity().yaw = yaw;
        getNpcEntity().pitch = pitch;
        for (Player viewer : viewers) {
            ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntity.PacketPlayOutEntityLook(getNpcEntity().getId(), y, p, getNpcEntity().onGround));
            ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityHeadRotation(getNpcEntity(), y));
        }
        return this;
    }

    protected void displayLore(EntityType npcEntity, Player player) {
        List<String> lore = Lists.reverse(getLore(player));
        ArrayList<EntityArmorStand> value = new ArrayList<>();
        this.loreEntities.put(player, value);
        int index = 1;
        for (String s : lore) {
            EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftPlayer) player).getHandle().getWorld(), npcEntity.locX, npcEntity.locY + npcEntity.getHeadHeight() + 0.25*index++, npcEntity.locZ);
            entityArmorStand.n(true);
            entityArmorStand.setGravity(true);
            entityArmorStand.setInvisible(true);
            entityArmorStand.setCustomNameVisible(true);
            entityArmorStand.setCustomName(s);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutSpawnEntityLiving(entityArmorStand));
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entityArmorStand));
            value.add(entityArmorStand);
        }
    }

    protected void hideLore(Player player) {
        List<EntityArmorStand> entityArmorStands = loreEntities.get(player);
        if (entityArmorStands != null) {
            for (EntityArmorStand entityArmorStand : entityArmorStands) {
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(entityArmorStand.getId()));
            }
        }
    }

    protected void updateLorePosition(Player viewer, EntityType npcEntity) {
        List<EntityArmorStand> entityArmorStands = loreEntities.get(viewer);
        if (entityArmorStands != null) {
            int index = 1;
            for (EntityArmorStand entityArmorStand : entityArmorStands) {
                entityArmorStand.setPosition(npcEntity.locX, npcEntity.locY + npcEntity.getHeadHeight() + 0.25*index++, npcEntity.locZ);
                ((CraftPlayer) viewer).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityTeleport(entityArmorStand));
            }
        }
    }

    public boolean displayToAll() {
        return true;
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface NPCRegistry {
        String addonName();
        String namespace();
        String name();
        String skin();
        WorldsManager.WorldType world() default WorldsManager.WorldType.NONE;
    }


    public static NPCRegistry getNPCRegistry(Class<? extends AbstractNPC> clazz) {
        for (NPCRegistry annotation : clazz.getAnnotationsByType(NPCRegistry.class)) {
            return annotation;
        }
        return null;
    }

    public void tryFace(double x, double y, double z) {
        float[] face = RotationUtils.tryFace(getNpcEntity().locX - x, getNpcEntity().locY - y, getNpcEntity().locZ - z);
        rotate(face[0], face[1]);
    }

}
