package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropTramplingDisabler extends SBFeature {
    public CropTramplingDisabler() {
        super("Crop trampling disabler", "Disables the crop trampling mechanism", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onCropDestroy(PlayerInteractEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getPlayer().getWorld().getName());
        if (world == null || world.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) return;
        if (event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType().equals(Material.SOIL)) {
            event.setCancelled(true);
        }

    }

    @Subscribe()
    public void onCropDestroy(EntityInteractEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getEntity().getWorld().getName());
        if (world == null || world.getWorldType() == WorldsManager.WorldType.PRIVATE_ISLAND) return;
        if (event.getEntity() instanceof Creature && event.getBlock().getType().equals(Material.SOIL)) {
            event.setCancelled(true);
        }

    }
}
