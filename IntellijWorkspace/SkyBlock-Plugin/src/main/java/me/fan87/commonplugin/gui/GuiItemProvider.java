package me.fan87.commonplugin.gui;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GuiItemProvider {

    public static ItemStack backgroundGlassPane() {
        return backgroundGlassPane(15);
    }

    public static ItemStack backgroundGlassPane(int color) {
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) color);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getSkull(String texture, String uuid) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        return getSkull(item, texture, uuid);
    }

    public static ItemStack getSkull(ItemStack item, String texture, String uuid) {
        assert item.getType() == Material.SKULL_ITEM;
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", uuid);
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", texture);
        return item;
    }

    public static ItemStack getMenuSkull(SBPlayer player, boolean yours) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        List<String> lores = new ArrayList<>();
        for (Field declaredField : player.getStats().getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                Object value = declaredField.get(player.getStats());
                if (value instanceof SBStat) {
                    SBStat stat = ((SBStat) value);
                    lores.add("  " + stat.getColor() + stat.getIcon() + " " + stat.getName() + " " + ChatColor.WHITE + stat.getValueDisplay(stat.getValue(player)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        meta.setLore(lores);
        if (!yours) {meta.setDisplayName(ChatColor.GREEN + player.getPlayer().getName() + "'s Profile");} else {meta.setDisplayName("§aYour SkyBlock Profile");}
        skull.setItemMeta(meta);
        getSkull(skull, player.getSkin(), player.getPlayer().getUniqueId().toString());
        return skull;
    }

    public static ItemStack getPreviousPageItem(String pageName) {
        ItemStack itemStack = new ItemStack(Material.ARROW);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§aGo Back");
        List<String> lores = new ArrayList<>();
        lores.add("§7To " + pageName);
        itemMeta.setLore(lores);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack getClosePageItem() {
        ItemStack itemStack = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§cClose");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
