package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LangUtils;
import me.fan87.commonplugin.utils.LoreUtils;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class SBItemStack {

    @Getter
    private ItemStack itemStack;
    private boolean customized;
    


    public SBItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        customized = getNBT().hasKey("ExtraAttributes");
        updateItem();
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

}
