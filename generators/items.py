# Items Generator by fan87


import requests
import json
from difflib import SequenceMatcher
import datetime
import re

content = json.loads(requests.get("https://api.hypixel.net/resources/skyblock/items").text)


def similar(a: str, b: str) -> float:
    return SequenceMatcher(None, a, b).ratio()

materials =["STONE", "GRASS", "DIRT", "COBBLESTONE", "WOOD", "SAPLING", "BEDROCK", "WATER", "STATIONARY_WATER", "LAVA", "STATIONARY_LAVA", "SAND", "GRAVEL", "GOLD_ORE", "IRON_ORE", "COAL_ORE", "LOG", "LEAVES", "SPONGE", "GLASS", "LAPIS_ORE", "LAPIS_BLOCK", "DISPENSER", "SANDSTONE", "NOTE_BLOCK", "BED_BLOCK", "POWERED_RAIL", "DETECTOR_RAIL", "PISTON_STICKY_BASE", "WEB", "LONG_GRASS", "DEAD_BUSH", "PISTON_BASE", "PISTON_EXTENSION", "WOOL", "PISTON_MOVING_PIECE", "YELLOW_FLOWER", "RED_ROSE", "BROWN_MUSHROOM", "RED_MUSHROOM", "GOLD_BLOCK", "IRON_BLOCK", "DOUBLE_STEP", "STEP", "BRICK", "TNT", "BOOKSHELF", "MOSSY_COBBLESTONE", "OBSIDIAN", "TORCH", "FIRE", "MOB_SPAWNER", "WOOD_STAIRS", "CHEST", "REDSTONE_WIRE", "DIAMOND_ORE", "DIAMOND_BLOCK", "WORKBENCH", "CROPS", "SOIL", "FURNACE", "BURNING_FURNACE", "SIGN_POST", "WOODEN_DOOR", "LADDER", "RAILS", "COBBLESTONE_STAIRS", "WALL_SIGN", "LEVER", "STONE_PLATE", "IRON_DOOR_BLOCK", "WOOD_PLATE", "REDSTONE_ORE", "GLOWING_REDSTONE_ORE", "REDSTONE_TORCH_OFF", "REDSTONE_TORCH_ON", "STONE_BUTTON", "SNOW", "ICE", "SNOW_BLOCK", "CACTUS", "CLAY", "SUGAR_CANE_BLOCK", "JUKEBOX", "FENCE", "PUMPKIN", "NETHERRACK", "SOUL_SAND", "GLOWSTONE", "PORTAL", "JACK_O_LANTERN", "CAKE_BLOCK", "DIODE_BLOCK_OFF", "DIODE_BLOCK_ON", "STAINED_GLASS", "TRAP_DOOR", "MONSTER_EGGS", "SMOOTH_BRICK", "HUGE_MUSHROOM_1", "HUGE_MUSHROOM_2", "IRON_FENCE", "THIN_GLASS", "MELON_BLOCK", "PUMPKIN_STEM", "MELON_STEM", "VINE", "FENCE_GATE", "BRICK_STAIRS", "SMOOTH_STAIRS", "MYCEL", "WATER_LILY", "NETHER_BRICK", "NETHER_FENCE", "NETHER_BRICK_STAIRS", "NETHER_WARTS", "ENCHANTMENT_TABLE", "BREWING_STAND", "CAULDRON", "ENDER_PORTAL", "ENDER_PORTAL_FRAME", "ENDER_STONE", "DRAGON_EGG", "REDSTONE_LAMP_OFF", "REDSTONE_LAMP_ON", "WOOD_DOUBLE_STEP", "WOOD_STEP", "COCOA", "SANDSTONE_STAIRS", "EMERALD_ORE", "ENDER_CHEST", "TRIPWIRE_HOOK", "TRIPWIRE", "EMERALD_BLOCK", "SPRUCE_WOOD_STAIRS", "BIRCH_WOOD_STAIRS", "JUNGLE_WOOD_STAIRS", "COMMAND", "BEACON", "COBBLE_WALL", "FLOWER_POT", "CARROT", "POTATO", "WOOD_BUTTON", "SKULL", "ANVIL", "TRAPPED_CHEST", "GOLD_PLATE", "IRON_PLATE", "REDSTONE_COMPARATOR_OFF", "REDSTONE_COMPARATOR_ON", "DAYLIGHT_DETECTOR", "REDSTONE_BLOCK", "QUARTZ_ORE", "HOPPER", "QUARTZ_BLOCK", "QUARTZ_STAIRS", "ACTIVATOR_RAIL", "DROPPER", "STAINED_CLAY", "STAINED_GLASS_PANE", "LEAVES_2", "LOG_2", "ACACIA_STAIRS", "DARK_OAK_STAIRS", "SLIME_BLOCK", "BARRIER", "IRON_TRAPDOOR", "PRISMARINE", "SEA_LANTERN", "HAY_BLOCK", "CARPET", "HARD_CLAY", "COAL_BLOCK", "PACKED_ICE", "DOUBLE_PLANT", "STANDING_BANNER", "WALL_BANNER", "DAYLIGHT_DETECTOR_INVERTED", "RED_SANDSTONE", "RED_SANDSTONE_STAIRS", "DOUBLE_STONE_SLAB2", "STONE_SLAB2", "SPRUCE_FENCE_GATE", "BIRCH_FENCE_GATE", "JUNGLE_FENCE_GATE", "DARK_OAK_FENCE_GATE", "ACACIA_FENCE_GATE", "SPRUCE_FENCE", "BIRCH_FENCE", "JUNGLE_FENCE", "DARK_OAK_FENCE", "ACACIA_FENCE", "SPRUCE_DOOR", "BIRCH_DOOR", "JUNGLE_DOOR", "ACACIA_DOOR", "DARK_OAK_DOOR", "IRON_SPADE", "IRON_PICKAXE", "IRON_AXE", "FLINT_AND_STEEL", "APPLE", "BOW", "ARROW", "COAL", "DIAMOND", "IRON_INGOT", "GOLD_INGOT", "IRON_SWORD", "WOOD_SWORD", "WOOD_SPADE", "WOOD_PICKAXE", "WOOD_AXE", "STONE_SWORD", "STONE_SPADE", "STONE_PICKAXE", "STONE_AXE", "DIAMOND_SWORD", "DIAMOND_SPADE", "DIAMOND_PICKAXE", "DIAMOND_AXE", "STICK", "BOWL", "MUSHROOM_SOUP", "GOLD_SWORD", "GOLD_SPADE", "GOLD_PICKAXE", "GOLD_AXE", "STRING", "FEATHER", "SULPHUR", "WOOD_HOE", "STONE_HOE", "IRON_HOE", "DIAMOND_HOE", "GOLD_HOE", "SEEDS", "WHEAT", "BREAD", "LEATHER_HELMET", "LEATHER_CHESTPLATE", "LEATHER_LEGGINGS", "LEATHER_BOOTS", "CHAINMAIL_HELMET", "CHAINMAIL_CHESTPLATE", "CHAINMAIL_LEGGINGS", "CHAINMAIL_BOOTS", "IRON_HELMET", "IRON_CHESTPLATE", "IRON_LEGGINGS", "IRON_BOOTS", "DIAMOND_HELMET", "DIAMOND_CHESTPLATE", "DIAMOND_LEGGINGS", "DIAMOND_BOOTS", "GOLD_HELMET", "GOLD_CHESTPLATE", "GOLD_LEGGINGS", "GOLD_BOOTS", "FLINT", "PORK", "GRILLED_PORK", "PAINTING", "GOLDEN_APPLE", "SIGN", "WOOD_DOOR", "BUCKET", "WATER_BUCKET", "LAVA_BUCKET", "MINECART", "SADDLE", "IRON_DOOR", "REDSTONE", "SNOW_BALL", "BOAT", "LEATHER", "MILK_BUCKET", "CLAY_BRICK", "CLAY_BALL", "SUGAR_CANE", "PAPER", "BOOK", "SLIME_BALL", "STORAGE_MINECART", "POWERED_MINECART", "EGG", "COMPASS", "FISHING_ROD", "WATCH", "GLOWSTONE_DUST", "RAW_FISH", "COOKED_FISH", "INK_SACK", "BONE", "SUGAR", "CAKE", "BED", "DIODE", "COOKIE", "MAP", "SHEARS", "MELON", "PUMPKIN_SEEDS", "MELON_SEEDS", "RAW_BEEF", "COOKED_BEEF", "RAW_CHICKEN", "COOKED_CHICKEN", "ROTTEN_FLESH", "ENDER_PEARL", "BLAZE_ROD", "GHAST_TEAR", "GOLD_NUGGET", "NETHER_STALK", "POTION", "GLASS_BOTTLE", "SPIDER_EYE", "FERMENTED_SPIDER_EYE", "BLAZE_POWDER", "MAGMA_CREAM", "BREWING_STAND_ITEM", "CAULDRON_ITEM", "EYE_OF_ENDER", "SPECKLED_MELON", "MONSTER_EGG", "EXP_BOTTLE", "FIREBALL", "BOOK_AND_QUILL", "WRITTEN_BOOK", "EMERALD", "ITEM_FRAME", "FLOWER_POT_ITEM", "CARROT_ITEM", "POTATO_ITEM", "BAKED_POTATO", "POISONOUS_POTATO", "EMPTY_MAP", "GOLDEN_CARROT", "SKULL_ITEM", "CARROT_STICK", "NETHER_STAR", "PUMPKIN_PIE", "FIREWORK", "FIREWORK_CHARGE", "ENCHANTED_BOOK", "REDSTONE_COMPARATOR", "NETHER_BRICK_ITEM", "QUARTZ", "EXPLOSIVE_MINECART", "HOPPER_MINECART", "PRISMARINE_SHARD", "PRISMARINE_CRYSTALS", "RABBIT", "COOKED_RABBIT", "RABBIT_STEW", "RABBIT_FOOT", "RABBIT_HIDE", "ARMOR_STAND", "IRON_BARDING", "GOLD_BARDING", "DIAMOND_BARDING", "LEASH", "NAME_TAG", "COMMAND_MINECART", "MUTTON", "COOKED_MUTTON", "BANNER", "SPRUCE_DOOR_ITEM", "BIRCH_DOOR_ITEM", "JUNGLE_DOOR_ITEM", "ACACIA_DOOR_ITEM", "DARK_OAK_DOOR_ITEM", "GOLD_RECORD", "GREEN_RECORD", "RECORD_3", "RECORD_4", "RECORD_5", "RECORD_6", "RECORD_7", "RECORD_8", "RECORD_9", "RECORD_10", "RECORD_11", "RECORD_12"]
materials_blacklist = ["BEACON"]
for bl in materials_blacklist:
    materials.remove(bl)
