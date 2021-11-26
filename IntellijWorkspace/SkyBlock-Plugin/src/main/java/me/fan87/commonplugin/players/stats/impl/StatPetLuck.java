package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatPetLuck extends SBStat {

    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "";
    }

    @Override
    public double getDefaultValue() {
        return 0;
    }

    @Override
    public String getNamespace() {
        return "PET_LUCK";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Pet Luck increases how many pets you find and gives you better luck when crafting pets.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Pet Luck by leveling your Taming Skill.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Pet Luck by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Pet Luck";
    }

    @Override
    public String getIcon() {
        return "♣";
    }

    @Override
    public String getColor() {
        return "§d";
    }

    @Override
    public ItemStack getIconItemStack() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", "462d81ae-7f76-354c-b29e-5250f8ae54cc");
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTNjOGFhM2ZkZTI5NWZhOWY5YzI3ZjczNGJkYmFiMTFkMzNhMmU0M2U4NTVhY2NkNzQ2NTM1MjM3NzQxM2IifX19");
        return item;
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
