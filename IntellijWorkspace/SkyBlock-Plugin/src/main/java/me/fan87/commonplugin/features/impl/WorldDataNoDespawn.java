package me.fan87.commonplugin.features.impl;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class WorldDataNoDespawn extends SBFeature {
    public WorldDataNoDespawn() {
        super("World Data Saver", "Prevents WorldData entity getting killed", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof ArmorStand) {
            ItemStack itemInHand = ((ArmorStand) event.getEntity()).getItemInHand();
            net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
            if (itemStack.getTag() == null) itemStack.setTag(new NBTTagCompound());
            if (itemStack.getTag().hasKey("WorldDataStorage")) {
                event.setCancelled(true);
            }
        }
    }
}
