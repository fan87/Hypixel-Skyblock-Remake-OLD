package me.fan87.commonplugin.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InventoryUtils {

    /**
     * Give item to the player, and drop the item if inventory is full
     * @param player The player
     * @param items Items to add
     */
    public static void giveItem(Player player, ItemStack... items) {
        for (ItemStack value : player.getInventory().addItem(Arrays.stream(items).filter(Objects::nonNull).collect(Collectors.toList()).toArray(new ItemStack[0])).values()) {
            if (value == null) continue;
            ((CraftPlayer) player).getHandle().drop(CraftItemStack.asNMSCopy(value), true);
        }
    }

    public static boolean isInventoryFull(Inventory inventory) {
        return inventory.firstEmpty() == -1;
    }

}
