package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatSpeed extends SBStat {

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
        return "SPEED";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Speed increases your walk speed.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Speed by contributing to the §dFairy §rin the §2Wilderness§r.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Speed by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Speed";
    }

    @Override
    public String getIcon() {
        return "✦";
    }

    @Override
    public String getColor() {
        return ChatColor.WHITE + "";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.SUGAR);
    }

    @Override
    public double getTotalBonusValue() {
        double totalBonusValue = super.getTotalBonusValue();
        if (getBaseValue() + totalBonusValue > 500) {
            totalBonusValue = Math.max(0, 500 - getBaseValue());
        }
        return totalBonusValue;
    }

    @Override
    public double getBaseValue() {
        double baseValue = super.getBaseValue();
        return Math.max(0, Math.min(500, baseValue));
    }

    @Override
    public void onTick(SBPlayer player) {
        player.getPlayer().setWalkSpeed(((float) getValue())/500f);
    }
}
