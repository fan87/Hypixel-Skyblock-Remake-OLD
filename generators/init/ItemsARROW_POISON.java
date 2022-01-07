
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


// Start class: ItemsARROW_POISON
// Category: ARROW_POISON
// Last Update: 2022-01-07 09:10:25.079800

public class ItemsARROW_POISON {

    public static SBCustomItem TWILIGHT_ARROW_POISON;
    public static SBCustomItem TOXIC_ARROW_POISON;



    public ItemsARROW_POISON(SkyBlock skyBlock) {
        TWILIGHT_ARROW_POISON = new SBCustomItem("TWILIGHT_ARROW_POISON", "Twilight Arrow Poison", "", Material.INK_SACK, (short) 5, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.ARROW_POISON, skyBlock);
        SBItems.registerItem(TWILIGHT_ARROW_POISON);
        TOXIC_ARROW_POISON = new SBCustomItem("TOXIC_ARROW_POISON", "Toxic Arrow Poison", "", Material.INK_SACK, (short) 10, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.ARROW_POISON, skyBlock);
        SBItems.registerItem(TOXIC_ARROW_POISON);


    }

}

