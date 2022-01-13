package me.fan87.commonplugin.features.impl.gameplay.rendering;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.DamageIndicatorEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.impl.logic.EntityDespawner;
import me.fan87.commonplugin.utils.ColorUtils;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import me.fan87.commonplugin.events.Subscribe;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class EntityDamageIndicator extends SBFeature {
    public EntityDamageIndicator() {
        super("Entity Damage Indicator", "Shows entity's current health, info, and other info above them.", false);
    }

    private final static List<EntityDamageEvent> criticals = new ArrayList<>();

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    public static void setCritical(EntityDamageByEntityEvent event, boolean value) {
        if (value) {
            criticals.add(event);
        } else {
            criticals.remove(event);
        }
    }

    public static boolean isCritical(EntityDamageByEntityEvent event) {
        return criticals.contains(event);
    }

    @Subscribe(priority = -60)
    public void onEntityDamage(EntityDamageEvent event) {
        CraftEntity entity = ((CraftEntity) event.getEntity());
        if (!(entity instanceof LivingEntity)) {
            return;
        }
        DamageIndicatorEvent event1 = new DamageIndicatorEvent(event);
        EventManager.post(event1);
        if (event1.isCancelled()) return;
        float height = entity.getHandle().length / 2f;
        Location location = entity.getLocation().add(0, height, 0);
        EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftWorld) entity.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
        entityArmorStand.n(true);
        entityArmorStand.setGravity(true);
        entityArmorStand.setInvisible(true);
        entityArmorStand.setCustomNameVisible(true);
        if (criticals.contains(event)) {
            criticals.remove(event);
            entityArmorStand.setCustomName(ColorUtils.generateRainbowText("✧" + Math.round(event.getDamage()*5f) + "✧", ColorUtils.RainbowStyle.CRITICAL));
        } else {
            entityArmorStand.setCustomName(ChatColor.GRAY + "" + Math.round(event.getDamage()*5f));
        }
        EntityDespawner.planDespawnTime(((CraftWorld) entity.getWorld()).addEntity(entityArmorStand, CreatureSpawnEvent.SpawnReason.CUSTOM), 20);
    }
}
