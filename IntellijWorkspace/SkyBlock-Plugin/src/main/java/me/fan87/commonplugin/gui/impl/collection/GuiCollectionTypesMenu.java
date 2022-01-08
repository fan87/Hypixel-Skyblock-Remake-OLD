package me.fan87.commonplugin.gui.impl.collection;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class GuiCollectionTypesMenu extends Gui {

    private final SBPlayer player;

    public GuiCollectionTypesMenu(SBPlayer player) {
        super("Collection", 6);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
        {
            int unlockedCollections = player.getCollections().getUnlockedCollections();
            boolean showMaxedOut = unlockedCollections == player.getCollections().getCollections().length;
            set(5, 1, new GuiItem(
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
                            .build()));
        } // Avoid variable name

        int x = 3;
        for (SBCollection.CollectionType value : SBCollection.CollectionType.values()) {
            int unlockedCollections = player.getCollections().getUnlockedCollections(sbCollection -> sbCollection.getCollectionType() == value);
            boolean showMaxedOut = unlockedCollections == player.getCollections().getCollections().length;
            ItemStackBuilder itemStackBuilder = new ItemStackBuilder(value.getSkillType().getIconMaterial())
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + value.getName() + " Collection")
                    .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + "View your " + value.getName() + " Collection!"))
                    .addLore("");
            ItemStack build = itemStackBuilder
                    .addLore(NumberUtils.getPercentageText("Collection " +
                                    (showMaxedOut ? "Maxed Out" : "Unlocked"),
                            (showMaxedOut ? player.getCollections().getMaxedOutCollections(sbCollection -> sbCollection.getCollectionType() == value) : unlockedCollections),
                            (int) Arrays.stream(player.getCollections().getCollections()).filter(c -> c.getCollectionType() == value).count(), ChatColor.GOLD + "%"))
                    .addLore(NumberUtils.generateProgressBar(
                            (showMaxedOut ? player.getCollections().getMaxedOutCollections(sbCollection -> sbCollection.getCollectionType() == value) : unlockedCollections),
                            (int) Arrays.stream(player.getCollections().getCollections()).filter(c -> c.getCollectionType() == value).count()))
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to view!")
                    .build();
            set(x++, 3, new GuiItem(build, event -> {
                new GuiCollectionItemsMenu(player, value).open(player.getPlayer());
            }));
        }
    }

}
