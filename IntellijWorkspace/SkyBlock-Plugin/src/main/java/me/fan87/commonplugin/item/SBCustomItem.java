package me.fan87.commonplugin.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.RomanUtils;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        List<String> out = new ArrayList<>();
        if (itemStack.getEnchantments().size() > 0) {
            if (itemStack.getEnchantments().size() > 5) {
                Map<SBEnchantment, Integer> enchantments = itemStack.getEnchantments();
                List<String> ench = new ArrayList<>();
                for (SBEnchantment enchantment : enchantments.keySet()) {
                    ench.add(ChatColor.BLUE + enchantment.getDisplayName() + " " + RomanUtils.toRoman(enchantments.get(enchantment)));
                }
                out.addAll(LoreUtils.splitLoreForLine(ChatColor.BLUE + String.join(ChatColor.BLUE + ", ", ench)));
                out.add("");
            } else {
                for (SBEnchantment enchantment : itemStack.getEnchantments().keySet()) {
                    out.add(ChatColor.BLUE + enchantment.getDisplayName() + " " + RomanUtils.toRoman(itemStack.getEnchantments().get(enchantment)));
                    out.addAll(LoreUtils.splitLoreForLine(ChatColor.GRAY + enchantment.getDescription(ChatColor.GRAY, itemStack.getEnchantmentLevel(enchantment))));
                    out.add("");
                }
            }
        }
        if (!getDescription().equals("")) {
            out.addAll(LoreUtils.splitLoreForLine("§7" + getDescription()));
            out.add("");
        }
        if (shouldDisplayRarity()) {
            out.add(rarity.getColor() + ChatColor.BOLD.toString() + rarity.getName());
        }
        return out;
    }



    public boolean isInActive(int heldSlot, int slot, SBPlayer player) {
        return getCategory().getActiveChecker().isActive(heldSlot, player.getPlayer().getInventory(), slot);
    }

    public boolean isUnbreakable() {
        return true;
    }

    public ItemStack newItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial(), 1, durability);
        net.minecraft.server.v1_8_R3.ItemStack itemStack1 = CraftItemStack.asNMSCopy(itemStack);
//        NBTItem nbt = new NBTItem(itemStack, true);
//        NBTCompound extraAttributes = nbt.addCompound("ExtraAttributes");
//        extraAttributes.setString("id", getNamespace());
        NBTTagCompound nbtBase = new NBTTagCompound();
        if (itemStack1.getTag() == null) {
            itemStack1.setTag(new NBTTagCompound());
        }
        itemStack1.getTag().set("ExtraAttributes", nbtBase);
        nbtBase.setString("id", getNamespace());
        itemStack = CraftItemStack.asCraftMirror(itemStack1);
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
        applyExtraAttributes(nbtBase);
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

    public void applyExtraAttributes(NBTTagCompound compound) {

    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {

    }

    public boolean isRecipeUnlockedByDefault() {
        return unlockedByDefault;
    }

    @AllArgsConstructor
    @Getter
    public enum Category {
        MATERIAL((heldSlot, inventory, slot) -> false),
        BOW((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        HELMET((heldSlot, inventory, slot) ->  slot == 5),
        CHESTPLATE((heldSlot, inventory, slot) -> slot == 6),
        LEGGINGS((heldSlot, inventory, slot) -> slot == 7),
        BOOTS((heldSlot, inventory, slot) -> slot == 8),
        SPADE((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        PICKAXE((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        AXE((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        SWORD((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        SHEARS((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        HOE((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        FISHING_ROD((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        ARROW((heldSlot, inventory, slot) -> false),
        PET_ITEM((heldSlot, inventory, slot) -> false),
        REFORGE_STONE((heldSlot, inventory, slot) -> false),
        COSMETIC((heldSlot, inventory, slot) -> false),
        ACCESSORY((heldSlot, inventory, slot) -> true),
        TRAVEL_SCROLL((heldSlot, inventory, slot) -> false),
        BAIT((heldSlot, inventory, slot) -> false),
        DUNGEON_PASS((heldSlot, inventory, slot) -> false),
        ARROW_POISON((heldSlot, inventory, slot) -> false),
        WAND((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        DRILL((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        FISHING_WEAPON((heldSlot, inventory, slot) -> heldSlot == slot - 36),
        GAUNTLET((heldSlot, inventory, slot) -> heldSlot == slot - 36);

        private ActiveChecker activeChecker;

        public boolean isTool() {
            return this == SPADE || this == DRILL || this == SHEARS || this == AXE || this == PICKAXE || this == GAUNTLET;
        }

        public boolean isWeapon() {
            return this == BOW || this == SWORD || this == AXE || this == FISHING_WEAPON || this == GAUNTLET || this == WAND;
        }

        public boolean hasDurability() {
            return isTool() || isWeapon() || this == FISHING_ROD;
        }
    }

    public interface ActiveChecker {
        boolean isActive(int heldSlot, PlayerInventory inventory, int slot);
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

    @Subscribe()
    public void antiCrash(EventManager eventManager) {}

}
