
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


// Start class: ItemsDRILL
// Category: DRILL
// Last Update: 2022-01-07 09:10:25.079946

public class ItemsDRILL {

    public static SBCustomItem GEMSTONE_DRILL_2;
    public static SBCustomItem MITHRIL_DRILL_1;
    public static SBCustomItem GEMSTONE_DRILL_1;
    public static SBCustomItem MITHRIL_DRILL_2;
    public static SBCustomItem DIVAN_DRILL;
    public static SBCustomItem GEMSTONE_DRILL_3;
    public static SBCustomItem GEMSTONE_DRILL_4;
    public static SBCustomItem TITANIUM_DRILL_3;
    public static SBCustomItem TITANIUM_DRILL_1;
    public static SBCustomItem TITANIUM_DRILL_4;
    public static SBCustomItem TITANIUM_DRILL_2;



    public ItemsDRILL(SkyBlock skyBlock) {
        GEMSTONE_DRILL_2 = new SBCustomItem("GEMSTONE_DRILL_2", "Gemstone Drill LT-522", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(GEMSTONE_DRILL_2);
        MITHRIL_DRILL_1 = new SBCustomItem("MITHRIL_DRILL_1", "Mithril Drill SX-R226", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(MITHRIL_DRILL_1);
        GEMSTONE_DRILL_1 = new SBCustomItem("GEMSTONE_DRILL_1", "Ruby Drill TX-15", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(GEMSTONE_DRILL_1);
        MITHRIL_DRILL_2 = new SBCustomItem("MITHRIL_DRILL_2", "Mithril Drill SX-R326", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(MITHRIL_DRILL_2);
        DIVAN_DRILL = new SBCustomItem("DIVAN_DRILL", "Divan's Drill", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.MYTHIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(DIVAN_DRILL);
        GEMSTONE_DRILL_3 = new SBCustomItem("GEMSTONE_DRILL_3", "Topaz Drill KGR-12", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(GEMSTONE_DRILL_3);
        GEMSTONE_DRILL_4 = new SBCustomItem("GEMSTONE_DRILL_4", "Jasper Drill X", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(GEMSTONE_DRILL_4);
        TITANIUM_DRILL_3 = new SBCustomItem("TITANIUM_DRILL_3", "Titanium Drill DR-X555", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(TITANIUM_DRILL_3);
        TITANIUM_DRILL_1 = new SBCustomItem("TITANIUM_DRILL_1", "Titanium Drill DR-X355", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(TITANIUM_DRILL_1);
        TITANIUM_DRILL_4 = new SBCustomItem("TITANIUM_DRILL_4", "Titanium Drill DR-X655", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(TITANIUM_DRILL_4);
        TITANIUM_DRILL_2 = new SBCustomItem("TITANIUM_DRILL_2", "Titanium Drill DR-X455", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.DRILL, skyBlock);
        SBItems.registerItem(TITANIUM_DRILL_2);


    }

}

