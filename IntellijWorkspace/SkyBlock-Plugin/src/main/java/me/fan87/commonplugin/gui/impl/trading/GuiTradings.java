package me.fan87.commonplugin.gui.impl.trading;

import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.types.GuiList;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.SBTradings;

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
            getContents().add(value.getDisplayItem(player));
        }
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        putItems();
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
    }

    @Override
    public void goPage(int page) {
        new GuiTradings(player, page).open(player.getPlayer());
    }
}
