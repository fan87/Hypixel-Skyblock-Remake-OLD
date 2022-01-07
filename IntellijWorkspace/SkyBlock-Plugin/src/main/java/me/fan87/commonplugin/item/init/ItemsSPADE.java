
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


// Start class: ItemsSPADE
// Category: SPADE
// Last Update: 2022-01-07 08:50:37.810112

public class ItemsSPADE {

    public static SBCustomItem GOLD_SPADE;
    public static SBCustomItem STONE_SPADE;
    public static SBCustomItem IRON_SPADE;
    public static SBCustomItem DIAMOND_SPADE;
    public static SBCustomItem WOOD_SPADE;
    public static SBCustomItem FLINT_SHOVEL;
    public static SBCustomItem PROMISING_SPADE;



    public ItemsSPADE(SkyBlock skyBlock) {
        GOLD_SPADE = new ItemVanilla("GOLD_SPADE", "Golden Shovel", "", Material.GOLD_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(GOLD_SPADE);
        STONE_SPADE = new ItemVanilla("STONE_SPADE", "Stone Shovel", "", Material.STONE_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(STONE_SPADE);
        IRON_SPADE = new ItemVanilla("IRON_SPADE", "Iron Shovel", "", Material.IRON_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(IRON_SPADE);
        DIAMOND_SPADE = new ItemVanilla("DIAMOND_SPADE", "Diamond Shovel", "", Material.DIAMOND_SPADE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(DIAMOND_SPADE);
        WOOD_SPADE = new ItemVanilla("WOOD_SPADE", "Wooden Shovel", "", Material.WOOD_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(WOOD_SPADE);
        FLINT_SHOVEL = new SBCustomItem("FLINT_SHOVEL", "Flint Shovel", "", Material.IRON_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(FLINT_SHOVEL);
        PROMISING_SPADE = new SBCustomItem("PROMISING_SPADE", "Promising Shovel", "", Material.IRON_SPADE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(PROMISING_SPADE);


    }

}