materials_map = {}
vanillas = {}
customs = {}
items = content["items"]
for item in items:
    material = item["material"]
    id = item["id"]
    chance = similar(id, material)
    if ":" in id:
        vanillas[id] = material + ":" + id.split(":")[1]
    if material in materials:
        if chance == 1:
            vanillas[id] = material
            materials.remove(material)
        else:
            if (not (material in materials_map.keys())) or chance > materials_map[material][1]:
                materials_map.update({material: [id, chance]})

for material in materials_map:
    chance = materials_map[material][1]
    id = materials_map[material][0]
    if chance < 0.4:
        continue
    if material in materials:
        vanillas[id] = material
        materials.remove(material)

vanilla_items = {}
custom_items = {}
for item in items:
    custom_items.update({item["id"]: item})

for id in vanillas:
    vanilla_items.update({id: custom_items.pop(id)})

fields = {}
const = {}
categories = []

sample = """
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


// Start class: Items%CATEGORY%
// Category: %CATEGORY%
// Last Update: %LAST_UPDATE%

public class Items%CATEGORY% {

%FIELDS%


    public Items%CATEGORY%(SkyBlock skyBlock) {
        %CONST%

    }

}

"""
count = 0
colors = """
BLACK('0', 0)
DARK_BLUE('1', 1)
DARK_GREEN('2', 2)
DARK_AQUA('3', 3)
DARK_RED('4', 4)
DARK_PURPLE('5', 5)
GOLD('6', 6)
GRAY('7', 7)
DARK_GRAY('8', 8)
BLUE('9', 9)
GREEN('a', 10)
AQUA('b', 11)
RED('c', 12)
LIGHT_PURPLE('d', 13)
YELLOW('e', 14) 
WHITE('f', 15)
MAGIC('k', 16, true)
BOLD('l', 17, true)
STRIKETHROUGH('m', 18, true)
UNDERLINE('n', 19, true)
ITALIC('o', 20, true)
RESET('r', 21)
"""

