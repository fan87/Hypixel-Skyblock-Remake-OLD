

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;

public class ItemsARROW_POISON {

    public static SBCustomItem TWILIGHT_ARROW_POISON;
    public static SBCustomItem TOXIC_ARROW_POISON;



    public ItemsARROW_POISON(SkyBlock skyBlock) {
        TWILIGHT_ARROW_POISON = new SBCustomItem("TWILIGHT_ARROW_POISON", "Twilight Arrow Poison", "", Material.INK_SACK, (short) 5, "", SBCustomItem.Rarity.UNCOMMON, SBCustomItem.Category.ARROW_POISON, skyBlock);
        SBItems.registerItem(TWILIGHT_ARROW_POISON);
        TOXIC_ARROW_POISON = new SBCustomItem("TOXIC_ARROW_POISON", "Toxic Arrow Poison", "", Material.INK_SACK, (short) 10, "", SBCustomItem.Rarity.UNCOMMON, SBCustomItem.Category.ARROW_POISON, skyBlock);
        SBItems.registerItem(TOXIC_ARROW_POISON);


    }

}

