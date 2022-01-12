package me.fan87.commonplugin.enchantment.impl;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.inventory.ItemStack;
import me.fan87.commonplugin.events.Subscribe;

public class EnchTelekinesis extends SBEnchantment {

    public EnchTelekinesis(SkyBlock skyBlock) {
        super(skyBlock, new SBNamespace("default", "TELEKINESIS"), "Telekinesis", 1);
    }

    @Subscribe(priority = -200)
    public void onBreak(BlockDropEvent event) {
        if (!event.isCancelled()) {
            ItemStack[] itemStacks = event.getDrops().toArray(new ItemStack[0]);
            for (ItemStack itemStack : itemStacks) {
                if (event.getPlayer().getPlayer().getInventory().firstEmpty() != -1) {
                    event.getDrops().remove(itemStack);
                    NBTItem nbt = new NBTItem(itemStack, true);
                    boolean wasCustomized = nbt.hasKey("ExtraAttributes");
                    SBItemStack itemStack1 = new SBItemStack(itemStack);
                    itemStack = itemStack1.getItemStack();
                    if (!wasCustomized) {
                        for (SBCollection collection : event.getPlayer().getCollections().getCollections()) {
                            if (collection.getItem() == itemStack1.getType().getItem()) {
                                collection.setCollected(collection.getCollected() + itemStack1.getItemStack().getAmount(), event.getPlayer());
                            }
                        }
                    }
                    event.getPlayer().getPlayer().getInventory().addItem(itemStack);
                }
            }

            System.out.println(event.getDrops().size() + "A / " + Thread.currentThread().getName());
        }
    }


}