items = """

"""
all = ""

for id in vanilla_items:
    item = vanilla_items[id]
    description = ""
    durability = "0"
    skin = ""
    rarity = "COMMON"
    category = "VANILLA"
    real_category = "MATERIAL"
    glowing = "false"
    color = "null"
    count += 1
    if "color" in item:
        color = f"Color.fromRGB({item['color']})"
    if "description" in item:
        description = item["description"].replace("\"", "\\\"")
        for color in colors.split("\n"):
            try:
                description = description.replace("%%" + color.split("(")[0].lower() + "%%", "§" + color.split("'")[1])
            except:
                pass
    if "durability" in item:
        durability = str(item["durability"])
    if "skin" in item:
        skin = item["skin"]
    if "tier" in item:
        rarity = item["tier"]
    if "glowing" in item:
        glowing = "true"
    if "category" in item:
        # category = item["category"]
        real_category = item["category"]
        if not item["category"] in categories:
            categories.append(item["category"])
    if bool(re.match("[A-Z].*_GENERATOR_[1-9].*", id)):
        category = "GENERATOR"
    if category in fields:
        fields[category] += "    public static SBCustomItem %s;\n" % id.replace(":", "__")
    else:
        fields.update({category: "    public static SBCustomItem %s;\n" % id.replace(":", "__")})
    if color == "":
        color = "null"
    if category in const:
        const[category] += f"""        {id.replace(":", "__")} = new ItemVanilla("{id}", "{item["name"]}", "{description}", Material.{item["material"]}, (short) {durability}, "{skin}", SBCustomItem.Rarity.{rarity}, {glowing}, {color}, SBCustomItem.Category.{real_category}, skyBlock);
        SBItems.registerItem({id.replace(":", "__")});
"""
    else:
        const.update({category: f"""{        id.replace(":", "__")} = new ItemVanilla("{id}", "{item["name"]}", "{description}", Material.{item["material"]}, (short) {durability}, "{skin}", SBCustomItem.Rarity.{rarity}, {glowing}, {color}, SBCustomItem.Category.{real_category}, skyBlock);
        SBItems.registerItem({id.replace(":", "__")});
"""})
    all += f"public static SBCustomItem " + id.replace(":", "__") + f" = Items" + category + "." + id.replace(":", "__") + ";\n"

    

