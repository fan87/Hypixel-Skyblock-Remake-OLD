

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;

public class ItemsSHEARS {

    public static SBCustomItem SHEARS;
    public static SBCustomItem ENCHANTED_SHEARS;



    public ItemsSHEARS(SkyBlock skyBlock) {
        SHEARS = new ItemVanilla("SHEARS", "Shears", "", Material.SHEARS, (short) 0, "", SBCustomItem.Rarity.COMMON, false, SBCustomItem.Category.SHEARS, skyBlock);
        SBItems.registerItem(SHEARS);
        ENCHANTED_SHEARS = new SBCustomItem("ENCHANTED_SHEARS", "Enchanted Shears", "", Material.SHEARS, (short) 0, "", SBCustomItem.Rarity.COMMON, true, SBCustomItem.Category.SHEARS, skyBlock);
        SBItems.registerItem(ENCHANTED_SHEARS);


    }

}

