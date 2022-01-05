package me.fan87.commonplugin.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Predicate;

public class ItemUtils {

    public static boolean isAir(ItemStack itemStack) {
        return itemStack == null || itemStack.getType() == Material.AIR;
    }

    /**
     * I don't know how you explain it, just look at example:
     * Input:
     *
     *  a
     *  aa
     *
     * Output:
     *  a
     *  aa
     */
    public static SBMatrix<ItemStack> getRecipeMin(ItemStack[] itemStacks, int width, int height) {
        return new SBMatrix<ItemStack>(itemStacks, ItemStack.class, width, height).cleanEmpty(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                return isAir(itemStack);
            }
        });
    }

}
