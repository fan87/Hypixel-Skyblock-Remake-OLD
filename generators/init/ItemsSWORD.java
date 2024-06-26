
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


// Start class: ItemsSWORD
// Category: SWORD
// Last Update: 2022-01-14 03:34:12.697689

public class ItemsSWORD {

    public static SBCustomItem END_STONE_SWORD;
    public static SBCustomItem DAEDALUS_AXE;
    public static SBCustomItem FROZEN_SCYTHE;
    public static SBCustomItem ASTRAEA;
    public static SBCustomItem STARRED_SHADOW_FURY;
    public static SBCustomItem FANCY_SWORD;
    public static SBCustomItem SQUIRE_SWORD;
    public static SBCustomItem ZOMBIE_COMMANDER_WHIP;
    public static SBCustomItem CUTLASS;
    public static SBCustomItem ASPECT_OF_THE_DRAGON;
    public static SBCustomItem FLOWER_OF_TRUTH;
    public static SBCustomItem ICE_SPRAY_WAND;
    public static SBCustomItem CONJURING_SWORD;
    public static SBCustomItem SILENT_DEATH;
    public static SBCustomItem SHAMAN_SWORD;
    public static SBCustomItem WITHER_CLOAK;
    public static SBCustomItem ASPECT_OF_THE_JERRY;
    public static SBCustomItem SCORPION_FOIL;
    public static SBCustomItem CLEAVER;
    public static SBCustomItem FEROCITY_SWORD_150;
    public static SBCustomItem ASPECT_OF_THE_END;
    public static SBCustomItem SWORD_OF_REVELATIONS;
    public static SBCustomItem STAR_SWORD_3000;
    public static SBCustomItem PRISMARINE_BLADE;
    public static SBCustomItem NECROMANCER_SWORD;
    public static SBCustomItem BAT_WAND;
    public static SBCustomItem EMBER_ROD;
    public static SBCustomItem HEALING_CIRCLE_ITEM;
    public static SBCustomItem STAR_SWORD_9000;
    public static SBCustomItem SHADOW_FURY;
    public static SBCustomItem LEAPING_SWORD;
    public static SBCustomItem STONE_BLADE;
    public static SBCustomItem VOIDEDGE_KATANA;
    public static SBCustomItem GHOUL_BUSTER;
    public static SBCustomItem SPIRIT_SWORD;
    public static SBCustomItem SILVER_FANG;
    public static SBCustomItem STAR_SWORD;
    public static SBCustomItem FEL_SWORD;
    public static SBCustomItem TRIBAL_SPEAR;
    public static SBCustomItem RAIDER_AXE;
    public static SBCustomItem TACTICIAN_SWORD;
    public static SBCustomItem MIDAS_STAFF;
    public static SBCustomItem SINSEEKER_SCYTHE;
    public static SBCustomItem GIANT_CLEAVER;
    public static SBCustomItem END_SWORD;
    public static SBCustomItem AXE_OF_THE_SHREDDED;
    public static SBCustomItem SUPER_CLEAVER;
    public static SBCustomItem REVENANT_SWORD;
    public static SBCustomItem EMERALD_BLADE;
    public static SBCustomItem REAPER_SWORD;
    public static SBCustomItem SEISMIC_WAVE_STICK;
    public static SBCustomItem CRYPT_DREADLORD_SWORD;
    public static SBCustomItem SILK_EDGE_SWORD;
    public static SBCustomItem GIANTS_SWORD;
    public static SBCustomItem HUNTER_KNIFE;
    public static SBCustomItem VORPAL_KATANA;
    public static SBCustomItem HYPERION;
    public static SBCustomItem CRYPT_WITHERLORD_SWORD;
    public static SBCustomItem ORNATE_ZOMBIE_SWORD;
    public static SBCustomItem VALKYRIE;
    public static SBCustomItem MERCENARY_AXE;
    public static SBCustomItem STARRED_STONE_BLADE;
    public static SBCustomItem POLYMORPH_WAND;
    public static SBCustomItem ZOMBIE_KNIGHT_SWORD;
    public static SBCustomItem VOIDWALKER_KATANA;
    public static SBCustomItem FEROCITY_SWORD_50;
    public static SBCustomItem ASPECT_OF_THE_VOID;
    public static SBCustomItem SOUL_WHIP;
    public static SBCustomItem YETI_SWORD;
    public static SBCustomItem BONZO_STAFF;
    public static SBCustomItem ELECTRIC_WAND_ITEM;
    public static SBCustomItem REAPER_SCYTHE;
    public static SBCustomItem PIGMAN_SWORD;
    public static SBCustomItem VENOMOUS_SWORD;
    public static SBCustomItem MIDAS_SWORD;
    public static SBCustomItem STARRED_BONZO_STAFF;
    public static SBCustomItem NECRON_BLADE;
    public static SBCustomItem ATOMSPLIT_KATANA;
    public static SBCustomItem SPIDER_SWORD;
    public static SBCustomItem HORSEMAN_SCYTHE;
    public static SBCustomItem ROGUE_SWORD;
    public static SBCustomItem FLAMING_SWORD;
    public static SBCustomItem SCYLLA;
    public static SBCustomItem LIVID_DAGGER;
    public static SBCustomItem UNDEAD_SWORD;
    public static SBCustomItem HYPER_CLEAVER;
    public static SBCustomItem EDIBLE_MACE;
    public static SBCustomItem ZOMBIE_SOLDIER_CUTLASS;
    public static SBCustomItem EARTH_SHARD;
    public static SBCustomItem RECLUSE_FANG;
    public static SBCustomItem JERRY_STAFF;
    public static SBCustomItem GOLEM_SWORD;
    public static SBCustomItem NOVA_SWORD;
    public static SBCustomItem INK_WAND;
    public static SBCustomItem HARVESTING_SWORD;
    public static SBCustomItem FLORID_ZOMBIE_SWORD;
    public static SBCustomItem ZOMBIE_SWORD;
    public static SBCustomItem POOCH_SWORD;



