

// File generated with Hypixel SkyBlock API
// Tool by fan87

package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import org.bukkit.Material;

public class ItemsGAUNTLET {

    public static SBCustomItem GEMSTONE_GAUNTLET;



    public ItemsGAUNTLET(SkyBlock skyBlock) {
        GEMSTONE_GAUNTLET = new SBCustomItem("GEMSTONE_GAUNTLET", "Gemstone Gauntlet", "", Material.SKULL_ITEM, (short) 3, "ewogICJ0aW1lc3RhbXAiIDogMTYxODUyMTY2MzY1NCwKICAicHJvZmlsZUlkIiA6ICIxZDUyMzNkMzg4NjI0YmFmYjAwZTMxNTBhN2FhM2E4OSIsCiAgInByb2ZpbGVOYW1lIiA6ICIwMDAwMDAwMDAwMDAwMDBKIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdiZjAxYzE5OGY2ZTE2OTY1ZTIzMDIzNWNkMjJhNWE5ZjRhNDBlNDA5NDEyMzQ0Nzg5NDhmZjlhNTZlNTE3NzUiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ==", SBCustomItem.Rarity.LEGENDARY, false, SBCustomItem.Category.GAUNTLET, skyBlock);
        SBItems.registerItem(GEMSTONE_GAUNTLET);


    }

}

