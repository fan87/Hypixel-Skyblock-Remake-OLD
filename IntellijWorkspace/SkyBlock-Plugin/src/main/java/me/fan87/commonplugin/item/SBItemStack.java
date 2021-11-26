package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

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
        if (type.type == SBMaterial.ItemType.CUSTOM) {
            type.item.updatePlayerStats(player, inventoryIndex);
        }
    }

    private void updateItem() {
        generateExtraAttributes();
    }

    public SBMaterial getType() {
        Material material = Material.getMaterial(getItemID());
        if (material != null) {
            return new SBMaterial(material, null, SBMaterial.ItemType.VANILLA);
        } else {
            SBCustomItem item = SBItems.getItem(getItemID());
            if (item != null) return new SBMaterial(null, item, SBMaterial.ItemType.CUSTOM);
            return new SBMaterial(null, null, SBMaterial.ItemType.UNKNOWN);
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
            extraAttributeCompound.setString("id", getItemStack().getType().toString());
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

    public ItemStack getDisplayItemStack() {
        return getItemStack().clone();
    }

}
