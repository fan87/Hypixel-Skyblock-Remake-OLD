package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.PacketPlayInFlying;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;
import java.util.UUID;

public class SBItemStack {

    @Getter
    private ItemStack itemStack;
    private boolean customized;


    public SBItemStack(ItemStack itemStack) {
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        customized = getNBT().hasKey("ExtraAttributes");
        generateExtraAttributes();
    }

    public NBTItem getNBT() {
        return new NBTItem(getItemStack(), true);
    }

    public boolean shouldGenerateExtraInfo() {
        return getItemStack().getMaxStackSize() == 1;
    }

    protected void generateExtraAttributes() {
        if (!customized) {
            if (shouldGenerateExtraInfo()) {
                NBTCompound extraAttributeCompound = getExtraAttributeCompound();
                extraAttributeCompound.setString("uuid", UUID.randomUUID().toString());
                extraAttributeCompound.setString("id", getItemStack().getType().toString().toUpperCase(Locale.ROOT));
            }
        }
    }

    protected NBTCompound getExtraAttributeCompound() {
        NBTItem nbt = getNBT();
        if (!nbt.hasKey("ExtraAttributes")) {
            return nbt.addCompound("ExtraAttributes");
        }
        return nbt.getCompound("ExtraAttributes");
    }

    protected net.minecraft.server.v1_8_R3.ItemStack asNMSCopy() {
        return CraftItemStack.asNMSCopy(getItemStack());
    }

    public ItemStack getDisplayItemStack() {
        return getItemStack().clone();
    }

}
