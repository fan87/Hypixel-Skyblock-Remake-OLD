package me.fan87.commonplugin.enchantment.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.utils.SBNamespace;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class EnchTelekinesis extends SBEnchantment {

    public EnchTelekinesis(SkyBlock skyBlock) {
        super(skyBlock, new SBNamespace("default", "TELEKINESIS"), "Telekinesis", 1);
    }

    @Override
    public String getDescription(ChatColor color, int level) {
        return color + "Block and mob drops go directly into your inventory.";
    }

    @Subscribe(priority = -200)
    public void onBreak(BlockDropEvent event) {
        try {
            if (!event.isCancelled() && event.getPlayer().isEnchantmentActive(this)) {
                ItemStack[] itemStacks = event.getDrops().toArray(new ItemStack[0]);
                for (ItemStack itemStack : itemStacks) {
                    if (event.getPlayer().getPlayer().getInventory().firstEmpty() != -1) {
                        event.getDrops().remove(itemStack);
                        if (itemStack == null || itemStack.getType() == Material.AIR) continue;
                        NBTTagCompound tag = CraftItemStack.asNMSCopy(itemStack).getTag();
                        boolean wasCustomized = false;
                        if (tag != null) wasCustomized = tag.hasKey("ExtraAttributes");
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
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isItemAccepted(SBCustomItem item) {

        SBCustomItem.Category category = item.getCategory();
        return category.isWeapon() || category.isTool();
    }
}
