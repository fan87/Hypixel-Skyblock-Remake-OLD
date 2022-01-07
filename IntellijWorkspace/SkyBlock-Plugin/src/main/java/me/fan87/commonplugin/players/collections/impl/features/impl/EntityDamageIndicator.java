package me.fan87.commonplugin.players.collections.impl.features.impl;

import me.fan87.commonplugin.players.collections.impl.features.SBFeature;
import me.fan87.commonplugin.utils.ColorUtils;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class EntityDamageIndicator extends SBFeature {
    public EntityDamageIndicator() {
        super("Entity Damage Indicator", "Shows entity's current health, info, and other info above them.", false);
    }

    private final static List<EntityDamageByEntityEvent> criticals = new ArrayList<>();

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
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        CraftEntity entity = ((CraftEntity) event.getEntity());
        float height = entity.getHandle().length / 2f;
        Location location = entity.getLocation().add(0, height, 0);
        EntityArmorStand entityArmorStand = new EntityArmorStand(((CraftWorld) entity.getWorld()).getHandle(), location.getX(), location.getY(), location.getZ());
        entityArmorStand.n(true);
        entityArmorStand.setGravity(true);
        entityArmorStand.setInvisible(true);
        entityArmorStand.setCustomNameVisible(true);
        if (criticals.contains(event)) {
            criticals.remove(event);
            entityArmorStand.setCustomName(ColorUtils.generateRainbowText("✧" + Math.round(event.getDamage()) + "✧", ColorUtils.RainbowStyle.CRITICAL));
        } else {
            entityArmorStand.setCustomName(ChatColor.GRAY + "" + Math.round(event.getDamage()));
        }
        EntityDespawner.planDespawnTime(((CraftWorld) entity.getWorld()).addEntity(entityArmorStand, CreatureSpawnEvent.SpawnReason.CUSTOM), 20);
    }
}
