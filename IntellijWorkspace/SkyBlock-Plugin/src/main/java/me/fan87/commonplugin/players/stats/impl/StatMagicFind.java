package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatMagicFind extends SBStat {

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
        return "MAGIC_FIND";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Magic Find increases how many rare items you find.";
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
        return "Magic Find";
    }

    @Override
    public String getIcon() {
        return "✯";
    }

    @Override
    public String getColor() {
        return "§b";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.STICK);
    }

    @Override
    public String getPerPlayerDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
