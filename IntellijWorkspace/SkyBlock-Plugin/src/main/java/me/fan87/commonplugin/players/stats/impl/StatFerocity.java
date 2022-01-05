package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
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
        return new Dye(DyeColor.RED).toItemStack(1);
    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        return "Base extra strikes: " + getColor() + Math.floor(getValue()) + "\n" + ChatColor.RESET +
                "Chance for " + Math.max(1, getValue()/100) + " more: " + getColor() + Math.max(Math.floor(getValue() % 100), 0) + "%";
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
