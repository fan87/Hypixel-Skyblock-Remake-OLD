package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatMiningSpeed extends SBStat {

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
        return "MINING_SPEED";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Increases the speed of breaking mining blocks.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Magic Find by collecting unique pets.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Magic Find by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Mining Speed";
    }

    @Override
    public String getIcon() {
        return "⸕";
    }

    @Override
    public String getColor() {
        return "§6";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.DIAMOND_PICKAXE);
    }

    @Override
    public String getPerPlayerDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {
    }


}
