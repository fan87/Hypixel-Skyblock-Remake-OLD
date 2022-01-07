
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


// Start class: ItemsBAIT
// Category: BAIT
// Last Update: 2022-01-07 09:10:25.079613

public class ItemsBAIT {

    public static SBCustomItem SHARK_BAIT;
    public static SBCustomItem FISH_BAIT;
    public static SBCustomItem MINNOW_BAIT;
    public static SBCustomItem SPOOKY_BAIT;
    public static SBCustomItem DARK_BAIT;
    public static SBCustomItem LIGHT_BAIT;
    public static SBCustomItem CARROT_BAIT;
    public static SBCustomItem BLESSED_BAIT;
    public static SBCustomItem WHALE_BAIT;
    public static SBCustomItem ICE_BAIT;
    public static SBCustomItem SPIKED_BAIT;



    public ItemsBAIT(SkyBlock skyBlock) {
        SHARK_BAIT = new SBCustomItem("SHARK_BAIT", "Shark Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRmZjkwNDEyNGVmZTQ4NmIzYTU0MjYxZGJiODA3MmIwYTRlMTE2MTVhZDhkNzM5NGQ4MTRlMGU4YzhlZjllYiJ9fX0=", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(SHARK_BAIT);
        FISH_BAIT = new SBCustomItem("FISH_BAIT", "Fish Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjliYjE4ZTFjZmU2ZWRmMDM1MzVlNWZkYzY0ODJlMDlhN2ZkZjk1MTI1Yzg2MTEyNjliOWRlOWQ1NDcxNWI5ZCJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(FISH_BAIT);
        MINNOW_BAIT = new SBCustomItem("MINNOW_BAIT", "Minnow Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY5MWExNDg2N2VmOTg2Nzg3MmRjZGM4YzU0ZTNkNGNmYjVlNTI1ZGNjZmI3Zjk5YTczMTRhNDVmYWViMzAxZSJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(MINNOW_BAIT);
        SPOOKY_BAIT = new SBCustomItem("SPOOKY_BAIT", "Spooky Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTEwNzRiYTc5NjE2YzdkOGNmOGUzMzg0OTAzOWY2NzQxMGEyZjdjOWNlNzkzZDQ0N2UyMWY1YWEyNGQ1MDEwOCJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(SPOOKY_BAIT);
        DARK_BAIT = new SBCustomItem("DARK_BAIT", "Dark Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzMzYzUxNmYzZjM4MDkxNmQ0MWE5MzU1YTMxOWY4NTk0M2FhY2M4YTljMWI0YTEzODAxNzQ4NGI2MTExNGY2OCJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(DARK_BAIT);
        LIGHT_BAIT = new SBCustomItem("LIGHT_BAIT", "Light Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM2YTlhZGQyNTY0NWJmY2MzNzdjMjVlZjBjMmU5OTAxZDE5NDkzYzNlOTgxZWJjNmJhN2ExYTFiNjQ2NmNlNCJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(LIGHT_BAIT);
        CARROT_BAIT = new SBCustomItem("CARROT_BAIT", "Carrot Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQzYTZiZDk4YWMxODMzYzY2NGM0OTA5ZmY4ZDJkYzYyY2U4ODdiZGNmM2NjNWIzODQ4NjUxYWU1YWY2YiJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(CARROT_BAIT);
        BLESSED_BAIT = new SBCustomItem("BLESSED_BAIT", "Blessed Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA2MGE0NjQ5N2NlZTc2MTEzNDZjOWRkOGViOGY1ZGU0NDgzZGUyNTkxNDcyMTEzNTM4NDgzNTZkZTE1ZDE5In19fQ==", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(BLESSED_BAIT);
        WHALE_BAIT = new SBCustomItem("WHALE_BAIT", "Whale Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzNhYTcxNjc2ZTgxZmI1M2EwNDBkZmRjYTNlNWI0N2Q1M2U2ZWZkNjY1ZTY5ZmI0Mzk3NzhlOGM0ZWZiMWNjIn19fQ==", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(WHALE_BAIT);
        ICE_BAIT = new SBCustomItem("ICE_BAIT", "Ice Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTExMzY2MTZkOGM0YTg3YTU0Y2U3OGE5N2I1NTE2MTBjMmIyYzhmNmQ0MTBiYzM4Yjg1OGY5NzRiMTEzYjIwOCJ9fX0=", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(ICE_BAIT);
        SPIKED_BAIT = new SBCustomItem("SPIKED_BAIT", "Spiked Bait", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTEyYmM1ZWYyNmYzNjdiZGJkNzExNmU4MGZkZTRhZjE4MjNjMGU5MzYwYWZhYjAyYTlkM2E0Y2I2ZDVmY2Q4MiJ9fX0=", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.BAIT, skyBlock);
        SBItems.registerItem(SPIKED_BAIT);


    }

}

