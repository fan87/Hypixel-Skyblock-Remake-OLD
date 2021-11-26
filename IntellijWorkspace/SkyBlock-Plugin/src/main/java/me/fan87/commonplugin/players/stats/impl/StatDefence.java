package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatDefence extends SBStat {

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
        return "DEFENCE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Defense reduces the damage that you take from enemies.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Defense by leveling your Mining Skill and contributing to the " + ChatColor.LIGHT_PURPLE + "Fairy " + ChatColor.RESET + "in the " + ChatColor.DARK_GREEN + "Wilderness" + ChatColor.RESET + ".";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Defense by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Defence";
    }

    @Override
    public String getIcon() {
        return "❈";
    }

    @Override
    public String getColor() {
        return ChatColor.GREEN + "";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }

    @Override
    public String getPerPlayerDescription(SBPlayer player) {
        StatHealth health = player.getStats().getHealth();
        return "Damage Reduction: " + ChatColor.GREEN + Math.round(getDamageModifier()*1000f)/10f + "%\n" + ChatColor.RESET +
                "Effective Health: " + ChatColor.RED + Math.round(health.getValue()*getDamageModifier()*10f)/10f + health.getIcon();
    }

    @Override
    public void onTick(SBPlayer player) {
        
    }

    public double getDamageModifier() {
        return getValue()/(getValue() + 100);
    }
}
