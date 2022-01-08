package me.fan87.commonplugin.gui.impl;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.collection.GuiCollectionTypesMenu;
import me.fan87.commonplugin.gui.impl.recipe.GuiRecipeTypesMenu;
import me.fan87.commonplugin.gui.impl.skill.GuiSkillTypesMenu;
import me.fan87.commonplugin.gui.impl.trading.GuiTradings;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.SBTradings;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.greenrobot.eventbus.Subscribe;

import java.util.Collection;
import java.util.List;

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
        set(2, 3, new GuiItem(
                new ItemStackBuilder(Material.DIAMOND_SWORD)
                        .setDisplayName(ChatColor.GREEN + "Your Skills")
                        .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + "View your Skill progression and rewards."))
                        .addLore("")
                        .addLore(ChatColor.YELLOW + "Click to view!")
                        .build(), event -> {
                    new GuiSkillTypesMenu(player).open(((Player) event.getWhoClicked()));
        }));
        int unlockedCollections = player.getCollections().getUnlockedCollections();
        boolean showMaxedOut = unlockedCollections == player.getCollections().getCollections().length;
        set(3, 3, new GuiItem(
                new ItemStackBuilder(Material.PAINTING)
                        .setDisplayName(ChatColor.GREEN + "Collection")
                        .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY +
                                "View all of the items available in SkyBlock. Collect more of an item to unlock rewards on your way to becoming a master of SkyBlock!"))
                        .addLore("")
                        .addLore(NumberUtils.getPercentageText("Collection " +
                                (showMaxedOut?"Maxed Out":"Unlocked"),
                                (showMaxedOut?player.getCollections().getMaxedOutCollections(): unlockedCollections),
                                player.getCollections().getCollections().length, ChatColor.GOLD + "%"))
                        .addLore(NumberUtils.generateProgressBar(
                                (showMaxedOut?player.getCollections().getMaxedOutCollections(): unlockedCollections),
                                player.getCollections().getCollections().length))
                        .addLore("")
                        .addLore(ChatColor.YELLOW + "Click to view!")
                        .build(), event -> {
            new GuiCollectionTypesMenu(player).open(((Player) event.getWhoClicked()));
        }));
        set(5, 4, new GuiItem(craft, event -> {
            event.setCancelled(true);
            new GuiCraftingTable(player.getSkyBlock(), player).open(((Player) event.getWhoClicked()));
        }));
        {
            List<SBCustomItem> allUnlockableCraftingRecipes = player.getSkyBlock().getRecipesManager().getAllUnlockableCraftingRecipes();
            int unlocked = (int) allUnlockableCraftingRecipes.stream().filter(player::isRecipeUnlocked).count();
            int all = allUnlockableCraftingRecipes.size();
            set(4, 3, new GuiItem(new ItemStackBuilder(Material.BOOK)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Recipe Book")
                    .addLore(ChatColor.GRAY + "Through your adventure, you will unlock recipes for all kinds of special items! You can view how to craft items here.", true)
                    .addLore("")
                    .addLore(NumberUtils.getPercentageText(ChatColor.GRAY + "Recipe Book Unlocked", unlocked, all))
                    .addLore(NumberUtils.generateProgressBar(unlocked, all))
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to view!")
                    .build(), event -> {
                new GuiRecipeTypesMenu(player).open(player.getPlayer());
            }));
        }
        {
            Collection<SBTrading> allTrades = SBTradings.getRegisteredTradings().values();
            int unlocked = (int) allTrades.stream().filter(player::isTradingUnlocked).count();
            int all = allTrades.size();
            set(5, 3, new GuiItem(new ItemStackBuilder(Material.EMERALD)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Trades")
                    .addLore(ChatColor.GRAY + "View your available trades.\nThese trades are always available and accessible through the SkyBlock Menu.", true)
                    .addLore("")
                    .addLore(NumberUtils.getPercentageText(ChatColor.GRAY + "Trades Unlocked", unlocked, all))
                    .addLore(NumberUtils.generateProgressBar(unlocked, all))
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to view!")
                    .build(), event -> {
                new GuiTradings(player, 1).open(player.getPlayer());
            }));
        }
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
