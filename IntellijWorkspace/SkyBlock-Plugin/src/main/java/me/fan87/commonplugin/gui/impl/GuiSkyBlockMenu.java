package me.fan87.commonplugin.gui.impl;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LoreUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.greenrobot.eventbus.Subscribe;

public class GuiSkyBlockMenu extends Gui {

    private SBPlayer player;

    public GuiSkyBlockMenu(SBPlayer player) {
        super("SkyBlock Menu", 6);
        this.player = player;
    }

    @Override
    public void init() {
        this.fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        ItemStack menuSkull = GuiItemProvider.getMenuSkull(this.player);
        ItemMeta itemMeta = menuSkull.getItemMeta();
        itemMeta.getLore().add("");
        itemMeta.getLore().add("§7§eClick to view your profile!");
        this.set(5, 2, new GuiItem(menuSkull, event -> {
            event.setCancelled(true);
            player.openProfileMenu();
        }));
        ItemStack craft = new ItemStack(Material.WORKBENCH);
        ItemMeta craftItemMeta = craft.getItemMeta();
        craftItemMeta.setDisplayName(ChatColor.GREEN + "Crafting Table");
        craftItemMeta.setLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + "Opens the crafting gird.\n\n" + ChatColor.YELLOW + "Click to open!"));
        craft.setItemMeta(craftItemMeta);
        this.set(5, 4, new GuiItem(craft, event -> {
            event.setCancelled(true);
            new GuiCraftingTable(player.getSkyBlock(), player).open(((Player) event.getWhoClicked()));
        }));
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        ItemStack menuSkull = GuiItemProvider.getMenuSkull(this.player);
        ItemMeta itemMeta = menuSkull.getItemMeta();
        itemMeta.getLore().add("");
        itemMeta.getLore().add("§7§eClick to view your profile!");
        this.set(5, 2, new GuiItem(menuSkull, e -> {
            e.setCancelled(true);
            player.openProfileMenu();
        }));
    }
}
