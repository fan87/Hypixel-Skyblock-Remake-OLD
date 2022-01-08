package me.fan87.commonplugin.recipes.impl;

import lombok.Getter;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import me.fan87.commonplugin.recipes.recipeitem.impl.SBAirRecipeItem;
import me.fan87.commonplugin.recipes.recipeitem.impl.SBCustomRecipeItem;
import me.fan87.commonplugin.utils.ItemUtils;
import me.fan87.commonplugin.utils.SBMatrix;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SBShapedRecipe extends SBRecipe {

    private final Map<Character, SBRecipeItem> ingredients = new HashMap<>();
    @Getter
    private String[] shape = new String[0];
    private final ItemStack outputItem;
    private final boolean unlockable;
    private final SBCustomItem item;

    public SBShapedRecipe(ShapedRecipe shapedRecipe, boolean unlockable) {
        outputItem = shapedRecipe.getResult();
        shape = shapedRecipe.getShape();
        Map<Character, ItemStack> map = shapedRecipe.getIngredientMap();
        for (Character character : map.keySet()) {
            if (map.get(character) == null) ingredient(character, new SBAirRecipeItem()); else ingredient(character, new SBCustomRecipeItem(map.get(character).getType(), map.get(character).getDurability(), 1));
        }
        shape(shapedRecipe.getShape());
        this.item = null;
        this.unlockable = unlockable;
    }

    public SBShapedRecipe(SBCustomItem outputType, int amount, boolean unlockable) {
        ingredient(' ', new SBAirRecipeItem());
        this.unlockable = unlockable;
        this.outputItem = new SBItemStack(outputType, amount).getItemStack();
        this.item = outputType;
    }

    /**
     * @deprecated Use {@link SBShapedRecipe#SBShapedRecipe(SBCustomItem, int, boolean) instead}
     * @param outputItem Output item, but deprecated since recipe system won't be able to get the output type
     */
    @Deprecated
    public SBShapedRecipe(ItemStack outputItem) {
        this.outputItem = outputItem;
        ingredient(' ', new SBAirRecipeItem());
        this.unlockable = true;
        this.item = null;
    }

    public SBShapedRecipe ingredient(char c, SBRecipeItem item) {
        ingredients.put(c, item);
        return this;
    }

    private SBRecipeItem getIngredient(char c) {
        return ingredients.get(c);
    }

    public SBShapedRecipe shape(String... shape) {
        for (String s : shape) {
            for (char c : s.toCharArray()) {
                if (getIngredient(c) == null) throw new NullPointerException(c + " is not defined in recipe!");
            }
        }
        this.shape = shape;
        return this;
    }



    @Override
    public boolean match(ItemStack[] items, int width, int height) {
        if (!isValid()) {
            return false;
        }
        assert items.length == width*height;
        SBMatrix<ItemStack> cleaned = ItemUtils.getRecipeMin(items, width, height);
        if (cleaned.getHeight() == 0 || cleaned.getWidth() == 0) return false;
        SBRecipeItem[] recipeItems = getRecipeItems();
        if (cleaned.getContent().length != recipeItems.length) return false;
        for (int i = 0; i < cleaned.getContent().length; i++) {
            if (!recipeItems[i].check(cleaned.getContent()[i])) {
                return false;
            }
        }
        return true;
    }


    public boolean isValid() {
        for (String s : shape) {
            for (char c : s.toCharArray()) {
                if (c != ' ') return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return outputItem.clone();
    }

    @Override
    public RecipeType getType() {
        return RecipeType.CRAFTING_TABLE;
    }

    @Override
    public boolean action(ItemStack[] items, int width, int height) {
        if (!isValid()) return false;
        assert items.length == width*height;
        SBMatrix<ItemStack> cleaned = ItemUtils.getRecipeMin(items, width, height);
        if (cleaned.getHeight() == 0 || cleaned.getWidth() == 0) return false;
        SBRecipeItem[] recipeItems = getRecipeItems();
        if (cleaned.getContent().length != recipeItems.length) return false;
        for (int i = 0; i < cleaned.getContent().length; i++) {
            if (!recipeItems[i].check(cleaned.getContent()[i])) {
                return false;
            }
        }
        for (int i = 0; i < cleaned.getContent().length; i++) {
            recipeItems[i].action(cleaned.getContent()[i]);
        }
        return true;
    }

    @Override
    public SBCustomItem getOutputType() {
        return item;
    }

    public boolean isUnlockable() {
        return unlockable;
    }

    @Override
    public ItemStack[] getExample() {
        ItemStack[] itemStacks = new ItemStack[9];
        for (int y = 0; y < getShape().length; y++) {
            for (int x = 0; x < getShape()[y].toCharArray().length; x++) {
                itemStacks[y*3 + x] = getIngredient(getShape()[y].toCharArray()[x]).getExampleItem();
            }
        }
        return itemStacks;
    }

    private SBRecipeItem[] getRecipeItems() {
        List<SBRecipeItem> items = new ArrayList<>();
        for (String s : shape) {
            for (char c : s.toCharArray()) {
                items.add(getIngredient(c));
            }
        }
        return items.toArray(new SBRecipeItem[0]);
    }


}
