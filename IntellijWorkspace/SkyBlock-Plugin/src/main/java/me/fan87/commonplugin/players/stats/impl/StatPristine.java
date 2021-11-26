package me.fan87.commonplugin.players.stats.impl;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class StatPristine extends SBStat {

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
        return "PRISTINE";
    }

    @Override
    public String getDescription(SBPlayer player) {
        return "Pristine is the chance to increase the quality of a Gemstone when its dropped.";
    }

    @Override
    public String getBaseDescription() {
        return "Increase your base Pristine by exploring the world!";
    }

    @Override
    public String getBonusDescription() {
        return "Increase your bonus Pristine by equipping items and armor, and storing accessories in your inventory.";
    }

    @Override
    public String getName() {
        return "Pristine";
    }

    @Override
    public String getIcon() {
        return "✧";
    }

    @Override
    public String getColor() {
        return "§5";
    }

    @Override
    public ItemStack getIconItemStack() {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", "08e13e8e-2831-3e9c-a7b0-d9506b0f65b0");
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", "ewogICJ0aW1lc3RhbXAiIDogMTYyNDk3OTI3NDk3OSwKICAicHJvZmlsZUlkIiA6ICJiNjM2OWQ0MzMwNTU0NGIzOWE5OTBhODYyNWY5MmEwNSIsCiAgInByb2ZpbGVOYW1lIiA6ICJCb2JpbmhvXyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9kODg2ZTBmNDExODViMThhM2FmZDg5NDg4ZDJlZTRjYWEwNzM1MDA5MjQ3Y2NjZjAzOWNlZDZhZWQ3NTJmZjFhIgogICAgfQogIH0KfQ");
        return item;
    }

    @Override
    public void onTick(SBPlayer player) {
    }
}
