package me.fan87.commonplugin.recipes;

import org.bukkit.inventory.ItemStack;

public abstract class SBRecipe {

    public abstract boolean match(ItemStack[] items, int width, int height);
    public abstract ItemStack getOutput();
    public abstract RecipeType getType();
    public abstract boolean action(ItemStack[] items, int width, int height);

    public enum RecipeType {
        CRAFTING_TABLE;
    }

}
