package me.fan87.commonplugin.recipes.recipeitem.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import me.fan87.commonplugin.recipes.recipeitem.SBRecipeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
@EqualsAndHashCode(callSuper = false)
public class SBSimpleRecipeItem extends SBRecipeItem {

    private final Material material;
    private final int amount;

    public SBSimpleRecipeItem(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }

    public SBSimpleRecipeItem(Material material) {
        this(material, 1);
    }

    @Override
    public boolean check(ItemStack itemStack) {
        if (itemStack == null) return material == Material.AIR;
        if (material == Material.AIR && itemStack.getType() == Material.AIR) return true; // Ignore amount
        return material == itemStack.getType() && itemStack.getAmount() >= amount;
    }

    @Override
    public boolean action(ItemStack itemStack) {
        if (itemStack == null) return material == Material.AIR;
        if (material == Material.AIR && itemStack.getType() == Material.AIR) return true; // Ignore amount
        boolean b = material == itemStack.getType() && itemStack.getAmount() >= amount;
        if (b) {
            itemStack.setAmount(itemStack.getAmount() - amount);
        }
        return b;
    }
}
