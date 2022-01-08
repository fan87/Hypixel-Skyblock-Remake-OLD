package me.fan87.commonplugin.recipes.recipeitem;

import org.bukkit.inventory.ItemStack;

public abstract class SBRecipeItem {

    public abstract boolean check(ItemStack itemStack);
    public abstract boolean action(ItemStack itemStack);
    public abstract ItemStack getExampleItem();

}
