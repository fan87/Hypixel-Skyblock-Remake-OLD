package me.fan87.commonplugin.entity;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.Hologram;
import net.minecraft.server.v1_8_R3.EntityArmorStand;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class SBEntity extends ISBEntity {

    protected final SkyBlock skyBlock;
    private LivingEntity entity;
    private boolean removed;
    protected Hologram nameTag;

    public SBEntity(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
    }


    protected abstract LivingEntity spawnEntity();

    @Override
    public void spawn(Location location) {
        this.entity = spawnEntity();
        skyBlock.getEntitiesManager().addEntity(this.entity);
        entity.setMaxHealth(getMaxHealth()/5d);
        entity.setHealth(getMaxHealth()/5d);
        entity.setRemoveWhenFarAway(false);
        entity.setCustomName("");
        entity.setCustomNameVisible(false);
        nameTag = new Hologram(skyBlock, entity.getLocation(), String.format("%s[%sLv%d%s] %s%s %s%d%s/%s%d%s❤",
                ChatColor.DARK_GRAY,
                ChatColor.GRAY,
                getLevel(),
                ChatColor.DARK_GRAY,
                ChatColor.RED,
                getName(),
                ChatColor.GREEN,
                (int) getCurrentHealth(),
                ChatColor.WHITE,
                ChatColor.GREEN,
                (int) getMaxHealth(),
                ChatColor.RED
        ));
        nameTag.create(location.getWorld());
    }

    @Override
    public LivingEntity getEntity() {
        return entity;
    }

    @Override
    public double getCurrentHealth() {
        return entity.getHealth() * 5f;
    }

    @Override
    public boolean isDead() {
        return getCurrentHealth() <= 0;
    }

    public void remove() {
        removed = true;
    }

    @Override
    public EntityUpdateResult updateEntity() {
        double x = ((CraftLivingEntity) entity).getHandle().locX;
        double y = ((CraftLivingEntity) entity).getHandle().locY;
        double z = ((CraftLivingEntity) entity).getHandle().locZ;
        nameTag.getNpcEntity().setPosition(x, y, z);
        for (Player player : nameTag.loreEntities.keySet()) {
            for (EntityArmorStand entityArmorStand : nameTag.loreEntities.get(player)) {

                String format = String.format("%s[%sLv%d%s] %s%s %s%d%s/%s%d%s❤",
                        ChatColor.DARK_GRAY,
                        ChatColor.GRAY,
                        getLevel(),
                        ChatColor.DARK_GRAY,
                        ChatColor.RED,
                        getName(),
                        ChatColor.GREEN,
                        (int) getCurrentHealth(),
                        ChatColor.WHITE,
                        ChatColor.GREEN,
                        (int) getMaxHealth(),
                        ChatColor.RED
                );
                if (!entityArmorStand.getCustomName().equals(format)) {
                    entityArmorStand.setCustomName(format);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityMetadata(entityArmorStand.getId(), entityArmorStand.getDataWatcher(), false));
                }
            }
        }
        return super.updateEntity();
    }

    @Override
    public void onDestroy() {
        if (nameTag != null) {
            nameTag.destroy();
        }
    }

    @Override
    protected boolean shouldBeRemoved() {
        return ((CraftWorld) getEntity().getWorld()).getHandle().a(entity.getEntityId()) == null || removed;
    }

}