    public ItemsSWORD(SkyBlock skyBlock) {
        END_STONE_SWORD = new SBCustomItem("END_STONE_SWORD", "End Stone Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 20480, skyBlock);
        SBItems.registerItem(END_STONE_SWORD);
        DAEDALUS_AXE = new SBCustomItem("DAEDALUS_AXE", "Daedalus Axe", "", Material.GOLD_AXE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(DAEDALUS_AXE);
        FROZEN_SCYTHE = new SBCustomItem("FROZEN_SCYTHE", "Frozen Scythe", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 100, skyBlock);
        SBItems.registerItem(FROZEN_SCYTHE);
        ASTRAEA = new SBCustomItem("ASTRAEA", "Astraea", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(ASTRAEA);
        STARRED_SHADOW_FURY = new SBCustomItem("STARRED_SHADOW_FURY", "Shadow Fury", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 500000, skyBlock);
        SBItems.registerItem(STARRED_SHADOW_FURY);
        FANCY_SWORD = new SBCustomItem("FANCY_SWORD", "Fancy Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SWORD, 50, skyBlock);
        SBItems.registerItem(FANCY_SWORD);
        SQUIRE_SWORD = new SBCustomItem("SQUIRE_SWORD", "Squire Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 2500, skyBlock);
        SBItems.registerItem(SQUIRE_SWORD);
        ZOMBIE_COMMANDER_WHIP = new SBCustomItem("ZOMBIE_COMMANDER_WHIP", "Zombie Commander Whip", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(ZOMBIE_COMMANDER_WHIP);
        CUTLASS = new SBCustomItem("CUTLASS", "Cutlass", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(CUTLASS);
        ASPECT_OF_THE_DRAGON = new SBCustomItem("ASPECT_OF_THE_DRAGON", "Aspect of the Dragons", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 100000, skyBlock);
        SBItems.registerItem(ASPECT_OF_THE_DRAGON);
        FLOWER_OF_TRUTH = new SBCustomItem("FLOWER_OF_TRUTH", "Flower of Truth", "", Material.RED_ROSE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.SWORD, 100000, skyBlock);
        SBItems.registerItem(FLOWER_OF_TRUTH);
        ICE_SPRAY_WAND = new SBCustomItem("ICE_SPRAY_WAND", "Ice Spray Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.SWORD, 10000, skyBlock);
        SBItems.registerItem(ICE_SPRAY_WAND);
        CONJURING_SWORD = new SBCustomItem("CONJURING_SWORD", "Conjuring", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 2000, skyBlock);
        SBItems.registerItem(CONJURING_SWORD);
        SILENT_DEATH = new SBCustomItem("SILENT_DEATH", "Silent Death", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 20000, skyBlock);
        SBItems.registerItem(SILENT_DEATH);
        SHAMAN_SWORD = new SBCustomItem("SHAMAN_SWORD", "Shaman Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(SHAMAN_SWORD);
        WITHER_CLOAK = new SBCustomItem("WITHER_CLOAK", "Wither Cloak Sword", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(WITHER_CLOAK);
        ASPECT_OF_THE_JERRY = new SBCustomItem("ASPECT_OF_THE_JERRY", "Aspect of the Jerry", "", Material.WOOD_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(ASPECT_OF_THE_JERRY);
        SCORPION_FOIL = new SBCustomItem("SCORPION_FOIL", "Scorpion Foil", "", Material.WOOD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(SCORPION_FOIL);
        CLEAVER = new SBCustomItem("CLEAVER", "Cleaver", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 8, skyBlock);
        SBItems.registerItem(CLEAVER);
        FEROCITY_SWORD_150 = new SBCustomItem("FEROCITY_SWORD_150", "Ferocity Sword 150", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(FEROCITY_SWORD_150);
        ASPECT_OF_THE_END = new SBCustomItem("ASPECT_OF_THE_END", "Aspect of the End", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 56000, skyBlock);
        SBItems.registerItem(ASPECT_OF_THE_END);
        SWORD_OF_REVELATIONS = new SBCustomItem("SWORD_OF_REVELATIONS", "Sword of Revelations", "", Material.WOOD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(SWORD_OF_REVELATIONS);
        STAR_SWORD_3000 = new SBCustomItem("STAR_SWORD_3000", "Sword of the Stars 3000", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.SPECIAL, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(STAR_SWORD_3000);
        PRISMARINE_BLADE = new SBCustomItem("PRISMARINE_BLADE", "Prismarine Blade", "", Material.PRISMARINE_SHARD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 160, skyBlock);
        SBItems.registerItem(PRISMARINE_BLADE);
        NECROMANCER_SWORD = new SBCustomItem("NECROMANCER_SWORD", "Necromancer Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(NECROMANCER_SWORD);
        BAT_WAND = new SBCustomItem("BAT_WAND", "Spirit Sceptre", "", Material.RED_ROSE, (short) 2, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(BAT_WAND);
        EMBER_ROD = new SBCustomItem("EMBER_ROD", "Ember Rod", "", Material.BLAZE_ROD, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(EMBER_ROD);
        HEALING_CIRCLE_ITEM = new SBCustomItem("HEALING_CIRCLE_ITEM", "Healing Circle", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(HEALING_CIRCLE_ITEM);
        STAR_SWORD_9000 = new SBCustomItem("STAR_SWORD_9000", "Sword of the Stars 9000", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.SPECIAL, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(STAR_SWORD_9000);
        SHADOW_FURY = new SBCustomItem("SHADOW_FURY", "Shadow Fury", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 500000, skyBlock);
        SBItems.registerItem(SHADOW_FURY);
        LEAPING_SWORD = new SBCustomItem("LEAPING_SWORD", "Leaping Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 375000, skyBlock);
        SBItems.registerItem(LEAPING_SWORD);
        STONE_BLADE = new SBCustomItem("STONE_BLADE", "Adaptive Blade", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 20000, skyBlock);
        SBItems.registerItem(STONE_BLADE);
        VOIDEDGE_KATANA = new SBCustomItem("VOIDEDGE_KATANA", "Voidedge Katana", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(VOIDEDGE_KATANA);
        GHOUL_BUSTER = new SBCustomItem("GHOUL_BUSTER", "Ghoul Buster", "", Material.IRON_HOE, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(GHOUL_BUSTER);
        SPIRIT_SWORD = new SBCustomItem("SPIRIT_SWORD", "Spirit Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(SPIRIT_SWORD);
        SILVER_FANG = new SBCustomItem("SILVER_FANG", "Silver Fang", "", Material.GHAST_TEAR, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, true, null, SBCustomItem.Category.SWORD, 2000, skyBlock);
        SBItems.registerItem(SILVER_FANG);
        STAR_SWORD = new SBCustomItem("STAR_SWORD", "Sword of the Stars", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.SPECIAL, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(STAR_SWORD);
        FEL_SWORD = new SBCustomItem("FEL_SWORD", "Fel Sword", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 20000, skyBlock);
        SBItems.registerItem(FEL_SWORD);
        TRIBAL_SPEAR = new SBCustomItem("TRIBAL_SPEAR", "Tribal Spear", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(TRIBAL_SPEAR);
        RAIDER_AXE = new SBCustomItem("RAIDER_AXE", "Raider Axe", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(RAIDER_AXE);
        TACTICIAN_SWORD = new SBCustomItem("TACTICIAN_SWORD", "Tactician's Sword", "", Material.WOOD_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(TACTICIAN_SWORD);
        MIDAS_STAFF = new SBCustomItem("MIDAS_STAFF", "Midas Staff", "", Material.GOLD_SPADE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(MIDAS_STAFF);
        SINSEEKER_SCYTHE = new SBCustomItem("SINSEEKER_SCYTHE", "§4Sin§5seeker Scythe", "", Material.GOLD_HOE, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(SINSEEKER_SCYTHE);
        GIANT_CLEAVER = new SBCustomItem("GIANT_CLEAVER", "Giant Cleaver", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(GIANT_CLEAVER);
        END_SWORD = new SBCustomItem("END_SWORD", "End Sword", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 10, skyBlock);
        SBItems.registerItem(END_SWORD);
        AXE_OF_THE_SHREDDED = new SBCustomItem("AXE_OF_THE_SHREDDED", "Axe of the Shredded", "", Material.DIAMOND_AXE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(AXE_OF_THE_SHREDDED);
        SUPER_CLEAVER = new SBCustomItem("SUPER_CLEAVER", "Super Cleaver", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(SUPER_CLEAVER);
        REVENANT_SWORD = new SBCustomItem("REVENANT_SWORD", "Revenant Falchion", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(REVENANT_SWORD);
        EMERALD_BLADE = new SBCustomItem("EMERALD_BLADE", "Emerald Blade", "", Material.EMERALD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 153000, skyBlock);
        SBItems.registerItem(EMERALD_BLADE);
        REAPER_SWORD = new SBCustomItem("REAPER_SWORD", "Reaper Falchion", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(REAPER_SWORD);
        SEISMIC_WAVE_STICK = new SBCustomItem("SEISMIC_WAVE_STICK", "Seismic Wave", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(SEISMIC_WAVE_STICK);
        CRYPT_DREADLORD_SWORD = new SBCustomItem("CRYPT_DREADLORD_SWORD", "Dreadlord Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(CRYPT_DREADLORD_SWORD);
        SILK_EDGE_SWORD = new SBCustomItem("SILK_EDGE_SWORD", "Silk-Edge Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 375000, skyBlock);
        SBItems.registerItem(SILK_EDGE_SWORD);
        GIANTS_SWORD = new SBCustomItem("GIANTS_SWORD", "Giant's Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(GIANTS_SWORD);
        HUNTER_KNIFE = new SBCustomItem("HUNTER_KNIFE", "Hunter Knife", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(HUNTER_KNIFE);
        VORPAL_KATANA = new SBCustomItem("VORPAL_KATANA", "Vorpal Katana", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(VORPAL_KATANA);
        HYPERION = new SBCustomItem("HYPERION", "Hyperion", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(HYPERION);
        CRYPT_WITHERLORD_SWORD = new SBCustomItem("CRYPT_WITHERLORD_SWORD", "Crypt Witherlord Sword", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 500, skyBlock);
        SBItems.registerItem(CRYPT_WITHERLORD_SWORD);
        ORNATE_ZOMBIE_SWORD = new SBCustomItem("ORNATE_ZOMBIE_SWORD", "Ornate Zombie Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(ORNATE_ZOMBIE_SWORD);
        VALKYRIE = new SBCustomItem("VALKYRIE", "Valkyrie", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(VALKYRIE);
        MERCENARY_AXE = new SBCustomItem("MERCENARY_AXE", "Mercenary Axe", "", Material.IRON_AXE, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 15000, skyBlock);
        SBItems.registerItem(MERCENARY_AXE);
        STARRED_STONE_BLADE = new SBCustomItem("STARRED_STONE_BLADE", "Adaptive Blade", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 20000, skyBlock);
        SBItems.registerItem(STARRED_STONE_BLADE);
        POLYMORPH_WAND = new SBCustomItem("POLYMORPH_WAND", "Polymorph Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.SWORD, 64000, skyBlock);
        SBItems.registerItem(POLYMORPH_WAND);
        ZOMBIE_KNIGHT_SWORD = new SBCustomItem("ZOMBIE_KNIGHT_SWORD", "Zombie Knight Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(ZOMBIE_KNIGHT_SWORD);
        VOIDWALKER_KATANA = new SBCustomItem("VOIDWALKER_KATANA", "Voidwalker Katana", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(VOIDWALKER_KATANA);
        FEROCITY_SWORD_50 = new SBCustomItem("FEROCITY_SWORD_50", "Ferocity Sword 50", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(FEROCITY_SWORD_50);
        ASPECT_OF_THE_VOID = new SBCustomItem("ASPECT_OF_THE_VOID", "Aspect of the Void", "", Material.DIAMOND_SPADE, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 56000, skyBlock);
        SBItems.registerItem(ASPECT_OF_THE_VOID);
        SOUL_WHIP = new SBCustomItem("SOUL_WHIP", "Soul Whip", "", Material.FISHING_ROD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(SOUL_WHIP);
        YETI_SWORD = new SBCustomItem("YETI_SWORD", "Yeti Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(YETI_SWORD);
        BONZO_STAFF = new SBCustomItem("BONZO_STAFF", "Bonzo's Staff", "", Material.BLAZE_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(BONZO_STAFF);
        ELECTRIC_WAND_ITEM = new SBCustomItem("ELECTRIC_WAND_ITEM", "Electric Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.RARE, true, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(ELECTRIC_WAND_ITEM);
        REAPER_SCYTHE = new SBCustomItem("REAPER_SCYTHE", "Reaper Scythe", "", Material.DIAMOND_HOE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(REAPER_SCYTHE);
        PIGMAN_SWORD = new SBCustomItem("PIGMAN_SWORD", "Pigman Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.SWORD, 3100000, skyBlock);
        SBItems.registerItem(PIGMAN_SWORD);
        VENOMOUS_SWORD = new SBCustomItem("VENOMOUS_SWORD", "Venomous Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 20, skyBlock);
        SBItems.registerItem(VENOMOUS_SWORD);
        MIDAS_SWORD = new SBCustomItem("MIDAS_SWORD", "Midas' Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 10000, skyBlock);
        SBItems.registerItem(MIDAS_SWORD);
        STARRED_BONZO_STAFF = new SBCustomItem("STARRED_BONZO_STAFF", "Bonzo's Staff", "", Material.BLAZE_ROD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(STARRED_BONZO_STAFF);
        NECRON_BLADE = new SBCustomItem("NECRON_BLADE", "Necron's Blade (Unrefined)", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(NECRON_BLADE);
        ATOMSPLIT_KATANA = new SBCustomItem("ATOMSPLIT_KATANA", "Atomsplit Katana", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, true, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(ATOMSPLIT_KATANA);
        SPIDER_SWORD = new SBCustomItem("SPIDER_SWORD", "Spider Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SWORD, 5, skyBlock);
        SBItems.registerItem(SPIDER_SWORD);
        HORSEMAN_SCYTHE = new SBCustomItem("HORSEMAN_SCYTHE", "Horseman's Scythe", "", Material.DIAMOND_HOE, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(HORSEMAN_SCYTHE);
        ROGUE_SWORD = new SBCustomItem("ROGUE_SWORD", "Rogue Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SWORD, 3, skyBlock);
        SBItems.registerItem(ROGUE_SWORD);
        FLAMING_SWORD = new SBCustomItem("FLAMING_SWORD", "Flaming Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 20, skyBlock);
        SBItems.registerItem(FLAMING_SWORD);
        SCYLLA = new SBCustomItem("SCYLLA", "Scylla", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 200, skyBlock);
        SBItems.registerItem(SCYLLA);
        LIVID_DAGGER = new SBCustomItem("LIVID_DAGGER", "Livid Dagger", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 500000, skyBlock);
        SBItems.registerItem(LIVID_DAGGER);
        UNDEAD_SWORD = new SBCustomItem("UNDEAD_SWORD", "Undead Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.COMMON, false, null, SBCustomItem.Category.SWORD, 5, skyBlock);
        SBItems.registerItem(UNDEAD_SWORD);
        HYPER_CLEAVER = new SBCustomItem("HYPER_CLEAVER", "Hyper Cleaver", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(HYPER_CLEAVER);
        EDIBLE_MACE = new SBCustomItem("EDIBLE_MACE", "Edible Mace", "", Material.MUTTON, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(EDIBLE_MACE);
        ZOMBIE_SOLDIER_CUTLASS = new SBCustomItem("ZOMBIE_SOLDIER_CUTLASS", "Zombie Soldier Cutlass", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 1000, skyBlock);
        SBItems.registerItem(ZOMBIE_SOLDIER_CUTLASS);
        EARTH_SHARD = new SBCustomItem("EARTH_SHARD", "Earth Shard", "", Material.STONE_SWORD, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 123, skyBlock);
        SBItems.registerItem(EARTH_SHARD);
        RECLUSE_FANG = new SBCustomItem("RECLUSE_FANG", "Recluse Fang", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(RECLUSE_FANG);
        JERRY_STAFF = new SBCustomItem("JERRY_STAFF", "Jerry-chine Gun", "", Material.GOLD_BARDING, (short) 0, "", SBCustomItem.Rarity.EPIC, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(JERRY_STAFF);
        GOLEM_SWORD = new SBCustomItem("GOLEM_SWORD", "Golem Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 77000, skyBlock);
        SBItems.registerItem(GOLEM_SWORD);
        NOVA_SWORD = new SBCustomItem("NOVA_SWORD", "Sword of the Universe", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.SPECIAL, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(NOVA_SWORD);
        INK_WAND = new SBCustomItem("INK_WAND", "Ink Wand", "", Material.STICK, (short) 0, "", SBCustomItem.Rarity.EPIC, true, null, SBCustomItem.Category.SWORD, 10000, skyBlock);
        SBItems.registerItem(INK_WAND);
        HARVESTING_SWORD = new SBCustomItem("HARVESTING_SWORD", "Harvesting Sword", "", Material.DIAMOND_SWORD, (short) 0, "", SBCustomItem.Rarity.UNCOMMON, false, null, SBCustomItem.Category.SWORD, 1, skyBlock);
        SBItems.registerItem(HARVESTING_SWORD);
        FLORID_ZOMBIE_SWORD = new SBCustomItem("FLORID_ZOMBIE_SWORD", "Florid Zombie Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(FLORID_ZOMBIE_SWORD);
        ZOMBIE_SWORD = new SBCustomItem("ZOMBIE_SWORD", "Zombie Sword", "", Material.IRON_SWORD, (short) 0, "", SBCustomItem.Rarity.RARE, false, null, SBCustomItem.Category.SWORD, 300000, skyBlock);
        SBItems.registerItem(ZOMBIE_SWORD);
        POOCH_SWORD = new SBCustomItem("POOCH_SWORD", "Pooch Sword", "", Material.GOLD_SWORD, (short) 0, "", SBCustomItem.Rarity.LEGENDARY, false, null, SBCustomItem.Category.SWORD, 0, skyBlock);
        SBItems.registerItem(POOCH_SWORD);


    }

}

