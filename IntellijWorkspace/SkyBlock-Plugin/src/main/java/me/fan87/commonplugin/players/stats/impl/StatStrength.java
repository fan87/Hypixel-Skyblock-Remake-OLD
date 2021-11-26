package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
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
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.BLAZE_POWDER);
    }

    @Override
    public void onTick(SBPlayer player) {
        throw new NotImplementedException("Strength Stat is not implemented yet!");
    }
}
