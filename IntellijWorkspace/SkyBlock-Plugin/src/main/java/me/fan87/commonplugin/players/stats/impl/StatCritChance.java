package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatCritChance extends SBStat {

    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "%";
    }

    @Override
    public double getDefaultValue() {
        return 30;
    }

    @Override
    public String getNamespace() {
        return "CRIT_CHANCE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Crit Chance is your chance to deal extra damage on enemies.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Crit Chance by leveling your Combat Skill.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Speed by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Crit Chance";
    }

    @Override
    public String getIcon() {
        return "☣";
    }

    @Override
    public String getColor() {
        return "§9";
    }

    @Override
    public ItemStack getIconItemStack() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", "bff50f62-bbd8-3ea8-9e7f-126e7efe9544");
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0ZjQ5NTM1YTI3NmFhY2M0ZGM4NDEzM2JmZTgxYmU1ZjJhNDc5OWE0YzA0ZDlhNGRkYjcyZDgxOWVjMmIyYiJ9fX0\u003d");
        return item;
    }

    @Override
    public String getPerPlayerDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {

    }
}
