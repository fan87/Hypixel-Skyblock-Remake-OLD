package me.fan87.commonplugin.players.tradings;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.addon.exceptions.UnknownAddonError;
import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.item.init.ItemsVanilla;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import me.fan87.commonplugin.players.tradings.tradable.impl.ItemTradable;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SBTradings {

    private static Map<SBNamespace, SBTrading> registeredTradings = new HashMap<>();



    public static SBTrading GRASS = new SBTrading(
            new SBNamespace("default", "GRASS_BLOCK"),
            new SBTradable[]{ new ItemTradable(new SBItemVector(ItemsVanilla.DIRT, 4))},
            new SBItemVector(ItemsVanilla.GRASS, 4), true);


    static {
        registerTradingWithoutChecking(GRASS);
        for (Field declaredField : SBTrading.class.getDeclaredFields()) {
            if (declaredField.getType() == SBTrading.class) {
                try {
                    SBTrading trading = (SBTrading) declaredField.get(null);
                    registerTradingWithoutChecking(trading);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Registering Trade: " + trading.getNamespace());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void registerTrading(SBTrading trading) {
        String addonName = trading.getNamespace().getAddonName();
        SBAddon addon = SkyBlock.getPlugin(SkyBlock.class).getAddon(addonName);
        if (addon != null) throw new UnknownAddonError(addonName);
        if (registeredTradings.containsKey(new SBNamespace(addonName, trading.getNamespace().getNamespace()))) {
            throw new IllegalArgumentException("Trading namespace already taken: " + addonName + "::" + trading.getNamespace());
        }
        registerTradingWithoutChecking(trading);
    }

    private static void registerTradingWithoutChecking(SBTrading trading) {
        registeredTradings.put(new SBNamespace(trading.getNamespace().getAddonName(), trading.getNamespace().getNamespace()), trading);
    }

    public static Map<SBNamespace, SBTrading> getRegisteredTradings() {
        return new HashMap<>(registeredTradings);
    }
    public static SBTrading getTradingByNamespace(SBNamespace namespace) {
        return registeredTradings.get(namespace);
    }
    public static SBTrading getTradingByNamespace(String namespace) {
        String addonName = "default";
        String name = namespace;
        if (namespace.contains("::")) {
            addonName = namespace.split("::")[0];
            name = namespace.split("::")[1];
        }
        return getTradingByNamespace(new SBNamespace(addonName, name));
    }
}
