package me.fan87.commonplugin.entity;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.Hologram;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class SBEntity extends ISBEntity {

    protected final SkyBlock skyBlock;
    private LivingEntity entity;
    private boolean removed;
    private Hologram nameTag;

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
        return entity.getHealth() * 5;
    }

    @Override
    public boolean isDead() {
        return getCurrentHealth() <= 0;
    }

    public void remove() {
        removed = true;
    }

    @Override
    public void onDestroy() {
        if (nameTag != null) {
            nameTag.destroy();
        }
    }

    @Override
    protected boolean shouldBeRemoved() {
        return entity.isDead() || removed;
    }

}
