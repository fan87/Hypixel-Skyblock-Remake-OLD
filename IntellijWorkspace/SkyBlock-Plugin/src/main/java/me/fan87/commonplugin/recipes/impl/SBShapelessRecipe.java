package me.fan87.commonplugin.recipes.impl;

import lombok.Getter;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import me.fan87.commonplugin.recipes.recipeitem.impl.SBCustomRecipeItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.ArrayList;
import java.util.List;

public class SBShapelessRecipe extends SBRecipe {

    @Getter
    private final List<SBRecipeItem> ingredients = new ArrayList<>();

    private final boolean unlockable;
    private final SBCustomItem item;
    private final ItemStack output;

    public SBShapelessRecipe(ShapelessRecipe recipe) {
        for (ItemStack itemStack : recipe.getIngredientList()) {
            ingredients.add(new SBCustomRecipeItem(itemStack.getType(), itemStack.getDurability(), itemStack.getAmount()));
        }
        this.output = recipe.getResult();
        this.unlockable = true;
        this.item = null;
    }


    public SBShapelessRecipe(SBCustomItem item, int amount, boolean unlockable) {
        this.item = item;
        this.output = new SBItemStack(item, amount).getItemStack();
        this.unlockable = unlockable;
    }

    /**
     * @deprecated Use {@link SBShapelessRecipe#SBShapelessRecipe(SBCustomItem, int, boolean) instead}
     * @param output Output item, but deprecated since recipe system won't be able to get the output type
     */
    @Deprecated
    public SBShapelessRecipe(ItemStack output) {
        this.output = output;
        this.unlockable = false;
        this.item = null;
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
        return output.clone();
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

    @Override
    public SBCustomItem getOutputType() {
        return item;
    }


    @Override
    public boolean isUnlockable() {
        return unlockable;
    }
}
