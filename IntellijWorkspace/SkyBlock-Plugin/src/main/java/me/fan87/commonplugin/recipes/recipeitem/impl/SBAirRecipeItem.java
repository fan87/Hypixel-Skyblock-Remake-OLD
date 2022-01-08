package me.fan87.commonplugin.recipes.recipeitem.impl;

import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SBAirRecipeItem extends SBRecipeItem {
    @Override
    public boolean check(ItemStack itemStack) {
        return itemStack.getType() == Material.AIR;
    }

    @Override
    public boolean action(ItemStack itemStack) {
        return itemStack.getType() == Material.AIR;
    }
}
