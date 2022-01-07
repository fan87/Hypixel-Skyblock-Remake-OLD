
////////////////////////////////////////////////////////
//                                                    //
// File generated with Hypixel SkyBlock API           //
// Tool by fan87                                      //
// https://github.com/fan87/Hypixel-Skyblock-Remake   //
//                                                    //
// PLEASE CHECK "GENERATOIN" FILE FOR MORE INFO       //
//                                                    //
//                                                    //
////////////////////////////////////////////////////////


package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;
import org.bukkit.Color;


// Start class: ItemsSHEARS
// Category: SHEARS
// Last Update: 2022-01-07 09:10:25.075206

public class ItemsSHEARS {

    public static SBCustomItem SHEARS;
    public static SBCustomItem ENCHANTED_SHEARS;



    public ItemsSHEARS(SkyBlock skyBlock) {
        SHEARS = new ItemVanilla("SHEARS", "Shears", "", Material.SHEARS, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SHEARS, skyBlock);
        SBItems.registerItem(SHEARS);
        ENCHANTED_SHEARS = new SBCustomItem("ENCHANTED_SHEARS", "Enchanted Shears", "", Material.SHEARS, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.SHEARS, skyBlock);
        SBItems.registerItem(ENCHANTED_SHEARS);


    }

}

