package me.fan87.commonplugin.players.stats.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatSeaCreatureChance extends SBStat {

    @Override
    public String getValueDisplay(double value) {
        return (int) Math.round(value) + "%";
    }

    @Override
    public double getDefaultValue() {
        return 20;
    }

    @Override
    public String getNamespace() {
        return "SEA_CREATURE_CHANCE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Sea Creature Chance is your chance to catch Sea Creatures while fishing.";
    }

    @Override
    public String getBaseDescription() {
        return "All players have the same base chance.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Sea Creature Chance by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Sea Creature Chance";
    }

    @Override
    public String getIcon() {
        return "α";
    }

    @Override
    public String getColor() {
        return "§3";
    }

    @Override
    public ItemStack getIconItemStack() {
        return new ItemStack(Material.PRISMARINE_CRYSTALS);
    }

    @Override
    public String getPerPlayerDescription(SBPlayer player) {
        return "";
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
