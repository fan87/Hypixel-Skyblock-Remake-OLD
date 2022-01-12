package me.fan87.commonplugin.enchantment;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class SBEnchantment {

    private final SBNamespace namespace;
    private final String displayName;
    protected final SkyBlock skyBlock;
    @Getter
    private final int maxEnchantingTableLevel;

    public SBEnchantment(SkyBlock skyBlock, SBNamespace namespace, String displayName, int maxEnchantingTableLevel) {
        this.skyBlock = skyBlock;
        this.namespace = namespace;
        this.displayName = displayName;
        this.maxEnchantingTableLevel = maxEnchantingTableLevel;
    }

    public SBNamespace getNamespace() {
        return namespace;
    }
    public String getDisplayName() {
        return displayName;
    }

    public Map<Enchantment, Integer> getVanillaEnchantment(int level) {
        HashMap<Enchantment, Integer> enchantmentIntegerHashMap = new HashMap<>();
        enchantmentIntegerHashMap.put(Enchantment.DURABILITY, 1);
        return enchantmentIntegerHashMap;
    }

    public boolean isCompatibleWith(SBEnchantment enchantment) {
        return true;
    }

    public boolean isItemAccepted(SBCustomItem item) {
        return item.getCategory() == SBCustomItem.Category.SWORD;
    }

    public String getDescription(ChatColor color, int level) {
        return color + "Empty Description.";
    }

}
