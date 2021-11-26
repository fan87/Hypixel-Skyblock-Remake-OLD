package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.apache.commons.lang.NotImplementedException;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatIntelligence extends SBStat {

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
        return "INTELLIGENCE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Intelligence increases both your Mana Pool and the damage of your magical items.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base ntelligence by leveling your Enchanting and Alchemy Skills.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Intelligence by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Intelligence";
    }

    @Override
    public String getIcon() {
        return "✎";
    }

    @Override
    public String getColor() {
        return "§b";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.ENCHANTED_BOOK);
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
