package me.fan87.commonplugin.enchantment;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.addon.exceptions.UnknownAddonError;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.utils.SBNamespace;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Using {@link SkyBlock#getPlugin(Class)}
 */
public class SBEnchantments {

    protected static final SkyBlock skyBlock = SkyBlock.getPlugin(SkyBlock.class);


    private static final Map<SBNamespace, SBEnchantment> registeredEnchantments = new HashMap<>();

    static {
        for (Field declaredField : SBEnchantments.class.getDeclaredFields()) {
            if (declaredField.getType() == SBEnchantment.class && Modifier.isStatic(declaredField.getModifiers())) {
                try {
                    forceRegisterEnchantment(((SBEnchantment) declaredField.get(null)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerEnchantment(SBEnchantment enchantment) {
        SBNamespace namespace = enchantment.getNamespace();
        SBAddon addon = skyBlock.getAddon(namespace.getAddonName());
        if (addon == null) {
            throw new UnknownAddonError(namespace.getAddonName());
        }
        forceRegisterEnchantment(enchantment);
    }

    protected static void forceRegisterEnchantment(SBEnchantment enchantment) {
        if (enchantment.getNamespace() == null) {
            throw new NullPointerException("Namespace could not be null (" + enchantment.getClass().getName() + ")");
        } else {
            if (enchantment.getNamespace().getNamespace() == null) throw new NullPointerException("Namespace could not be null (" + enchantment.getClass().getName() + ".getNamespace().getNamespace() == null)");
            if (enchantment.getNamespace().getAddonName() == null) throw new NullPointerException("Addon Name could not be null (" + enchantment.getClass().getName() + ".getNamespace().getAddonName() == null)");
        }
        if (getEnchantment(enchantment.getNamespace()) == null) throw new IllegalArgumentException("Enchantment namespace already taken");
        EventManager.EVENT_BUS.register(enchantment);
        registeredEnchantments.put(enchantment.getNamespace(), enchantment);
    }

    public static SBEnchantment getEnchantment(SBNamespace namespace) {
        return registeredEnchantments.get(namespace);
    }

}
