package me.fan87.commonplugin.recipes.recipeitem.impl;

import lombok.Getter;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SBCustomRecipeItem extends SBRecipeItem {

    @Getter
    private final int amount;
    @Getter
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
        if (itemStack == null || itemStack.getType() == Material.AIR) return false;
        SBItemStack sbItemStack = new SBItemStack(itemStack);
        if (getItem() == null) return false;
        String first = sbItemStack.getType().getItem().getNamespace();
        String second = getItem().getNamespace();
        return (sbItemStack.getType().getItem() == item
                || ((first.startsWith("WOOD:") || first.equals("WOOD")) && (second.startsWith("WOOD:") || second.equals("WOOD")))
        ) && itemStack.getAmount() >= amount && sbItemStack.canBeUsedForCrafting();
    }

    @Override
    public boolean action(ItemStack itemStack) {
        if (itemStack == null || itemStack.getType() == Material.AIR) return false;
        SBItemStack sbItemStack = new SBItemStack(itemStack);
        String first = sbItemStack.getType().getItem().getNamespace();
        String second = getItem().getNamespace();
        if ((sbItemStack.getType().getItem() == item
                || ((first.startsWith("WOOD:") || first.equals("WOOD")) && (second.startsWith("WOOD:") || second.equals("WOOD")))
        ) && sbItemStack.canBeUsedForCrafting()) {
            if (itemStack.getAmount() >= amount) {
                itemStack.setAmount(itemStack.getAmount() - amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getExampleItem() {
        return new ItemStackBuilder(item.newItemStack())
                .setAmount(amount)
                .build();
    }
}
