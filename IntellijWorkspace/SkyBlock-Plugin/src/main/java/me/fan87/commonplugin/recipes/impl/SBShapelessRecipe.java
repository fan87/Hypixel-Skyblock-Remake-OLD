package me.fan87.commonplugin.recipes.impl;

import lombok.Getter;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import me.fan87.commonplugin.recipes.recipeitem.impl.SBSimpleRecipeItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class SBShapelessRecipe extends SBRecipe {

    @Getter
    private final List<SBRecipeItem> ingredients = new ArrayList<>();

    public SBShapelessRecipe(ShapelessRecipe recipe) {
        for (ItemStack itemStack : recipe.getIngredientList()) {
            ingredients.add(new SBSimpleRecipeItem(itemStack.getType(), itemStack.getAmount()));
        }
        this.output = recipe.getResult();
    }

    private final ItemStack output;

    public SBShapelessRecipe(ItemStack output) {
        this.output = output;
    }


    @Override
    public boolean match(ItemStack[] items, int width, int height) {
        for (SBRecipeItem ingredient : ingredients) {
            boolean passed = false;
            for (ItemStack item : items) {
                if (ingredient.check(item)) {
                    passed = true;
                }
            }
            if (!passed) return false;
        }
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public RecipeType getType() {
        return RecipeType.CRAFTING_TABLE;
    }

    @Override
    public boolean action(ItemStack[] items, int width, int height) {
        for (SBRecipeItem ingredient : ingredients) {
            boolean passed = false;
            for (ItemStack item : items) {
                if (ingredient.check(item)) {
                    passed = true;
                    break;
                }
            }
            if (!passed) return false;
        }
        for (SBRecipeItem ingredient : ingredients) {
            for (ItemStack item : items) {
                ingredient.action(item);
            }
        }
        return true;
    }
}
