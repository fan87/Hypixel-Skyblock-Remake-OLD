package me.fan87.commonplugin.gui.impl;

import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.LoreUtils;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GuiCollectionItemsMenu extends Gui {

    private SBCollection.CollectionType collectionType;
    private SBPlayer player;

    public GuiCollectionItemsMenu(SBPlayer player, SBCollection.CollectionType collectionType) {
        super(collectionType.getName() + " Collection", 6);
        this.collectionType = collectionType;
        this.player = player;
    }

    @Override
    public void init() {
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        int unlockedCollections = player.getCollections().getUnlockedCollections(sbCollection -> sbCollection.getCollectionType() == collectionType);
        boolean showMaxedOut = unlockedCollections == player.getCollections().getCollections().length;
        ItemStackBuilder itemStackBuilder = new ItemStackBuilder(collectionType.getSkillType().getIconMaterial(), collectionType.getSkillType().getIconData())
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + collectionType.getName() + " Collection")
                .addLore(LoreUtils.splitLoreForLine(ChatColor.GRAY + "View your " + collectionType.getName() + " Collection!"))
                .addLore("");
        ItemStack build = itemStackBuilder
                .addLore(NumberUtils.getPercentageText("Collection " +
                                (showMaxedOut ? "Maxed Out" : "Unlocked"),
                        (showMaxedOut ? player.getCollections().getMaxedOutCollections(sbCollection -> sbCollection.getCollectionType() == collectionType) : unlockedCollections),
                        player.getCollections().getCollections().length, ChatColor.GOLD + "%"))
                .addLore(NumberUtils.generateProgressBar(
                        (showMaxedOut ? player.getCollections().getMaxedOutCollections(sbCollection -> sbCollection.getCollectionType() == collectionType) : unlockedCollections),
                        player.getCollections().getCollections().length))
                .build();

        set(5, 1, new GuiItem(build));

        int index = -1;
        for (SBCollection collection : player.getCollections().getCollectionsByType(collectionType)) {
            index++;
            int x = index % 7;
            int y = index / 7;
            ItemStackBuilder builder = new ItemStackBuilder(collection.getItem().getMaterial(), collection.getItem().getDamage());
            builder.addAllItemFlags();
            if (collection.getCollected() > 0) {
                builder.setDisplayName(ChatColor.YELLOW + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(collection.getLevel(collection.getCollected())));
                builder.addLore(ChatColor.GRAY + "View all your " + collection.getItem().getDisplayName() + " Collection progress and rewards!", true);
                if (!collection.isMaxedOut()) {
                    builder.addLore("");
                    builder.addLore(NumberUtils.getPercentageText("Progress to " + collection.getItem().getDisplayName() + " " + collection.getLevelDisplay(collection.getLevel(collection.getCollected())),
                            collection.getCollected(), collection.getRequiredAmount(collection.getLevel(collection.getCollected()) + 1), ChatColor.GOLD + "%"));
                    builder.addLore(NumberUtils.generateProgressBar(collection.getCollected(), collection.getRequiredAmount(collection.getLevel(collection.getCollected()) + 1)));
                    builder.addLore("");
                    builder.addLore(collection.getRewardsLore(collection.getLevel() + 1));

                }
                builder.addLore("");
                builder.addLore(ChatColor.YELLOW + "Click to view!");
                set(x + 2, y + 2, new GuiItem(builder.build(), event -> {
                    new GuiCollectionMenu(player, collection).open(player.getPlayer());
                }));
            } else {
                builder.setMaterial(Material.CLAY_BALL);
                builder.setData(0);
                builder.setDisplayName(ChatColor.RED + collection.getItem().getDisplayName());
                builder.addLore(ChatColor.GRAY + "Find this item to add it into your collection and unlock collection rewards!", true);
                set(x + 2, y + 2, new GuiItem(builder.build()));
            }
        }

        renderGoBackItems(new GuiCollectionTypesMenu(player), player.getPlayer());
    }
}
