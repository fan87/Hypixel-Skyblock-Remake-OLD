package me.fan87.commonplugin.item;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LoreUtils;
import net.minecraft.server.v1_8_R3.EnumItemRarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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


    public SBCustomItem(String namespace, String displayName, String description, Material material, SkyBlock skyBlock) {
        this.namespace = namespace;
        this.material = material;
        this.displayName = displayName;
        this.description = description;
        this.skyBlock = skyBlock;
    }

    public SBCustomItem(String namespace, String displayName, Material material, SkyBlock skyBlock) {
        this(namespace, displayName, "", material, skyBlock);
    }

    public void updatePlayerStats(SBPlayer player, int inventoryIndex) {

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
        return out;
    }



    public boolean activateForSlot(int slot, SBPlayer player) {
        return true;
    }

    public ItemStack newItemStack() {
        ItemStack itemStack = new ItemStack(getMaterial());
        NBTItem nbt = new NBTItem(itemStack, true);
        NBTCompound extraAttributes = nbt.addCompound("ExtraAttributes");
        extraAttributes.setString("id", getNamespace());
        applyExtraAttributes(extraAttributes);
        return itemStack;
    }

    public void applyExtraAttributes(NBTCompound compound) {

    }

    @AllArgsConstructor
    @Getter
    public enum Rarity {
        COMMON("Common", ChatColor.WHITE),
        UNCOMMON("Uncommon", ChatColor.GREEN),
        RARE("Rare", ChatColor.DARK_BLUE),
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

}
