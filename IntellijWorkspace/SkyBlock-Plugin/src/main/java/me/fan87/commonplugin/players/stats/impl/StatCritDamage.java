package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatCritDamage extends SBStat {

    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "%";
    }

    @Override
    public double getDefaultValue() {
        return 50;
    }

    @Override
    public String getNamespace() {
        return "CRIT_DAMAGE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Crit Damage is the amount of extra damage you deal when landing a critical hit.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Crit Damage by exploring the world!";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Crit Damage by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Crit Damage";
    }

    @Override
    public String getIcon() {
        return "☠";
    }

    @Override
    public String getColor() {
        return "§9";
    }

    @Override
    public ItemStack getIconItemStack() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", "930d32b1-d7cc-3823-b44b-59b4220a5111");
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGRhZmIyM2VmYzU3ZjI1MTg3OGU1MzI4ZDExY2IwZWVmODdiNzljODdiMjU0YTdlYzcyMjk2ZjkzNjNlZjdjIn19fQ\u003d\u003d");
        return item;
    }

    @Override
    public void onTick(SBPlayer player) {

    }
}
