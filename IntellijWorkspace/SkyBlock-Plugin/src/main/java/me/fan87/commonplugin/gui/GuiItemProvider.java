package me.fan87.commonplugin.gui;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Bukkit;
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
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, Short.parseShort("15"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getSkull(String texture, String uuid) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        NBTItem nbtItem = new NBTItem(item, true);
        NBTCompound skullOwner = nbtItem.addCompound("SkullOwner");
        skullOwner.setString("Id", uuid);
        NBTCompound properties = skullOwner.addCompound("Properties");
        NBTListCompound textures = properties.getCompoundList("textures").addCompound();
        textures.setString("Value", texture);
        return item;
    }

    public static ItemStack getMenuSkull(SBPlayer player) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM);
        SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        List<String> lores = new ArrayList<>();
        for (Field declaredField : player.getStats().getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                Object value = declaredField.get(player.getStats());
                if (value instanceof SBStat) {
                    SBStat stat = ((SBStat) value);
                    lores.add("  " + stat.getColor() + stat.getIcon() + " " + stat.getName() + " " + ChatColor.WHITE + stat.getValueDisplay(stat.getValue()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        meta.setOwner(player.getPlayer().getName());
        skull.setItemMeta(meta);
        return skull;
    }

}
