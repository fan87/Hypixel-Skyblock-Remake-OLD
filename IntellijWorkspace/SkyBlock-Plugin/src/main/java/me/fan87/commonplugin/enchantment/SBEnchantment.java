package me.fan87.commonplugin.enchantment;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.enchantments.Enchantment;

import java.util.HashMap;
import java.util.Map;

public class SBEnchantment {

    private final SBNamespace namespace;
    private final String displayName;
    protected final SkyBlock skyBlock;

    public SBEnchantment(SkyBlock skyBlock, SBNamespace namespace, String displayName) {
        this.skyBlock = skyBlock;
        this.namespace = namespace;
        this.displayName = displayName;
    }

    public SBNamespace getNamespace() {
        return namespace;
    }
    public String getDisplayName() {
        return displayName;
    }

    public Map<Enchantment, Integer> getVanillaEnchantment() {
        return new HashMap<>();
    }

    public boolean isCompatibleWith(SBEnchantment enchantment) {
        return true;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean isItemAccepted(SBCustomItem item) {
        return item.getCategory() == SBCustomItem.Category.SWORD;
    }

}
