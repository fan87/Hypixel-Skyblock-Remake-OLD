

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import org.bukkit.Material;

public class ItemsWAND {

    public static SBCustomItem GENERALS_HOPE_OF_THE_RESISTANCE;
    public static SBCustomItem HOPE_OF_THE_RESISTANCE;
    public static SBCustomItem WAND_OF_RESTORATION;
    public static SBCustomItem WAND_OF_ATONEMENT;
    public static SBCustomItem WAND_OF_HEALING;
    public static SBCustomItem GYROKINETIC_WAND;
    public static SBCustomItem WAND_OF_MENDING;
    public static SBCustomItem CELESTE_WAND;
    public static SBCustomItem STARLIGHT_WAND;



    public ItemsWAND(SkyBlock skyBlock) {
        GENERALS_HOPE_OF_THE_RESISTANCE = new SBCustomItem("GENERALS_HOPE_OF_THE_RESISTANCE", "Staff of the Rising Sun", "", Material.DOUBLE_PLANT, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(GENERALS_HOPE_OF_THE_RESISTANCE);
        HOPE_OF_THE_RESISTANCE = new SBCustomItem("HOPE_OF_THE_RESISTANCE", "Staff of the Rising Sun", "", Material.DOUBLE_PLANT, (short) 0, "", SBCustomItem.Rarity.EPIC, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(HOPE_OF_THE_RESISTANCE);
        WAND_OF_RESTORATION = new SBCustomItem("WAND_OF_RESTORATION", "Wand of Restoration", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.EPIC, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(WAND_OF_RESTORATION);
        WAND_OF_ATONEMENT = new SBCustomItem("WAND_OF_ATONEMENT", "Wand of Atonement", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(WAND_OF_ATONEMENT);
        WAND_OF_HEALING = new SBCustomItem("WAND_OF_HEALING", "Wand of Healing", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(WAND_OF_HEALING);
        GYROKINETIC_WAND = new SBCustomItem("GYROKINETIC_WAND", "Gyrokinetic Wand", "", Material.BLAZE_ROD, (short) 0, "", SBCustomItem.Rarity.EPIC, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(GYROKINETIC_WAND);
        WAND_OF_MENDING = new SBCustomItem("WAND_OF_MENDING", "Wand of Mending", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(WAND_OF_MENDING);
        CELESTE_WAND = new SBCustomItem("CELESTE_WAND", "Celeste Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(CELESTE_WAND);
        STARLIGHT_WAND = new SBCustomItem("STARLIGHT_WAND", "Starlight Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, SBCustomItem.Category.WAND, skyBlock);
        SBItems.registerItem(STARLIGHT_WAND);


    }

}

