
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
// Last Update: 2022-01-10 17:27:50.174864

public class ItemsSPADE {

    public static SBCustomItem FLINT_SHOVEL;
    public static SBCustomItem PROMISING_SPADE;



    public ItemsSPADE(SkyBlock skyBlock) {
        FLINT_SHOVEL = new SBCustomItem("FLINT_SHOVEL", "Flint Shovel", "", Material.IRON_SPADE, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(FLINT_SHOVEL);
        PROMISING_SPADE = new SBCustomItem("PROMISING_SPADE", "Promising Shovel", "", Material.IRON_SPADE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SPADE, skyBlock);
        SBItems.registerItem(PROMISING_SPADE);


    }

}