for id in custom_items:
    item = custom_items[id]
    if id == "SKYBLOCK_MENU":
        continue
    description = ""
    durability = "0"
    skin = ""
    rarity = "COMMON"
    category = "MATERIAL_"
    if count % 3 == 0:
        category += "A"
    elif count % 3 == 1:
        category += "B"
    else: 
        category += "C"
    real_category = "MATERIAL"
    glowing = "false"
    color = "null"
    count += 1
    if "color" in item:
        color = f"Color.fromRGB({item['color']})"
    if "description" in item:
        description = item["description"].replace("\"", "\\\"")
        for color in colors.split("\n"):
            try:
                description = description.replace("%%" + color.split("(")[0].lower() + "%%", "§" + color.split("'")[1])
            except:
                pass
    if "durability" in item:
        durability = str(item["durability"])
    if "skin" in item:
        skin = item["skin"]
    if "tier" in item:
        rarity = item["tier"]
    if "glowing" in item:
        glowing = "true"
    if "category" in item:
        category = item["category"]
        real_category = item["category"]
        if not item["category"] in categories:
            categories.append(item["category"])
    if bool(re.match("[A-Z].*_GENERATOR_[1-9].*", id)):
        category = "GENERATOR"
    if category in fields:
        fields[category] += "    public static SBCustomItem %s;\n" % id.replace(":", "__")
    else:
        fields.update({category: "    public static SBCustomItem %s;\n" % id.replace(":", "__")})
    if color == "":
        color = "null"
    if category in const:
        const[category] += f"""        {id.replace(":", "__")} = new SBCustomItem("{id}", "{item["name"]}", "{description}", Material.{item["material"]}, (short) {durability}, "{skin}", SBCustomItem.Rarity.{rarity}, {glowing}, {color}, SBCustomItem.Category.{real_category}, skyBlock);
        SBItems.registerItem({id.replace(":", "__")});
"""
    else:
        const.update({category: f"""{        id.replace(":", "__")} = new SBCustomItem("{id}", "{item["name"]}", "{description}", Material.{item["material"]}, (short) {durability}, "{skin}", SBCustomItem.Rarity.{rarity}, {glowing}, {color}, SBCustomItem.Category.{real_category}, skyBlock);
        SBItems.registerItem({id.replace(":", "__")});
"""})
    all += f"public static SBCustomItem " + id.replace(":", "__") + f" = Items" + category + "." + id.replace(":", "__") + ";\n"
    


for category in fields.keys():
    content = sample.replace("%CONST%", const[category]).replace("%FIELDS%", fields[category]).replace("%CATEGORY%", category).replace("%LAST_UPDATE%", str(datetime.datetime.now()))
    open("init/Items" + str(category) + ".java", "w").write(content)
    print("new Items" + category + "(skyBlock);")

open("SBItems.txt", "w").write(all)