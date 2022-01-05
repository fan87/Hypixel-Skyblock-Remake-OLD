package me.fan87.commonplugin.utils;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

    /**
     * Give item to the player, and drop the item if inventory is full
     * @param player The player
     * @param items Items to add
     */
    public static void giveItem(Player player, ItemStack... items) {
        for (ItemStack value : player.getInventory().addItem(items).values()) {
            ((CraftPlayer) player).getHandle().drop(CraftItemStack.asNMSCopy(value), true);
        }
    }

}
