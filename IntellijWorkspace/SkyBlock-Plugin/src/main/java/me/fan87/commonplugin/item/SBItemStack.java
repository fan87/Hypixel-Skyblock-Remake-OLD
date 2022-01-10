package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.enchantment.SBEnchantments;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LangUtils;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.SBNamespace;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SBItemStack {

    @Getter
    private ItemStack itemStack;
    private boolean customized;

    private final Map<SBEnchantment, Integer> enchantments = new HashMap<>();

    public SBItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        customized = getNBT().hasKey("ExtraAttributes");
        updateItem();
        NBTCompound enchantments = getExtraAttributeCompound().getCompound("enchantments");
        for (String key : enchantments.getKeys()) {
            SBEnchantment enchantment = SBEnchantments.getEnchantment(SBNamespace.fromString(key));
            if (enchantment == null) continue;
            this.enchantments.put(enchantment, enchantments.getInteger(key));
        }
    }

    public boolean canBeUsedForCrafting() {
        return true;
    }

    public SBItemStack(SBCustomItem item) {
        this(item, 1);
    }

    public SBItemStack(SBCustomItem item, int amount) {
        ItemStack itemStack = item.newItemStack();
        itemStack.setAmount(amount);
        this.itemStack = itemStack;
    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {
        SBMaterial type = getType();
        if (type.getType() == SBMaterial.ItemType.CUSTOM) {
            type.getItem().updatePlayerStats(player, inventoryIndex);
        }
    }

    private void updateItem() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        generateExtraAttributes();
    }

    public SBMaterial getType() {
        Material material = Material.getMaterial(getItemID());

        SBCustomItem item = SBItems.getItem(getItemID());
        if (item != null) {
            return new SBMaterial(null, item, SBMaterial.ItemType.CUSTOM);
        } else {
            if (material != null) {
                return new SBMaterial(material, null, SBMaterial.ItemType.VANILLA);
            } else {
                return new SBMaterial(null, null, SBMaterial.ItemType.UNKNOWN);
            }
        }
    }

    public NBTItem getNBT() {
        return new NBTItem(getItemStack(), true);
    }

    public String getItemID() {
        generateExtraAttributes();
        String id = getExtraAttributeCompound().getString("id");
        return id;
    }

    public boolean shouldGenerateExtraInfo() {
        return getItemStack().getMaxStackSize() == 1;
    }

    protected void generateExtraAttributes() {
        if (!customized) {
            NBTCompound extraAttributeCompound = getExtraAttributeCompound();
            ItemVanilla item = SBItems.getVanillaItem(getItemStack().getType(), getItemStack().getDurability());
            extraAttributeCompound.setString("id", item ==null?getItemStack().getType().toString():item.getNamespace());
            if (shouldGenerateExtraInfo()) {
                extraAttributeCompound.setString("uuid", UUID.randomUUID().toString());
            }
        }
    }

    public NBTCompound getExtraAttributeCompound() {
        NBTItem nbt = getNBT();
        if (!nbt.hasKey("ExtraAttributes")) {
            return nbt.addCompound("ExtraAttributes");
        }
        return nbt.getCompound("ExtraAttributes");
    }

    public net.minecraft.server.v1_8_R3.ItemStack asNMSCopy() {
        return CraftItemStack.asNMSCopy(getItemStack());
    }

    public String getVanillaItemName() {
        net.minecraft.server.v1_8_R3.ItemStack itemStack = asNMSCopy();
        return LangUtils.getName(itemStack.getItem().k(itemStack) + ".name");
    }

    public ItemStack getDisplayItemStack() {
        ItemStack clone = getItemStack().clone();
        ItemMeta itemMeta = clone.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        String displayName = itemMeta.getDisplayName();
        if (displayName != null && !displayName.equals("")) {
            return clone;
        }
        SBMaterial type = getType();
        if (type.getType() == SBMaterial.ItemType.CUSTOM) {
            itemMeta.setDisplayName(type.getItem().getRarity().getColor() + type.getItem().getDisplayName());
            itemMeta.setLore(type.getItem().getLores());
        }
        if (type.getType() == SBMaterial.ItemType.UNKNOWN) {
            itemMeta.setDisplayName(itemMeta.getDisplayName() + ChatColor.BLACK + " (Deprecated)");
        }
        if (type.getType() == SBMaterial.ItemType.VANILLA) {
            EnumItemRarity rarity = asNMSCopy().u();
            SBCustomItem.Rarity realOne = SBCustomItem.Rarity.getRarityByVanillaRarity(rarity);
            itemMeta.setDisplayName(realOne.getColor() + "" + getVanillaItemName());
            itemMeta.setLore(LoreUtils.splitLoreForLine(realOne.getColor().toString() + ChatColor.BOLD + realOne.getName().toUpperCase()));
        }
        itemStack.setItemMeta(itemMeta);
        clone.setItemMeta(itemMeta);
        return clone;
    }

    public void addSafeEnchantment(SBEnchantment enchantment, int level) {
        enchantment = SBEnchantments.getEnchantment(enchantment.getNamespace());

        if (enchantment.getMaxLevel() >= level) {
            addEnchantment(enchantment, level);
            return;
        }
        throw new IllegalArgumentException("Max level of " + enchantment.getNamespace() + " is " + level);
    }

    public void addEnchantment(SBEnchantment enchantment, int level) {
        enchantments.put(enchantment, level);
        saveEnchantments();
    }

    public void clearEnchantment() {
        enchantments.clear();
        saveEnchantments();
    }

    private void saveEnchantments() {
        if (enchantments.size() > 0) {
            NBTCompound enchantments = getExtraAttributeCompound().getOrCreateCompound("enchantments");
            for (SBEnchantment key : this.enchantments.keySet()) {
                enchantments.setInteger(key.getNamespace() + "", this.enchantments.get(key));
            }
        } else {
            if (getExtraAttributeCompound().hasKey("enchantments")) {
                getExtraAttributeCompound().removeKey("enchantments");
            }
        }
    }

    public int getEnchantmentLevel(SBEnchantment enchantment) {
        Integer integer = enchantments.get(enchantment);
        if (integer == null) return 0;
        return integer;
    }

    public boolean isInActive(PlayerInventory inventory, int slot) {
        SBCustomItem item = getType().getItem();
        if (item != null) {
            return item.getCategory().getActiveChecker().isActive(inventory, slot);
        }
        return false;
    }

}
