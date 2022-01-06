

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;

public class ItemsFISHING_ROD {

    public static SBCustomItem FISHING_ROD;
    public static SBCustomItem ROD_OF_THE_SEA;
    public static SBCustomItem WINTER_ROD;
    public static SBCustomItem CHAMP_ROD;
    public static SBCustomItem YETI_ROD;
    public static SBCustomItem LEGEND_ROD;
    public static SBCustomItem ICE_ROD;
    public static SBCustomItem AUGER_ROD;
    public static SBCustomItem CHALLENGE_ROD;
    public static SBCustomItem SPEEDSTER_ROD;
    public static SBCustomItem FARMER_ROD;
    public static SBCustomItem SPONGE_ROD;
    public static SBCustomItem PRISMARINE_ROD;



    public ItemsFISHING_ROD(SkyBlock skyBlock) {
        FISHING_ROD = new ItemVanilla("FISHING_ROD", "Fishing Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(FISHING_ROD);
        ROD_OF_THE_SEA = new SBCustomItem("ROD_OF_THE_SEA", "Rod of the Sea", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(ROD_OF_THE_SEA);
        WINTER_ROD = new SBCustomItem("WINTER_ROD", "Winter Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(WINTER_ROD);
        CHAMP_ROD = new SBCustomItem("CHAMP_ROD", "Rod of Champions", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(CHAMP_ROD);
        YETI_ROD = new SBCustomItem("YETI_ROD", "Yeti Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(YETI_ROD);
        LEGEND_ROD = new SBCustomItem("LEGEND_ROD", "Rod of Legends", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(LEGEND_ROD);
        ICE_ROD = new SBCustomItem("ICE_ROD", "Ice Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(ICE_ROD);
        AUGER_ROD = new SBCustomItem("AUGER_ROD", "Auger Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(AUGER_ROD);
        CHALLENGE_ROD = new SBCustomItem("CHALLENGE_ROD", "Challenging Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(CHALLENGE_ROD);
        SPEEDSTER_ROD = new SBCustomItem("SPEEDSTER_ROD", "Speedster Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(SPEEDSTER_ROD);
        FARMER_ROD = new SBCustomItem("FARMER_ROD", "Farmer's Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(FARMER_ROD);
        SPONGE_ROD = new SBCustomItem("SPONGE_ROD", "Sponge Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(SPONGE_ROD);
        PRISMARINE_ROD = new SBCustomItem("PRISMARINE_ROD", "Prismarine Rod", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, SBCustomItem.Category.FISHING_ROD, skyBlock);
        SBItems.registerItem(PRISMARINE_ROD);


    }

}

