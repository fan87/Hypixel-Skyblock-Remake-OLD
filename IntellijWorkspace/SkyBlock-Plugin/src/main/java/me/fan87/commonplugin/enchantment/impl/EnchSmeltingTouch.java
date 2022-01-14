package me.fan87.commonplugin.enchantment.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.Material;
import org.bukkit.block.ContainerBlock;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchSmeltingTouch extends SBEnchantment {
    public EnchSmeltingTouch(SkyBlock skyBlock) {
        super(skyBlock, new SBNamespace("default", "SMELTING_TOUCH"), "Smelting Touch", 1);
    }

    @Subscribe(priority = -200)
    public void onBreak(BlockDropEvent event) {
        if (!event.isCancelled()) {
            if (event.getPlayer().isEnchantmentActive(this)) {
                if (event.getBlockBreakEvent().getBlock() instanceof ContainerBlock) return;
                event.setDrops(Arrays.asList(new ItemStack(Material.DIAMOND_ORE)));
                event.setCancelled(true);
            }
        }
    }
}
