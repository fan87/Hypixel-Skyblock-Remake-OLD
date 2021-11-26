package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

public class StatFerocity extends SBStat {

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
        return "FEROCITY";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Ferocity grants percent chance to double-strike enemies. Increments of 100 increases the base number of strikes.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Ferocity by exploring the world!";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Ferocity by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Ferocity";
    }

    @Override
    public String getIcon() {
        return "⫽";
    }

    @Override
    public String getColor() {
        return "§c";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new Dye(DyeColor.RED).toItemStack();
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
