package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatBonusAttackSpeed extends SBStat {
    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "%";
    }

    @Override
    public double getDefaultValue() {
        return 0;
    }

    @Override
    public String getNamespace() {
        return "BONUS_ATTACK_SPEED";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Bonus Attack Speed decreases the time between hits on your opponent.";
    }

    @Override
    public String getBaseDescription() {
        return "All players have the same base bonus speed.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Bonus Attack Speed by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Bonus Attack Speed";
    }

    @Override
    public String getIcon() {
        return "⚔";
    }

    @Override
    public String getColor() {
        return "§e";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.GOLD_AXE);
    }

    @Override
    public void onTick(SBPlayer player) {

    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        if (getValue() != getDefaultValue()) {
            return "You now attack " + ChatColor.GREEN + Math.floor(getValue()) + "% " + ChatColor.RESET + " faster!";
        }
        return "";
    }
}
