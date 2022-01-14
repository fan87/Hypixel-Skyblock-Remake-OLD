
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
// Last Update: 2022-01-14 03:34:12.705527

public class ItemsSHEARS {

    public static SBCustomItem ENCHANTED_SHEARS;



    public ItemsSHEARS(SkyBlock skyBlock) {
        ENCHANTED_SHEARS = new SBCustomItem("ENCHANTED_SHEARS", "Enchanted Shears", "", Material.SHEARS, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.SHEARS, 2, skyBlock);
        SBItems.registerItem(ENCHANTED_SHEARS);


    }

}

