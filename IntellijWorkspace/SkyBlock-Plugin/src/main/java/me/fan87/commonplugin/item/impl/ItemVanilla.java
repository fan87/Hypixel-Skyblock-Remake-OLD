package me.fan87.commonplugin.item.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import org.bukkit.Color;
import org.bukkit.Material;

public class ItemVanilla extends SBCustomItem {
    public ItemVanilla(String namespace, String displayName, String description, Material material, SkyBlock skyBlock) {
        super(namespace, displayName, description, material, skyBlock);
    }

    public ItemVanilla(String namespace, String displayName, String description, Material material, short damage, String skin, Rarity rarity, boolean glowing, Color color, Category category, SkyBlock skyBlock) {
        super(namespace, displayName, description, material, damage, skin, rarity, glowing, color, category, skyBlock);
    }

    public ItemVanilla(String namespace, String displayName, Material material, SkyBlock skyBlock) {
        super(namespace, displayName, material, skyBlock);
    }
}
