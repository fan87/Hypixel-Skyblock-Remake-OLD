package me.fan87.commonplugin.features.entityindicator;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.entitydespawn.EntityDespawn;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.greenrobot.eventbus.Subscribe;

public class EntityDamageIndicator extends SBFeature {
    public EntityDamageIndicator() {
        super("Entity Damage Indicator", "Shows entity's current health, info, and other info above them.", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -60)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        CraftEntity entity = ((CraftEntity) event.getEntity());
        float height = entity.getHandle().length / 2f;
        Location location = entity.getLocation().add(0, height, 0);
        EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftWorld) entity.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
        entityArmorStand.n(true);
        entityArmorStand.setGravity(false);
        entityArmorStand.setInvisible(true);
        entityArmorStand.setCustomNameVisible(true);
        entityArmorStand.setCustomName(ChatColor.GRAY + "" + event.getDamage());
        EntityDespawn.planDespawnTime(((CraftWorld) entity.getWorld()).addEntity(entityArmorStand, CreatureSpawnEvent.SpawnReason.CUSTOM), 20);
    }
}
