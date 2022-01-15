package me.fan87.commonplugin.enchantment.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.Material;
import org.bukkit.block.ContainerBlock;
import org.bukkit.inventory.ItemStack;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchSmeltingTouch extends SBEnchantment {
    public EnchSmeltingTouch(SkyBlock skyBlock) {
        super(skyBlock, new SBNamespace("default", "SMELTING_TOUCH"), "Smelting Touch", 1);
    }

    @Subscribe(priority = -200)
    public void onBreak(BlockDropEvent event) {
        if (event.isCancelled()) return;
        if (!event.getPlayer().isEnchantmentActive(this)) return;
        if (event.getBlockBreakEvent().getBlock() instanceof ContainerBlock) return;
        List<ItemStack> meltedDrops = new ArrayList<>();
        for (ItemStack drop : event.getDrops()) {
            if (drop.getType() == Material.IRON_ORE) {
                drop.setType(Material.IRON_INGOT);
            }
            if (drop.getType() == Material.GOLD_ORE) {
                drop.setType(Material.GOLD_INGOT);
            }
            if (drop.getType() == Material.LOG || drop.getType() == Material.LOG_2) {
                drop.setType(Material.COAL);
                drop.setDurability(((short) 1));
            }
            if (drop.getType() == Material.COBBLESTONE) {
                drop.setType(Material.STONE);
            }
            if (drop.getType() == Material.STONE) {
                drop.setType(Material.COBBLESTONE);
            }
            meltedDrops.add(drop);
        }
    }
}

