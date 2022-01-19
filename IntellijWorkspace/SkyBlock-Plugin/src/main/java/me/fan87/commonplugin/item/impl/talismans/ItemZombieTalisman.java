package me.fan87.commonplugin.item.impl.talismans;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class ItemZombieTalisman extends SBCustomItem {
    @Override
    public boolean canBeReforged() {
        return true;
    }

    public ItemZombieTalisman(SkyBlock skyBlock) {
        super("ZOMBIE_TALISMAN",
                "Zombie Talisman",
                "Reduce the damage taken from Zombies by " + ChatColor.GREEN + "5%" + ChatColor.GRAY + ".",
                Material.SKULL_ITEM,
                (short) 2,
                Rarity.COMMON,
                Category.ACCESSORY,
                50,
                skyBlock,
                RecipeCategory.SPECIAL);
    }

    

}
