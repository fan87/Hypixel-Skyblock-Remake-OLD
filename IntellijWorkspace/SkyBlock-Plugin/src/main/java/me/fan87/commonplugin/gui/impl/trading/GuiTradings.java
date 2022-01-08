package me.fan87.commonplugin.gui.impl.trading;

import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.types.GuiList;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.SBTradings;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.util.ArrayList;

public class GuiTradings extends GuiList {

    private final SBPlayer player;

    public GuiTradings(SBPlayer player, int currentPage) {
        super("Trades", currentPage, new ArrayList<>());
        this.player = player;
    }

    @Override
    public void init() {
        for (SBTrading value : SBTradings.getRegisteredTradings().values()) {
            ItemStackBuilder builder = new ItemStackBuilder(Material.INK_SACK, 8)
                    .setDisplayName(ChatColor.RED + "???")
                    .addLore();
            if (player.isTradingUnlocked(value)) {
                builder.setItemStack(value.getTo().getDisplayItem());
                builder.addLore("");
                builder.addLore(ChatColor.GRAY + "Cost");
                for (SBTradable sbTradable : value.getFrom()) {
                    builder.addLore(sbTradable.toString());
                }
                builder.addLore("");
                builder.addLore(ChatColor.YELLOW + "Click to trade!");
                builder.addLore(ChatColor.YELLOW + "Right-Click for more trading options!");
            }
            getContents().add(new GuiItem(builder.build(), event -> {
                if (player.isTradingUnlocked(value)) {
                    value.confirmTrade(player, 1f);
                } else {
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    player.getPlayer().sendMessage(ChatColor.RED + "This item is locked!");
                }
            }));
        }
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        putItems();
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
    }

    @Override
    public void goPage(int page) {

    }
}
