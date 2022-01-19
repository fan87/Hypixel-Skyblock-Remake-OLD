package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatStrength extends SBStat {
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
        return "STRENGTH";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Strength increases your base melee damage, including punching and weapons.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Strength by leveling your Foraging Skill and contributing to the §dFairy §rin the §2Wilderness§r.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Strength by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Strength";
    }

    @Override
    public String getIcon() {
        return "❁";
    }

    @Override
    public String getColor() {
        return "§c";
    }

    @Override
    public SBPlayerStats.StatType getType() {
        return SBPlayerStats.StatType.STRENGTH;
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.BLAZE_POWDER);
    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        return "Base Damage: " + ChatColor.GREEN + (int) (5 * (1 + getValue(player)/100f));
    }


    @Override
    public void onTick(SBPlayer player) {

    }
}
