package me.fan87.commonplugin.item.impl.misc;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import org.bukkit.Color;
import org.bukkit.Material;

public class ItemVanilla extends SBCustomItem {

    public ItemVanilla(String namespace, String displayName, String description, Material material, short damage, String skin, Rarity rarity, boolean glowing, Color color, Category category, double price, SkyBlock skyBlock) {
        super(namespace, displayName, description, material, damage, rarity, category, price, skyBlock, RecipeCategory.SPECIAL);
    }


    @Override
    public boolean isPlaceable() {
        return true;
    }
}
