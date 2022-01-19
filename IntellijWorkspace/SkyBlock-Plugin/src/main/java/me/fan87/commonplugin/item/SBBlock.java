package me.fan87.commonplugin.item;

import me.fan87.commonplugin.SkyBlock;
import org.bukkit.Material;

public abstract class SBBlock extends SBCustomItem {


    public SBBlock(String namespace, String displayName, String description, Material material, short durability, Rarity rarity, Category category, double sellPrice, SkyBlock skyBlock, RecipeCategory recipeCategory, boolean unlockedByDefault) {
        super(namespace, displayName, description, material, durability, rarity, category, sellPrice, skyBlock, recipeCategory, unlockedByDefault);
    }

    public SBBlock(String namespace, String displayName, String description, Material material, short durability, Rarity rarity, Category category, double sellPrice, SkyBlock skyBlock, RecipeCategory recipeCategory) {
        super(namespace, displayName, description, material, durability, rarity, category, sellPrice, skyBlock, recipeCategory);
    }

    @Override
    public boolean isPlaceable() {
        return true;
    }

    public abstract Class<?> getBlockDataClass();

}
