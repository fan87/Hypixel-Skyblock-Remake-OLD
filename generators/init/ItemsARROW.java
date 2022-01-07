
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


// Start class: ItemsARROW
// Category: ARROW
// Last Update: 2022-01-07 09:10:25.075644

public class ItemsARROW {

    public static SBCustomItem ARROW;
    public static SBCustomItem REDSTONE_TIPPED_ARROW;
    public static SBCustomItem GOLD_TIPPED_ARROW;
    public static SBCustomItem BOUNCY_ARROW;
    public static SBCustomItem ICY_ARROW;
    public static SBCustomItem EXPLOSIVE_ARROW;
    public static SBCustomItem ARMORSHRED_ARROW;
    public static SBCustomItem EMERALD_TIPPED_ARROW;
    public static SBCustomItem GLUE_ARROW;
    public static SBCustomItem REINFORCED_IRON_ARROW;
    public static SBCustomItem NANSORB_ARROW;



    public ItemsARROW(SkyBlock skyBlock) {
        ARROW = new ItemVanilla("ARROW", "Flint Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(ARROW);
        REDSTONE_TIPPED_ARROW = new SBCustomItem("REDSTONE_TIPPED_ARROW", "Redstone-tipped Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(REDSTONE_TIPPED_ARROW);
        GOLD_TIPPED_ARROW = new SBCustomItem("GOLD_TIPPED_ARROW", "Gold-tipped Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(GOLD_TIPPED_ARROW);
        BOUNCY_ARROW = new SBCustomItem("BOUNCY_ARROW", "Bouncy Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(BOUNCY_ARROW);
        ICY_ARROW = new SBCustomItem("ICY_ARROW", "Icy Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(ICY_ARROW);
        EXPLOSIVE_ARROW = new SBCustomItem("EXPLOSIVE_ARROW", "Explosive Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(EXPLOSIVE_ARROW);
        ARMORSHRED_ARROW = new SBCustomItem("ARMORSHRED_ARROW", "Armorshred Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(ARMORSHRED_ARROW);
        EMERALD_TIPPED_ARROW = new SBCustomItem("EMERALD_TIPPED_ARROW", "Emerald-tipped Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(EMERALD_TIPPED_ARROW);
        GLUE_ARROW = new SBCustomItem("GLUE_ARROW", "Glue Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(GLUE_ARROW);
        REINFORCED_IRON_ARROW = new SBCustomItem("REINFORCED_IRON_ARROW", "Reinforced Iron Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(REINFORCED_IRON_ARROW);
        NANSORB_ARROW = new SBCustomItem("NANSORB_ARROW", "Nansorb Arrow", "", Material.ARROW, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.ARROW, skyBlock);
        SBItems.registerItem(NANSORB_ARROW);


    }

}

