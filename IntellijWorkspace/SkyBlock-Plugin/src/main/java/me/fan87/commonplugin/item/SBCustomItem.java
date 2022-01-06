package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LoreUtils;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private final String skin;

    @Getter
    private final short damage;

    @Getter
    private final Rarity rarity;

    @Getter
    private final Category category;

    @Getter
    private final boolean glowing;


    public SBCustomItem(String namespace, String displayName, String description, Material material, SkyBlock skyBlock) {
        this(namespace, displayName, description, material, (short) 0, "", Rarity.COMMON, false, Category.MATERIAL, skyBlock);
    }
    public SBCustomItem(String namespace, String displayName, String description, Material material, short damage, String skin, Rarity rarity, boolean glowing, Category category, SkyBlock skyBlock) {
        this.namespace = namespace;
        this.material = material;
        this.displayName = displayName;
        this.description = description;
        this.skin = skin;
        this.rarity = rarity;
        this.category = category;
        this.damage = damage;
        this.skyBlock = skyBlock;
        this.glowing = glowing;
    }

    public SBCustomItem(String namespace, String displayName, Material material, SkyBlock skyBlock) {
        this(namespace, displayName, "", material, skyBlock);
    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {
        // Todo: Implement
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
        if (!getDescription().equals("") && shouldDisplayRarity()) {
            out.addAll(LoreUtils.splitLoreForLine("§7" + getDescription()));
            out.add("");
        }
        out.add(rarity.getColor() + ChatColor.BOLD.toString() + rarity.getName());
        return out;
    }



    public boolean activateForSlot(int slot, SBPlayer player) {
        return true;
    }

    public ItemStack newItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial(), 1, damage);
        NBTItem nbt = new NBTItem(itemStack, true);
        NBTCompound extraAttributes = nbt.addCompound("ExtraAttributes");
        extraAttributes.setString("id", getNamespace());
        if (!skin.equals("")) {
            NBTCompound skullOwner = nbt.addCompound("SkullOwner");
            skullOwner.setString("Id", UUID.randomUUID().toString());
            NBTCompound properties = skullOwner.addCompound("Properties");
            NBTListCompound textures = properties.getCompoundList("textures").addCompound();
            textures.setString("Value", skin);
        }
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

        if (isGlowing()) {
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }
        applyExtraAttributes(extraAttributes);
        return itemStack;
    }

    public void applyExtraAttributes(NBTCompound compound) {

    }

    @AllArgsConstructor
    @Getter
    public enum Category {
        MATERIAL, BOW, BOOTS, SPADE, PICKAXE, AXE, SWORD, HELMET, LEGGINGS, SHEARS, CHESTPLATE, HOE, FISHING_ROD, ARROW, PET_ITEM, REFORGE_STONE, COSMETIC, ACCESSORY, TRAVEL_SCROLL, BAIT, DUNGEON_PASS, ARROW_POISON, WAND, DRILL, FISHING_WEAPON, GAUNTLET
    }

    @AllArgsConstructor
    @Getter
    public enum Rarity {
        COMMON("Common", ChatColor.WHITE),
        UNCOMMON("Uncommon", ChatColor.GREEN),
        RARE("Rare", ChatColor.BLUE),
        EPIC("Epic", ChatColor.DARK_PURPLE),
        LEGENDARY("Legendary", ChatColor.GOLD),
        MYTHIC("Mythic", ChatColor.LIGHT_PURPLE),
        DIVINE("Divine", ChatColor.AQUA),
        SPECIAL("Special", ChatColor.RED),
        VERY_SPECIAL("Very Special", ChatColor.RED),
        ;
        private String name;
        private ChatColor color;

        public static Rarity getRarityByVanillaRarity(EnumItemRarity rarity) {
            if (rarity == EnumItemRarity.COMMON) return COMMON;
            return UNCOMMON;
        }
    }

    @Subscribe
    public void antiCrash(EventManager eventManager) {}

}
