
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


// Start class: ItemsPET_ITEM
// Category: PET_ITEM
// Last Update: 2022-01-14 03:34:12.697516

public class ItemsPET_ITEM {

    public static SBCustomItem PET_ITEM_COMBAT_SKILL_BOOST_COMMON;
    public static SBCustomItem PET_ITEM_HARDENED_SCALES_UNCOMMON;
    public static SBCustomItem PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON;
    public static SBCustomItem PET_ITEM_ALL_SKILLS_BOOST_EPIC;
    public static SBCustomItem PET_ITEM_BUBBLEGUM;
    public static SBCustomItem PET_ITEM_FARMING_SKILL_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_COMBAT_SKILL_BOOST_EPIC;
    public static SBCustomItem BIGGER_TEETH;
    public static SBCustomItem ALL_SKILLS_SUPER_BOOST;
    public static SBCustomItem PET_ITEM_FARMING_SKILL_BOOST_COMMON;
    public static SBCustomItem PET_ITEM_SPOOKY_CUPCAKE;
    public static SBCustomItem PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON;
    public static SBCustomItem PET_ITEM_VAMPIRE_FANG;
    public static SBCustomItem PET_ITEM_FISHING_SKILL_BOOST_COMMON;
    public static SBCustomItem PET_ITEM_HARDENED_SCALES_COMMON;
    public static SBCustomItem PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON;
    public static SBCustomItem MINOS_RELIC;
    public static SBCustomItem PET_ITEM_FARMING_SKILL_BOOST_RARE;
    public static SBCustomItem PET_ITEM_ALL_SKILLS_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_FORAGING_SKILL_BOOST_EPIC;
    public static SBCustomItem CROCHET_TIGER_PLUSHIE;
    public static SBCustomItem PET_ITEM_TOY_JERRY;
    public static SBCustomItem PET_ITEM_TIER_BOOST;
    public static SBCustomItem ANTIQUE_REMEDIES;
    public static SBCustomItem PET_ITEM_FISHING_SKILL_BOOST_EPIC;
    public static SBCustomItem PET_ITEM_BIG_TEETH_UNCOMMON;
    public static SBCustomItem PET_ITEM_LUCKY_CLOVER;
    public static SBCustomItem PET_ITEM_HARDENED_SCALES_RARE;
    public static SBCustomItem DWARF_TURTLE_SHELMET;
    public static SBCustomItem WASHED_UP_SOUVENIR;
    public static SBCustomItem GOLD_CLAWS;
    public static SBCustomItem PET_ITEM_HARDENED_SCALES_EPIC;
    public static SBCustomItem PET_ITEM_FLYING_PIG;
    public static SBCustomItem PET_ITEM_SHARPENED_CLAWS_LEGENDARY;
    public static SBCustomItem PET_ITEM_SHARPENED_CLAWS_RARE;
    public static SBCustomItem PET_ITEM_QUICK_CLAW;
    public static SBCustomItem SERRATED_CLAWS;
    public static SBCustomItem PET_ITEM_SHARPENED_CLAWS_COMMON;
    public static SBCustomItem PET_ITEM_EXP_SHARE;
    public static SBCustomItem PET_ITEM_IRON_CLAWS_EPIC;
    public static SBCustomItem PET_ITEM_FISHING_SKILL_BOOST_RARE;
    public static SBCustomItem PET_ITEM_SHARPENED_CLAWS_UNCOMMON;
    public static SBCustomItem PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON;
    public static SBCustomItem PET_ITEM_MINING_SKILL_BOOST_UNCOMMON;
    public static SBCustomItem PET_ITEM_MINING_SKILL_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_FORAGING_SKILL_BOOST_COMMON;
    public static SBCustomItem PET_ITEM_FISHING_SKILL_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_SADDLE;
    public static SBCustomItem REAPER_GEM;
    public static SBCustomItem PET_ITEM_ALL_SKILLS_BOOST_RARE;
    public static SBCustomItem PET_ITEM_ALL_SKILLS_BOOST_COMMON;
    public static SBCustomItem PET_ITEM_MINING_SKILL_BOOST_EPIC;
    public static SBCustomItem PET_ITEM_FORAGING_SKILL_BOOST_RARE;
    public static SBCustomItem PET_ITEM_COMBAT_SKILL_BOOST_RARE;
    public static SBCustomItem PET_ITEM_IRON_CLAWS_UNCOMMON;
    public static SBCustomItem PET_ITEM_FORAGING_SKILL_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_BIG_TEETH_RARE;
    public static SBCustomItem PET_ITEM_COMBAT_SKILL_BOOST_LEGENDARY;
    public static SBCustomItem PET_ITEM_BIG_TEETH_COMMON;
    public static SBCustomItem PET_ITEM_SHARPENED_CLAWS_EPIC;
    public static SBCustomItem PET_ITEM_BIG_TEETH_EPIC;
    public static SBCustomItem PET_ITEM_FARMING_SKILL_BOOST_EPIC;
    public static SBCustomItem REINFORCED_SCALES;
    public static SBCustomItem PET_ITEM_TEXTBOOK;
    public static SBCustomItem PET_ITEM_HARDENED_SCALES_LEGENDARY;
    public static SBCustomItem PET_ITEM_IRON_CLAWS_COMMON;
    public static SBCustomItem PET_ITEM_BIG_TEETH_LEGENDARY;
    public static SBCustomItem PET_ITEM_IRON_CLAWS_LEGENDARY;
    public static SBCustomItem PET_ITEM_IRON_CLAWS_RARE;
    public static SBCustomItem PET_ITEM_ALL_SKILLS_BOOST_UNCOMMON;
    public static SBCustomItem PET_ITEM_MINING_SKILL_BOOST_RARE;
    public static SBCustomItem PET_ITEM_MINING_SKILL_BOOST_COMMON;



