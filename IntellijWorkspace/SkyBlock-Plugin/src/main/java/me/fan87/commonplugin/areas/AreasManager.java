package me.fan87.commonplugin.areas;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AreasManager {

    @Getter
    private final List<SBArea> areas = new ArrayList<>();
    private final SkyBlock skyBlock;

    public final SBArea VILLAGE = new SBArea("Village", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -129, 55, -137, 66, 135, -3);
    public final SBArea FLOWER_SHOP = new SBArea("Flower Shop", ChatColor.LIGHT_PURPLE, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -16, 68, -27, 2, 118, -7);
    public final SBArea AUCTION_HOUSE = new SBArea("Auction House", ChatColor.GOLD, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -60, 68, -102, -21, 118, -82);
    public final SBArea BANK = new SBArea("Bank", ChatColor.GOLD, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -31, 68, -69, -21, 118, -57);
    public final SBArea FOREST = new SBArea("Forest", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -248, 60, -81, -84, 160, 21);
    public final SBArea FOREST_HOUSE = new SBArea("Forest", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -154, 160, -39, -137, 60, -28);
    public final SBArea VILLAGE_1 = new SBArea("Village", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -129, 65, -80, -84, 115, -57);
    public final SBArea BAZAAR_ALLEY = new SBArea("Bazaar Alley", ChatColor.YELLOW, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -44, 68, -79, -22, 118, -72);
    public final SBArea COLOSSEUM = new SBArea("Colosseum", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 52, 55, -73, 182, 135, -20);
    public final SBArea COLOSSEUM_1 = new SBArea("Colosseum", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 68, 55, -109, 182, 135, -75);
    public final SBArea LIBRARY = new SBArea("Library", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -45, 68, -119, -31, 118, -105);
    public final SBArea MOUNTAIN = new SBArea("Mountains", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -82, 55, -1, 66, 215, 115);
    public final SBArea WILDERNESS = new SBArea("Wilderness", ChatColor.DARK_GREEN, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 68, 52, -18, 208, 152, 222);
    public final SBArea FISHERMANS_HUT = new SBArea("Fisherman's Hut", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 120, 55, 27, 200, 135, 95);
    public final SBArea WIZARD_TOWER = new SBArea("Wizard Tower", ChatColor.LIGHT_PURPLE, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 29, 68, 64, 49, 168, 89);
    public final SBArea HIGH_LEVEL = new SBArea("High Level", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -82, 60, 117, 63, 160, 217);
    public final SBArea RUINS = new SBArea("Ruins", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -269, 60, 23, -84, 160, 173);
    public final SBArea RUINS_1 = new SBArea("Ruins", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -299, 60, 59, -271, 160, 119);
    public final SBArea TAVERN = new SBArea("Tavern", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -84, 65, -61, -72, 115, -50);
    public final SBArea GRAVEYARD = new SBArea("Graveyard", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -219, 72, -136, -84, 122, -81);
    public final SBArea GRAVEYARD_1 = new SBArea("Graveyard", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -173, 72, -232, -70, 122, -138);
    public final SBArea GRAVEYARD_2 = new SBArea("Graveyard", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -83, 72, -137, -65, 122, -108);
    public final SBArea COAL_MINES = new SBArea("Coal Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -186, 38, -167, -77, 66, -72);
    public final SBArea COAL_MINES_1 = new SBArea("Coal Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -196, 65, -111, -181, 70, -101);
    public final SBArea COAL_MINES_2 = new SBArea("Coal Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -80, 38, -144, -45, 66, -124);
    public final SBArea COAL_MINES_3 = new SBArea("Coal Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -78, 38, -134, -62, 66, -102);
    public final SBArea COAL_MINES_4 = new SBArea("Coal Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -69, 55, -238, 15, 105, -138);
    public final SBArea FARM = new SBArea("Farm", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 17, 60, -239, 51, 110, -137);
    public final SBArea FARM_1 = new SBArea("Farm", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 52, 60, -214, 66, 110, -117);
    public final SBArea FARM_2 = new SBArea("Farm", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 68, 60, -196, 153, 110, -111);
    public final SBArea CATACOMBS_ENTRANCE = new SBArea("Catacombs Entrance", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -96, 53, -143, -75, 63, -118);
    public final SBArea GOLD_MINE = new SBArea("Gold Mine", ChatColor.GOLD, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.GOLD_MINE, -103, 16, -406, 47, 156, -266);
    public final SBArea DEEP_CAVERNS = new SBArea("Deep Caverns", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 0, -462, 95, 256, -647);
    public final SBArea THE_BARN = new SBArea("The Barn", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.BARN, 83, 55, -329, 223, 195, -184);
    public final SBArea MUSHROOM_DESERT = new SBArea("Mushroom Desert", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.MUSHROOM_DESERT, 124, 60, -470, 264, 130, -335);
    public final SBArea BIRCH_PARK = new SBArea("Birch Park", ChatColor.GREEN, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.PARK, -487, 81, -145, -307, 166, 90);
    public final SBArea BIRCH_PARK_1 = new SBArea("Birch Park", ChatColor.GREEN, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.PARK, -306, 55, -94, -266, 110, 39);
    public final SBArea SPRUCE_WOODS = new SBArea("Spruce Woods", ChatColor.GREEN, SBArea.AreaCheckType.BIOME, Biome.ICE_PLAINS, WorldsManager.WorldType.PARK, -487, 81, -145, -307, 166, 90);
    public final SBArea SAVANNA_WOODLAND = new SBArea("Savanna Woodland", ChatColor.GREEN, SBArea.AreaCheckType.BIOME, Biome.SAVANNA, WorldsManager.WorldType.PARK, -487, 81, -145, -307, 166, 90);
    public final SBArea DARK_THICKET = new SBArea("Dark Thicket", ChatColor.GREEN, SBArea.AreaCheckType.BIOME, Biome.ROOFED_FOREST, WorldsManager.WorldType.PARK, -487, 81, -145, -307, 166, 90);
    public final SBArea JUNGLE_ISLAND = new SBArea("Jungle Island", ChatColor.GREEN, SBArea.AreaCheckType.BIOME, Biome.JUNGLE, WorldsManager.WorldType.PARK, -487, 81, -145, -307, 166, 90);
    public final SBArea HOWLING_CAVE = new SBArea("Howling Cave", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.PARK, -402, 50, -106, -337, 87, 39);
    public final SBArea SPIDERS_DEN = new SBArea("Spider's Den", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SPIDERS_DEN, -402, 65, -345, -217, 230, -134);
    public final SBArea SPIDERS_DEN_1 = new SBArea("Spider's Den", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SPIDERS_DEN, -257, 65, -370, -127, 230, -210);
    public final SBArea BLAZING_FORTRESS = new SBArea("Blazing Fortress", ChatColor.RED, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.BLAZING_FORTRESS, -444, 35, -766, -169, 230, -373);
    public final SBArea FASHION_SHOP = new SBArea("Fashion Shop", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, 18, 60, -46, 28, 160, -39);
    public final SBArea COMMUNITY_CENTER = new SBArea("Community Center", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -6, 60, -119, 12, 160, -100);
    public final SBArea BLACKSMITH = new SBArea("Blacksmith", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.SKYBLOCK_HUB, -33, 60, -136, -25, 160, -123);
    public final SBArea GUNPOWDER_MINES = new SBArea("Gunpowder Mines", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 134, -462, 95, 175, -647);
    public final SBArea LAPIS_QUARRY = new SBArea("Lapis Quarry", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 113, -462, 95, 132, -647);
    public final SBArea PIGMENS_DEN = new SBArea("Pigmen's Den", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 76, -462, 95, 111, -647);
    public final SBArea SLIMEHILL = new SBArea("Slimehill", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 46, -462, 95, 74, -647);
    public final SBArea DIAMOND_RESERVE = new SBArea("Diamond Reserve", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 21, -462, 95, 44, -647);
    public final SBArea OBSIDIAN_SANCTUARY = new SBArea("Obsidian Sanctuary", ChatColor.AQUA, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.DEEP_CAVERNS, -117, 5, -462, 95, 19, -647);
    public final SBArea THE_END = new SBArea("The End", ChatColor.LIGHT_PURPLE, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.END, -795, 0, -438, -1122, 225, -394);
    public final SBArea DRAGONS_NEST = new SBArea("Dragon's Nest", ChatColor.DARK_PURPLE, SBArea.AreaCheckType.POSITION, null, WorldsManager.WorldType.END, -750, 5, -351, -1271, 107, -474);


    public AreasManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;

        for (Field field : getClass().getFields()) {
            if (field.getType() == SBArea.class) {
                try {
                    registerArea((SBArea) field.get(this));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        loadAreaData();
    }

    public void registerArea(SBArea area) {
        areas.add(area);
    }

    public final static List<Long> areaGettingPerformance = new ArrayList<>();

    public SBArea getAreaOf(Location location) {
        long before = System.currentTimeMillis();
        for (SBArea sbLocation : areas.stream().sorted(Comparator.comparingDouble(o -> Math.abs((o.getFromX() - o.getToX()) * (o.getFromY() - o.getToY()) * (o.getFromZ() - o.getToZ())))).collect(Collectors.toList())) {
            if (sbLocation.match(skyBlock, location)) {
                areaGettingPerformance.add(System.currentTimeMillis() - before);
                return sbLocation;
            }
        }
        areaGettingPerformance.add(System.currentTimeMillis() - before);
        return null;
    }

    public void loadAreaData() {
        for (SBArea area : areas) {

        }
    }

    public List<Material> getOresToGenerate(SBArea area) {
        if (area == COAL_MINES || area == COAL_MINES_1 || area == COAL_MINES_2 || area == COAL_MINES_3 || area == COAL_MINES_4) {
            return Arrays.asList(Material.COAL_ORE);
        }
        if (area == GOLD_MINE) {
            return Arrays.asList(Material.GOLD_ORE, Material.IRON_ORE, Material.COAL_ORE);
        }
        if (area == DIAMOND_RESERVE) {
            return Arrays.asList(Material.DIAMOND_ORE);
        }
        if (area == OBSIDIAN_SANCTUARY) {
            return Arrays.asList(Material.DIAMOND_ORE, Material.OBSIDIAN, Material.DIAMOND_BLOCK);
        }
        if (area == GUNPOWDER_MINES) {
            return Arrays.asList(Material.GOLD_ORE, Material.IRON_ORE, Material.COAL_ORE);
        }
        if (area == LAPIS_QUARRY) {
            return Arrays.asList(Material.LAPIS_ORE);
        }
        if (area == PIGMENS_DEN) {
            return Arrays.asList(Material.REDSTONE_ORE);
        }
        if (area == SLIMEHILL) {
            return Arrays.asList(Material.EMERALD_ORE);
        }
        return Arrays.asList();
    }

    public boolean canMineLogs(SBArea area) {
        return area != null && area.getWorldType() == WorldsManager.WorldType.PARK || area == FOREST;
    }

}
