package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatHealth extends SBStat {


    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "";
    }

    @Override
    public double getDefaultValue() {
        return 100;
    }

    @Override
    public String getNamespace() {
        return "HEALTH";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Health is your total maximum health. Your natural regeneration gives " + ChatColor.GREEN + Math.round(getRegenAmount(player)*10f)/10f + " HP " + ChatColor.RESET + "every " + ChatColor.GREEN + "2s.";
    }

    public double getRegenAmount(SBPlayer player) {
        return getValue(player)*0.015;
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Health by leveling your Farming and Fishing Skills and contributing to the " + ChatColor.LIGHT_PURPLE + "Fairy " + ChatColor.RESET + "in the " + ChatColor.DARK_GREEN + "Wilderness" + ChatColor.RESET + ".";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Health by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Health";
    }

    @Override
    public String getIcon() {
        return "❤";
    }

    @Override
    public String getColor() {
        return ChatColor.RED + "";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.GOLDEN_APPLE);
    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {
        player.getPlayer().setHealthScaled(true);
        player.getPlayer().setHealthScale(20 + (int) ((getValue(player) - 100)/100f)*2);
        player.getPlayer().setMaxHealth(getValue(player));
    }
}
