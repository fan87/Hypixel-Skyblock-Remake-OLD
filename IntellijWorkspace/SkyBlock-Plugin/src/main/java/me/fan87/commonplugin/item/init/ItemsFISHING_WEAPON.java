

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;

public class ItemsFISHING_WEAPON {

    public static SBCustomItem PHANTOM_ROD;
    public static SBCustomItem THE_SHREDDER;



    public ItemsFISHING_WEAPON(SkyBlock skyBlock) {
        PHANTOM_ROD = new SBCustomItem("PHANTOM_ROD", "Phantom Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, SBCustomItem.Category.FISHING_WEAPON, skyBlock);
        SBItems.registerItem(PHANTOM_ROD);
        THE_SHREDDER = new SBCustomItem("THE_SHREDDER", "Shredder", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, SBCustomItem.Category.FISHING_WEAPON, skyBlock);
        SBItems.registerItem(THE_SHREDDER);


    }

}

