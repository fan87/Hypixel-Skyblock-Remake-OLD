package me.fan87.commonplugin.enchantment.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.utils.SBMap;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.enchantments.Enchantment;

import java.util.Map;

public class EnchEfficiency extends SBEnchantment {

    public EnchEfficiency(SkyBlock skyBlock) {
        super(skyBlock, new SBNamespace("default", "EFFICIENCY"), "Efficiency", 5);
    }

    @Override
    public Map<Enchantment, Integer> getVanillaEnchantment(int level) {
        Map<Enchantment, Integer> map = new SBMap<>();
        map.put(Enchantment.DIG_SPEED, level);
        return map;
    }

    @Override
    public boolean isItemAccepted(SBCustomItem item) {
        SBCustomItem.Category category = item.getCategory();
        return category.isTool();
    }
}
