package me.fan87.commonplugin.players.collections;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.SneakyThrows;
import me.fan87.commonplugin.players.collections.impl.combat.*;
import me.fan87.commonplugin.players.collections.impl.farming.*;
import me.fan87.commonplugin.players.collections.impl.fishing.*;
import me.fan87.commonplugin.players.collections.impl.foraging.*;
import me.fan87.commonplugin.players.collections.impl.mining.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@JsonAutoDetect(setterVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, creatorVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class SBPlayerCollections {

    @JsonProperty("INK_SACK:3")
    public CollectionInkSack3 INK_SACK__3 = new CollectionInkSack3();
    @JsonProperty("CARROT_ITEM")
    public CollectionCarrotItem CARROT_ITEM = new CollectionCarrotItem();
    @JsonProperty("CACTUS")
    public CollectionCactus CACTUS = new CollectionCactus();
    @JsonProperty("RAW_CHICKEN")
    public CollectionRawChicken RAW_CHICKEN = new CollectionRawChicken();
    @JsonProperty("SUGAR_CANE")
    public CollectionSugarCane SUGAR_CANE = new CollectionSugarCane();
    @JsonProperty("PUMPKIN")
    public CollectionPumpkin PUMPKIN = new CollectionPumpkin();
    @JsonProperty("WHEAT")
    public CollectionWheat WHEAT = new CollectionWheat();
    @JsonProperty("SEEDS")
    public CollectionSeeds SEEDS = new CollectionSeeds();
    @JsonProperty("MUSHROOM_COLLECTION")
    public CollectionMushroomCollection MUSHROOM_COLLECTION = new CollectionMushroomCollection();
    @JsonProperty("RABBIT")
    public CollectionRabbit RABBIT = new CollectionRabbit();
    @JsonProperty("NETHER_STALK")
    public CollectionNetherStalk NETHER_STALK = new CollectionNetherStalk();
    @JsonProperty("MUTTON")
    public CollectionMutton MUTTON = new CollectionMutton();
    @JsonProperty("MELON")
    public CollectionMelon MELON = new CollectionMelon();
    @JsonProperty("POTATO_ITEM")
    public CollectionPotatoItem POTATO_ITEM = new CollectionPotatoItem();
    @JsonProperty("LEATHER")
    public CollectionLeather LEATHER = new CollectionLeather();
    @JsonProperty("PORK")
    public CollectionPork PORK = new CollectionPork();
    @JsonProperty("FEATHER")
    public CollectionFeather FEATHER = new CollectionFeather();
    @JsonProperty("INK_SACK:4")
    public CollectionInkSack4 INK_SACK__4 = new CollectionInkSack4();
    @JsonProperty("REDSTONE")
    public CollectionRedstone REDSTONE = new CollectionRedstone();
    @JsonProperty("COAL")
    public CollectionCoal COAL = new CollectionCoal();
    @JsonProperty("ENDER_STONE")
    public CollectionEnderStone ENDER_STONE = new CollectionEnderStone();
    @JsonProperty("QUARTZ")
    public CollectionQuartz QUARTZ = new CollectionQuartz();
    @JsonProperty("SAND")
    public CollectionSand SAND = new CollectionSand();
    @JsonProperty("IRON_INGOT")
    public CollectionIronIngot IRON_INGOT = new CollectionIronIngot();
    @JsonProperty("OBSIDIAN")
    public CollectionObsidian OBSIDIAN = new CollectionObsidian();
    @JsonProperty("DIAMOND")
    public CollectionDiamond DIAMOND = new CollectionDiamond();
    @JsonProperty("COBBLESTONE")
    public CollectionCobblestone COBBLESTONE = new CollectionCobblestone();
    @JsonProperty("GLOWSTONE_DUST")
    public CollectionGlowstoneDust GLOWSTONE_DUST = new CollectionGlowstoneDust();
    @JsonProperty("GOLD_INGOT")
    public CollectionGoldIngot GOLD_INGOT = new CollectionGoldIngot();
    @JsonProperty("GRAVEL")
    public CollectionGravel GRAVEL = new CollectionGravel();
    @JsonProperty("EMERALD")
    public CollectionEmerald EMERALD = new CollectionEmerald();
    @JsonProperty("ICE")
    public CollectionIce ICE = new CollectionIce();
    @JsonProperty("NETHERRACK")
    public CollectionNetherrack NETHERRACK = new CollectionNetherrack();
    @JsonProperty("ENDER_PEARL")
    public CollectionEnderPearl ENDER_PEARL = new CollectionEnderPearl();
    @JsonProperty("SLIME_BALL")
    public CollectionSlimeBall SLIME_BALL = new CollectionSlimeBall();
    @JsonProperty("MAGMA_CREAM")
    public CollectionMagmaCream MAGMA_CREAM = new CollectionMagmaCream();
    @JsonProperty("GHAST_TEAR")
    public CollectionGhastTear GHAST_TEAR = new CollectionGhastTear();
    @JsonProperty("SULPHUR")
    public CollectionSulphur SULPHUR = new CollectionSulphur();
    @JsonProperty("ROTTEN_FLESH")
    public CollectionRottenFlesh ROTTEN_FLESH = new CollectionRottenFlesh();
    @JsonProperty("SPIDER_EYE")
    public CollectionSpiderEye SPIDER_EYE = new CollectionSpiderEye();
    @JsonProperty("BONE")
    public CollectionBone BONE = new CollectionBone();
    @JsonProperty("BLAZE_ROD")
    public CollectionBlazeRod BLAZE_ROD = new CollectionBlazeRod();
    @JsonProperty("STRING")
    public CollectionString STRING = new CollectionString();
    @JsonProperty("LOG_2")
    public CollectionLog2 LOG_2 = new CollectionLog2();
    @JsonProperty("LOG:1")
    public CollectionLog1 LOG__1 = new CollectionLog1();
    @JsonProperty("LOG:3")
    public CollectionLog3 LOG__3 = new CollectionLog3();
    @JsonProperty("LOG:2")
    public CollectionLog2 LOG__2 = new CollectionLog2();
    @JsonProperty("LOG")
    public CollectionLog LOG = new CollectionLog();
    @JsonProperty("LOG_2:1")
    public CollectionLog21 LOG_2__1 = new CollectionLog21();
    @JsonProperty("WATER_LILY")
    public CollectionWaterLily WATER_LILY = new CollectionWaterLily();
    @JsonProperty("PRISMARINE_SHARD")
    public CollectionPrismarineShard PRISMARINE_SHARD = new CollectionPrismarineShard();
    @JsonProperty("INK_SACK")
    public CollectionInkSack INK_SACK = new CollectionInkSack();
    @JsonProperty("RAW_FISH")
    public CollectionRawFish RAW_FISH = new CollectionRawFish();
    @JsonProperty("RAW_FISH:3")
    public CollectionRawFish3 RAW_FISH__3 = new CollectionRawFish3();
    @JsonProperty("RAW_FISH:2")
    public CollectionRawFish2 RAW_FISH__2 = new CollectionRawFish2();
    @JsonProperty("RAW_FISH:1")
    public CollectionRawFish1 RAW_FISH__1 = new CollectionRawFish1();
    @JsonProperty("PRISMARINE_CRYSTALS")
    public CollectionPrismarineCrystals PRISMARINE_CRYSTALS = new CollectionPrismarineCrystals();
    @JsonProperty("CLAY_BALL")
    public CollectionClayBall CLAY_BALL = new CollectionClayBall();
    @JsonProperty("SPONGE")
    public CollectionSponge SPONGE = new CollectionSponge();

    @SneakyThrows
    public SBCollection[] getCollections() {
        List<SBCollection> collectionList = new ArrayList<>();
        for (Field declaredField : getClass().getDeclaredFields()) {
            if (declaredField.getType().getSuperclass() == SBCollection.class) {
                collectionList.add(((SBCollection) declaredField.get(this)));
            }
        }
        return collectionList.toArray(new SBCollection[0]);
    }

    public int getMaxedOutCollections() {
        return getMaxedOutCollections(c -> true);
    }

    public int getUnlockedCollections() {
        return getUnlockedCollections(c -> true);
    }

    public int getMaxedOutCollections(Predicate<SBCollection> filter) {
        int amount = 0;
        for (SBCollection collection : getCollections()) {
            if (collection.isMaxedOut() && filter.test(collection)) {
                amount++;
            }
        }
        return amount;
    }

    public int getUnlockedCollections(Predicate<SBCollection> filter) {
        int amount = 0;
        for (SBCollection collection : getCollections()) {
            if (collection.getCollected() > 0 && filter.test(collection)) {
                amount++;
            }
        }
        return amount;
    }

    public SBCollection[] getCollectionsByType(SBCollection.CollectionType type) {
        return Arrays.stream(getCollections()).filter(c -> c.getCollectionType() == type).collect(Collectors.toList()).toArray(new SBCollection[0]);
    }

}
