package me.fan87.commonplugin.recipes.recipeitem.impl;

import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SBCustomRecipeItem extends SBRecipeItem {

    private final int amount;
    private final SBCustomItem item;

    public SBCustomRecipeItem(SBCustomItem item, int amount) {
        this.amount = amount;
        this.item = item;
    }

    public SBCustomRecipeItem(Material material, short durability, int amount) {
        this.amount = amount;
        this.item = SBItems.getVanillaItem(material, durability);
    }

    public SBCustomRecipeItem(SBCustomItem item) {
        this(item, 1);
    }

    @Override
    public boolean check(ItemStack itemStack) {
        SBItemStack sbItemStack = new SBItemStack(itemStack);
        return sbItemStack.getType().getItem() == item && itemStack.getAmount() >= amount && sbItemStack.canBeUsedForCrafting();
    }

    @Override
    public boolean action(ItemStack itemStack) {
        SBItemStack sbItemStack = new SBItemStack(itemStack);
        if (sbItemStack.getType().getItem() == item && sbItemStack.canBeUsedForCrafting()) {
            if (itemStack.getAmount() >= amount) {
                itemStack.setAmount(itemStack.getAmount() - amount);
                return true;
            }
        }
        return false;
    }
}
