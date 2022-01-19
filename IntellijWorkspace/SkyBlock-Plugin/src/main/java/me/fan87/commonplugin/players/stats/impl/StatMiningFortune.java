package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatMiningFortune extends SBStat {

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
        return "MINING_FORTUNE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Mining Fortune is the chance to gain double drops from ores, with a chance for triple drops at values greater than 100.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Mining Fortune by upgrading your Heart of the Mountain.";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Mining Fortune by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Mining Fortune";
    }

    @Override
    public String getIcon() {
        return "☘";
    }

    @Override
    public String getColor() {
        return "§6";
    }

    @Override
    public SBPlayerStats.StatType getType() {
        return SBPlayerStats.StatType.MINING_FORTUNE;
    }

    @Override
    public ItemStack getIconItemStack() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", "2e06585a-c6ee-3804-8254-7a6741519978");
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", "ewogICJ0aW1lc3RhbXAiIDogMTYwNzk3MTc1MDk5NSwKICAicHJvZmlsZUlkIiA6ICJhMmY4MzQ1OTVjODk0YTI3YWRkMzA0OTcxNmNhOTEwYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJiUHVuY2giLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjczNTc5NTc1Y2E4OGIzYThhZmUxZWQxODkwN2IzMTI1ZmUwOTg3YjAyYTg4ZWYwZThhMDEwODdjM2QwMjRjNCIKICAgIH0KICB9Cn0\u003d");
        return item;
    }

    @Override
    public String getExampledDescription(SBPlayer player) {
        if (getValue(player) < 100) {
            return "Chance for " + ChatColor.GREEN + "double " + ChatColor.RESET + "drops: " + ChatColor.GREEN + Math.min(100, Math.floor(getValue(player) % 100)) + "%";
        } else {
            return "Chance for " + ChatColor.GREEN + "triple " + ChatColor.RESET + "drops: " + ChatColor.GREEN + Math.min(100, Math.floor(getValue(player) % 100)) + "%";
        }
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
