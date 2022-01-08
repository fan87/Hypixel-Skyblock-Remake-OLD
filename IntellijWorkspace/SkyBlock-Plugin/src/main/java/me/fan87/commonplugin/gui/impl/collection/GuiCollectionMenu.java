package me.fan87.commonplugin.gui.impl.collection;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public class GuiCollectionMenu extends Gui {

    private final SBCollection collection;
    private final SBPlayer player;

    public GuiCollectionMenu(SBPlayer player, SBCollection collection) {
        super(collection.getItem().getDisplayName() + " Collection", 6);
        this.player = player;
        this.collection = collection;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        ItemStackBuilder builder = new ItemStackBuilder(collection.getItem().getMaterial(), collection.getItem().getDurability());
        builder.setDisplayName(ChatColor.YELLOW + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(collection.getLevel(collection.getCollected())));
        builder.addLore(ChatColor.GRAY + "View all your " + collection.getItem().getDisplayName() + " Collection progress and rewards!", true);
        builder.addLore("");
        builder.addLore(ChatColor.GRAY + "Total Collected: " + ChatColor.YELLOW + NumberUtils.formatNumber(collection.getCollected()));
        set(5, 1, new GuiItem(builder.build()));
        for (int level = 1; level < collection.getMaxLevel() + 1; level++) {
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(Material.STAINED_GLASS_PANE, 0);
            if (collection.getLevel() >= level) {
                itemStackBuilder.setDisplayName(ChatColor.GREEN + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(level));
                itemStackBuilder.setData(5);
            } else if (collection.getLevel() + 1 == level) {
                itemStackBuilder.setDisplayName(ChatColor.YELLOW + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(level));
                itemStackBuilder.setData(4);
            } else {
                itemStackBuilder.setDisplayName(ChatColor.RED + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(level));
                itemStackBuilder.setData(14);
            }
            itemStackBuilder.addLore("");
            itemStackBuilder.addLore(NumberUtils.getPercentageText("Progress", collection.getCollected(), collection.getRequiredAmount(level), ChatColor.GOLD + "%"));
            itemStackBuilder.addLore(NumberUtils.generateProgressBar(collection.getCollected(), collection.getRequiredAmount(level)));
            itemStackBuilder.addLore("");
            itemStackBuilder.addLore(collection.getRewardsLore(level));
            itemStackBuilder.addLore("");
            itemStackBuilder.addLore(ChatColor.YELLOW + "Click to view rewards!");
            itemStackBuilder.setAmount(level);
            int[] levelPosition = getLevelPosition(level, collection.getMaxLevel());
            set(levelPosition[0], levelPosition[1], new GuiItem(itemStackBuilder.build()));
        }
        renderGoBackItems(new GuiCollectionItemsMenu(player, collection.getCollectionType()), player.getPlayer());
    }

    private static int[] getLevelPosition(int level, int maxLevel) {
        int offset = maxLevel<9?(9 - maxLevel)/2:0;
        int y = level/(10-offset*2);
        int x = level % (10-offset*2) + offset;
        return new int[] {x, y + 3};
    }
}
