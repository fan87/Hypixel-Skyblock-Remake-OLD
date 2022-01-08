package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.LoreUtils;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class SBCustomItem {

    protected final SkyBlock skyBlock;

    @Getter
    private final String namespace;

    @Getter
    private final String displayName;

    @Getter
    private final Material material;

    @Getter
    private final String description;

    @Getter
    private final short durability;

    @Getter
    private final Rarity rarity;

    @Getter
    private final Category category;

    @Getter
    private final RecipeCategory recipeCategory;

    @Setter
    private final boolean unlockedByDefault;



    public SBCustomItem(String namespace, String displayName, String description, Material material, short durability, Rarity rarity, Category category, SkyBlock skyBlock, RecipeCategory recipeCategory, boolean unlockedByDefault) {
        this.namespace = namespace;
        this.material = material;
        this.displayName = displayName;
        this.description = description;
        this.rarity = rarity;
        this.category = category;
        this.durability = durability;
        this.skyBlock = skyBlock;
        this.recipeCategory = recipeCategory;
        this.unlockedByDefault = unlockedByDefault;
    }

    public SBCustomItem(String namespace, String displayName, String description, Material material, short durability, Rarity rarity, Category category, SkyBlock skyBlock, RecipeCategory recipeCategory) {
        this(namespace, displayName, description, material, durability, rarity, category, skyBlock, recipeCategory, false);
    }


    public boolean shouldDisplayRarity() {
        return true;
    }

    public List<String> getLores(SBItemStack itemStack) {
        if (itemStack.getType().getItem() != this) {
            return new ArrayList<>();
        }
        return getLores();
    }

    public List<String> getLores() {
        List<String> out = new ArrayList<>();
        out.addAll(LoreUtils.splitLoreForLine("§7" + getDescription()));
        if (!getDescription().equals("") && shouldDisplayRarity()) {
            out.add("");
            out.add(rarity.getColor() + ChatColor.BOLD.toString() + rarity.getName());
        }
        return out;
    }



    public boolean activateForSlot(int slot, SBPlayer player) {
        return true;
    }

    public ItemStack newItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial(), 1, durability);
        NBTItem nbt = new NBTItem(itemStack, true);
        NBTCompound extraAttributes = nbt.addCompound("ExtraAttributes");
        extraAttributes.setString("id", getNamespace());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        try {
            itemMeta.spigot().setUnbreakable(true);
        } catch (Exception ignored) {}
        itemStack.setItemMeta(itemMeta);
        applyExtraAttributes(extraAttributes);
        return itemStack;
    }

    public SBRecipe getRecipe() {
        for (SBRecipe craftingRecipe : skyBlock.getRecipesManager().getCraftingRecipes()) {
            if (craftingRecipe.getOutputType() == this) return craftingRecipe;
        }
        return null;
    }

    protected void extraProcessItemStack() {

    }

    public void applyExtraAttributes(NBTCompound compound) {

    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {

    }

    public boolean isRecipeUnlockedByDefault() {
        return unlockedByDefault;
    }

    @AllArgsConstructor
    @Getter
    public enum Category {
        MATERIAL, BOW, BOOTS, SPADE, PICKAXE, AXE, SWORD, HELMET, LEGGINGS, SHEARS, CHESTPLATE, HOE, FISHING_ROD, ARROW, PET_ITEM, REFORGE_STONE, COSMETIC, ACCESSORY, TRAVEL_SCROLL, BAIT, DUNGEON_PASS, ARROW_POISON, WAND, DRILL, FISHING_WEAPON, GAUNTLET
    }

    @AllArgsConstructor
    @Getter
    public enum Rarity {
        COMMON("COMMON", ChatColor.WHITE),
        UNCOMMON("UNCOMMON", ChatColor.GREEN),
        RARE("RARE", ChatColor.BLUE),
        EPIC("EPIC", ChatColor.DARK_PURPLE),
        LEGENDARY("LEGENDARY", ChatColor.GOLD),
        MYTHIC("MYTHIC", ChatColor.LIGHT_PURPLE),
        DIVINE("DIVINE", ChatColor.AQUA),
        SPECIAL("SPECIAL", ChatColor.RED),
        VERY_SPECIAL("VERY SPECIAL", ChatColor.RED),
        ;
        private String name;
        private ChatColor color;

        public static Rarity getRarityByVanillaRarity(EnumItemRarity rarity) {
            if (rarity == EnumItemRarity.COMMON) return COMMON;
            return UNCOMMON;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum RecipeCategory {
        FARMING("Farming", Material.GOLD_HOE, (short) 0),
        MINING("Mining", Material.STONE_PICKAXE, (short) 0),
        COMBAT("Combat", Material.STONE_SWORD, (short) 0),
        FISHING("Fishing", Material.FISHING_ROD, (short) 0),
        FORAGING("Foraging", Material.SAPLING, (short) 3),
        ENCHANTING("Enchanting", Material.ENCHANTMENT_TABLE, (short) 0),
        ALCHEMY("Alchemy", Material.BREWING_STAND_ITEM, (short) 0),
        CARPENTRY("Carpentry", Material.WORKBENCH, (short) 0),
        SLAYER("Slayer", Material.BOW, (short) 0),
        SPECIAL("Special", Material.NETHER_STAR, (short) 0),
        ;

        private String name;
        private Material icon;
        private short durability;
    }

    @Subscribe
    public void antiCrash(EventManager eventManager) {}

}