    public ItemsPET_ITEM(SkyBlock skyBlock) {
        PET_ITEM_COMBAT_SKILL_BOOST_COMMON = new SBCustomItem("PET_ITEM_COMBAT_SKILL_BOOST_COMMON", "Combat Exp Boost", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_COMBAT_SKILL_BOOST_COMMON);
        PET_ITEM_HARDENED_SCALES_UNCOMMON = new SBCustomItem("PET_ITEM_HARDENED_SCALES_UNCOMMON", "Hardened Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_HARDENED_SCALES_UNCOMMON);
        PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON", "Fishing Exp Boost", "", Material.RAW_FISH, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FISHING_SKILL_BOOST_UNCOMMON);
        PET_ITEM_ALL_SKILLS_BOOST_EPIC = new SBCustomItem("PET_ITEM_ALL_SKILLS_BOOST_EPIC", "All Skills Exp Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_ALL_SKILLS_BOOST_EPIC);
        PET_ITEM_BUBBLEGUM = new SBCustomItem("PET_ITEM_BUBBLEGUM", "Bubblegum", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY4ZDcxNmEyMGYxNzE4YzQ0NzhhOWU5ZDQyM2E0ODYwMTQzYzI3NzNiZTU5YzljOWQ2OTg0ZTg0MzExNTUifX19", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BUBBLEGUM);
        PET_ITEM_FARMING_SKILL_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_FARMING_SKILL_BOOST_LEGENDARY", "Farming Exp Boost", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FARMING_SKILL_BOOST_LEGENDARY);
        PET_ITEM_COMBAT_SKILL_BOOST_EPIC = new SBCustomItem("PET_ITEM_COMBAT_SKILL_BOOST_EPIC", "Combat Exp Boost", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_COMBAT_SKILL_BOOST_EPIC);
        BIGGER_TEETH = new SBCustomItem("BIGGER_TEETH", "Bigger Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(BIGGER_TEETH);
        ALL_SKILLS_SUPER_BOOST = new SBCustomItem("ALL_SKILLS_SUPER_BOOST", "All Skills Exp Super-Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(ALL_SKILLS_SUPER_BOOST);
        PET_ITEM_FARMING_SKILL_BOOST_COMMON = new SBCustomItem("PET_ITEM_FARMING_SKILL_BOOST_COMMON", "Farming Exp Boost", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FARMING_SKILL_BOOST_COMMON);
        PET_ITEM_SPOOKY_CUPCAKE = new SBCustomItem("PET_ITEM_SPOOKY_CUPCAKE", "Spooky Cupcake", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk3YWQ5OWQ1M2FhY2NlNmI3YTg0ZDg3NGU2N2I4YzU2ZmFkZWRmZGY2YTI1NjVjNzYyNmNlZmJmYzMwYzM1MSJ9fX0=", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SPOOKY_CUPCAKE);
        PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON", "Foraging Exp Boost", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FORAGING_SKILL_BOOST_UNCOMMON);
        PET_ITEM_VAMPIRE_FANG = new SBCustomItem("PET_ITEM_VAMPIRE_FANG", "Vampire Fang", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_VAMPIRE_FANG);
        PET_ITEM_FISHING_SKILL_BOOST_COMMON = new SBCustomItem("PET_ITEM_FISHING_SKILL_BOOST_COMMON", "Fishing Exp Boost", "", Material.RAW_FISH, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FISHING_SKILL_BOOST_COMMON);
        PET_ITEM_HARDENED_SCALES_COMMON = new SBCustomItem("PET_ITEM_HARDENED_SCALES_COMMON", "Hardened Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_HARDENED_SCALES_COMMON);
        PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON", "Combat Exp Boost", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_COMBAT_SKILL_BOOST_UNCOMMON);
        MINOS_RELIC = new SBCustomItem("MINOS_RELIC", "Minos Relic", "", Material.SKULL_ITEM, (short) 3, "ewogICJ0aW1lc3RhbXAiIDogMTU5ODg0NDE1NTk3MSwKICAicHJvZmlsZUlkIiA6ICI0MWQzYWJjMmQ3NDk0MDBjOTA5MGQ1NDM0ZDAzODMxYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNZWdha2xvb24iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDBiNDY0OGNiZDgxN2M3YjVmYzY1NGM5YzA1NGUzNjBkODFiYmZlMWEwMGYyMTQ2NTdhMTc0ZTNlMGQwN2QyMSIKICAgIH0KICB9Cn0=", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(MINOS_RELIC);
        PET_ITEM_FARMING_SKILL_BOOST_RARE = new SBCustomItem("PET_ITEM_FARMING_SKILL_BOOST_RARE", "Farming Exp Boost", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FARMING_SKILL_BOOST_RARE);
        PET_ITEM_ALL_SKILLS_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_ALL_SKILLS_BOOST_LEGENDARY", "All Skills Exp Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_ALL_SKILLS_BOOST_LEGENDARY);
        PET_ITEM_FORAGING_SKILL_BOOST_EPIC = new SBCustomItem("PET_ITEM_FORAGING_SKILL_BOOST_EPIC", "Foraging Exp Boost", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FORAGING_SKILL_BOOST_EPIC);
        CROCHET_TIGER_PLUSHIE = new SBCustomItem("CROCHET_TIGER_PLUSHIE", "Crochet Tiger Plushie", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2JkZGY1YmFlM2FmNTkyODU4ZGY5YTE1MDEwOWU4OGMxY2FlZDhiYTUxZTc5M2MyNWFhMDNjYTFiMjVkYiJ9fX0=", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(CROCHET_TIGER_PLUSHIE);
        PET_ITEM_TOY_JERRY = new SBCustomItem("PET_ITEM_TOY_JERRY", "Jerry 3D Glasses", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzFiZjZkMTgzOGQwMzJiOWJiMjhjNjc4Y2M5MzM1MzMzZDRkMjFjNDFiM2M0ZjVhMmY2NTBkMmIzOTFiMTMxOSJ9fX0=", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_TOY_JERRY);
        PET_ITEM_TIER_BOOST = new SBCustomItem("PET_ITEM_TIER_BOOST", "Tier Boost", "", Material.HOPPER, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_TIER_BOOST);
        ANTIQUE_REMEDIES = new SBCustomItem("ANTIQUE_REMEDIES", "Antique Remedies", "", Material.RED_ROSE, (short) 3, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(ANTIQUE_REMEDIES);
        PET_ITEM_FISHING_SKILL_BOOST_EPIC = new SBCustomItem("PET_ITEM_FISHING_SKILL_BOOST_EPIC", "Fishing Exp Boost", "", Material.RAW_FISH, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FISHING_SKILL_BOOST_EPIC);
        PET_ITEM_BIG_TEETH_UNCOMMON = new SBCustomItem("PET_ITEM_BIG_TEETH_UNCOMMON", "Big Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BIG_TEETH_UNCOMMON);
        PET_ITEM_LUCKY_CLOVER = new SBCustomItem("PET_ITEM_LUCKY_CLOVER", "Lucky Clover", "", Material.WATER_LILY, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_LUCKY_CLOVER);
        PET_ITEM_HARDENED_SCALES_RARE = new SBCustomItem("PET_ITEM_HARDENED_SCALES_RARE", "Hardened Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_HARDENED_SCALES_RARE);
        DWARF_TURTLE_SHELMET = new SBCustomItem("DWARF_TURTLE_SHELMET", "Dwarf Turtle Shelmet", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY0Njk4ZmVhNWVjM2YyZmQ3ZGI3YThlM2Y0ZTk4OWExNzE2MDM1YzJiZDM2NjY0MzRiYTFhZjU4MTU3In19fQ==", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(DWARF_TURTLE_SHELMET);
        WASHED_UP_SOUVENIR = new SBCustomItem("WASHED_UP_SOUVENIR", "Washed-up Souvenir", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzc3N2YwNDY0NGRlYzVmODBiZmVhYTc0MDFhY2ZiYmMxNTBlYjI1ZDNmZjhiZTQyMjBlN2MzNDQyNmNkNzI3YyJ9fX0=", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(WASHED_UP_SOUVENIR);
        GOLD_CLAWS = new SBCustomItem("GOLD_CLAWS", "Gold Claws", "", Material.GOLD_INGOT, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(GOLD_CLAWS);
        PET_ITEM_HARDENED_SCALES_EPIC = new SBCustomItem("PET_ITEM_HARDENED_SCALES_EPIC", "Hardened Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_HARDENED_SCALES_EPIC);
        PET_ITEM_FLYING_PIG = new SBCustomItem("PET_ITEM_FLYING_PIG", "Flying Pig", "", Material.FEATHER, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FLYING_PIG);
        PET_ITEM_SHARPENED_CLAWS_LEGENDARY = new SBCustomItem("PET_ITEM_SHARPENED_CLAWS_LEGENDARY", "Sharpened Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SHARPENED_CLAWS_LEGENDARY);
        PET_ITEM_SHARPENED_CLAWS_RARE = new SBCustomItem("PET_ITEM_SHARPENED_CLAWS_RARE", "Sharpened Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SHARPENED_CLAWS_RARE);
        PET_ITEM_QUICK_CLAW = new SBCustomItem("PET_ITEM_QUICK_CLAW", "Quick Claw", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMxMmE1YTEyZWNiMjRkNjg1MmRiMzg4ZTZhMzQ3MjFjYzY3ZjUyMmNjZGU3ZTgyNGI5Zjc1ZTk1MDM2YWM5MyJ9fX0=", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_QUICK_CLAW);
        SERRATED_CLAWS = new SBCustomItem("SERRATED_CLAWS", "Serrated Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(SERRATED_CLAWS);
        PET_ITEM_SHARPENED_CLAWS_COMMON = new SBCustomItem("PET_ITEM_SHARPENED_CLAWS_COMMON", "Sharpened Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SHARPENED_CLAWS_COMMON);
        PET_ITEM_EXP_SHARE = new SBCustomItem("PET_ITEM_EXP_SHARE", "Exp Share", "", Material.YELLOW_FLOWER, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_EXP_SHARE);
        PET_ITEM_IRON_CLAWS_EPIC = new SBCustomItem("PET_ITEM_IRON_CLAWS_EPIC", "Iron Claws", "", Material.IRON_INGOT, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_IRON_CLAWS_EPIC);
        PET_ITEM_FISHING_SKILL_BOOST_RARE = new SBCustomItem("PET_ITEM_FISHING_SKILL_BOOST_RARE", "Fishing Exp Boost", "", Material.RAW_FISH, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FISHING_SKILL_BOOST_RARE);
        PET_ITEM_SHARPENED_CLAWS_UNCOMMON = new SBCustomItem("PET_ITEM_SHARPENED_CLAWS_UNCOMMON", "Sharpened Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SHARPENED_CLAWS_UNCOMMON);
        PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON", "Farming Exp Boost", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FARMING_SKILL_BOOST_UNCOMMON);
        PET_ITEM_MINING_SKILL_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_MINING_SKILL_BOOST_UNCOMMON", "Mining Exp Boost", "", Material.IRON_PICKAXE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_MINING_SKILL_BOOST_UNCOMMON);
        PET_ITEM_MINING_SKILL_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_MINING_SKILL_BOOST_LEGENDARY", "Mining Exp Boost", "", Material.IRON_PICKAXE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_MINING_SKILL_BOOST_LEGENDARY);
        PET_ITEM_FORAGING_SKILL_BOOST_COMMON = new SBCustomItem("PET_ITEM_FORAGING_SKILL_BOOST_COMMON", "Foraging Exp Boost", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FORAGING_SKILL_BOOST_COMMON);
        PET_ITEM_FISHING_SKILL_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_FISHING_SKILL_BOOST_LEGENDARY", "Fishing Exp Boost", "", Material.RAW_FISH, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FISHING_SKILL_BOOST_LEGENDARY);
        PET_ITEM_SADDLE = new SBCustomItem("PET_ITEM_SADDLE", "Saddle", "", Material.SADDLE, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SADDLE);
        REAPER_GEM = new SBCustomItem("REAPER_GEM", "Reaper Gem", "", Material.SKULL_ITEM, (short) 3, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmIwNTVjODEwYmRkZmQxNjI2NGVjOGQ0MzljNDMyODNlMzViY2E3MWE1MDk4M2UxNWUzNjRjZDhhYjdjNjY4ZiJ9fX0", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(REAPER_GEM);
        PET_ITEM_ALL_SKILLS_BOOST_RARE = new SBCustomItem("PET_ITEM_ALL_SKILLS_BOOST_RARE", "All Skills Exp Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_ALL_SKILLS_BOOST_RARE);
        PET_ITEM_ALL_SKILLS_BOOST_COMMON = new SBCustomItem("PET_ITEM_ALL_SKILLS_BOOST_COMMON", "All Skills Exp Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_ALL_SKILLS_BOOST_COMMON);
        PET_ITEM_MINING_SKILL_BOOST_EPIC = new SBCustomItem("PET_ITEM_MINING_SKILL_BOOST_EPIC", "Mining Exp Boost", "", Material.IRON_PICKAXE, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_MINING_SKILL_BOOST_EPIC);
        PET_ITEM_FORAGING_SKILL_BOOST_RARE = new SBCustomItem("PET_ITEM_FORAGING_SKILL_BOOST_RARE", "Foraging Exp Boost", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FORAGING_SKILL_BOOST_RARE);
        PET_ITEM_COMBAT_SKILL_BOOST_RARE = new SBCustomItem("PET_ITEM_COMBAT_SKILL_BOOST_RARE", "Combat Exp Boost", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_COMBAT_SKILL_BOOST_RARE);
        PET_ITEM_IRON_CLAWS_UNCOMMON = new SBCustomItem("PET_ITEM_IRON_CLAWS_UNCOMMON", "Iron Claws", "", Material.IRON_INGOT, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_IRON_CLAWS_UNCOMMON);
        PET_ITEM_FORAGING_SKILL_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_FORAGING_SKILL_BOOST_LEGENDARY", "Foraging Exp Boost", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FORAGING_SKILL_BOOST_LEGENDARY);
        PET_ITEM_BIG_TEETH_RARE = new SBCustomItem("PET_ITEM_BIG_TEETH_RARE", "Big Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BIG_TEETH_RARE);
        PET_ITEM_COMBAT_SKILL_BOOST_LEGENDARY = new SBCustomItem("PET_ITEM_COMBAT_SKILL_BOOST_LEGENDARY", "Combat Exp Boost", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_COMBAT_SKILL_BOOST_LEGENDARY);
        PET_ITEM_BIG_TEETH_COMMON = new SBCustomItem("PET_ITEM_BIG_TEETH_COMMON", "Big Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BIG_TEETH_COMMON);
        PET_ITEM_SHARPENED_CLAWS_EPIC = new SBCustomItem("PET_ITEM_SHARPENED_CLAWS_EPIC", "Sharpened Claws", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_SHARPENED_CLAWS_EPIC);
        PET_ITEM_BIG_TEETH_EPIC = new SBCustomItem("PET_ITEM_BIG_TEETH_EPIC", "Big Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BIG_TEETH_EPIC);
        PET_ITEM_FARMING_SKILL_BOOST_EPIC = new SBCustomItem("PET_ITEM_FARMING_SKILL_BOOST_EPIC", "Farming Exp Boost", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_FARMING_SKILL_BOOST_EPIC);
        REINFORCED_SCALES = new SBCustomItem("REINFORCED_SCALES", "Reinforced Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(REINFORCED_SCALES);
        PET_ITEM_TEXTBOOK = new SBCustomItem("PET_ITEM_TEXTBOOK", "Textbook", "", Material.BOOK, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_TEXTBOOK);
        PET_ITEM_HARDENED_SCALES_LEGENDARY = new SBCustomItem("PET_ITEM_HARDENED_SCALES_LEGENDARY", "Hardened Scales", "", Material.PRISMARINE_CRYSTALS, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_HARDENED_SCALES_LEGENDARY);
        PET_ITEM_IRON_CLAWS_COMMON = new SBCustomItem("PET_ITEM_IRON_CLAWS_COMMON", "Iron Claws", "", Material.IRON_INGOT, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_IRON_CLAWS_COMMON);
        PET_ITEM_BIG_TEETH_LEGENDARY = new SBCustomItem("PET_ITEM_BIG_TEETH_LEGENDARY", "Big Teeth", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_BIG_TEETH_LEGENDARY);
        PET_ITEM_IRON_CLAWS_LEGENDARY = new SBCustomItem("PET_ITEM_IRON_CLAWS_LEGENDARY", "Iron Claws", "", Material.IRON_INGOT, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_IRON_CLAWS_LEGENDARY);
        PET_ITEM_IRON_CLAWS_RARE = new SBCustomItem("PET_ITEM_IRON_CLAWS_RARE", "Iron Claws", "", Material.IRON_INGOT, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_IRON_CLAWS_RARE);
        PET_ITEM_ALL_SKILLS_BOOST_UNCOMMON = new SBCustomItem("PET_ITEM_ALL_SKILLS_BOOST_UNCOMMON", "All Skills Exp Boost", "", Material.DIAMOND, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_ALL_SKILLS_BOOST_UNCOMMON);
        PET_ITEM_MINING_SKILL_BOOST_RARE = new SBCustomItem("PET_ITEM_MINING_SKILL_BOOST_RARE", "Mining Exp Boost", "", Material.IRON_PICKAXE, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_MINING_SKILL_BOOST_RARE);
        PET_ITEM_MINING_SKILL_BOOST_COMMON = new SBCustomItem("PET_ITEM_MINING_SKILL_BOOST_COMMON", "Mining Exp Boost", "", Material.IRON_PICKAXE, (short) 0, "", SBCustomItem.Rarity.COMMON, true, null, SBCustomItem.Category.PET_ITEM, 0, skyBlock);
        SBItems.registerItem(PET_ITEM_MINING_SKILL_BOOST_COMMON);


    }

}

