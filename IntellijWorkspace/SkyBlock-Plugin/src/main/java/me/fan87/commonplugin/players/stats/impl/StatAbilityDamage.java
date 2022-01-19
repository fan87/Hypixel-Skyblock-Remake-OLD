package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatAbilityDamage extends SBStat {

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
        return "ABILITY_DAMAGE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Ability Damage is the percentage of bonus damage applied to your spells!";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Ability Damage by leveling your Enchanting Skill.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Ability Damage by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Ability Damage";
    }

    @Override
    public String getIcon() {
        return "๑";
    }

    @Override
    public String getColor() {
        return "§c";
    }

    @Override
    public SBPlayerStats.StatType getType() {
        return SBPlayerStats.StatType.ABILITY_DAMAGE;
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.BEACON);
    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {

    }
}
