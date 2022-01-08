package me.fan87.commonplugin.players.tradings.tradable.impl;

import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import net.md_5.bungee.api.ChatColor;

public class ItemTradable extends SBTradable {

    private final SBItemVector itemVector;

    public ItemTradable(SBItemVector itemVector) {
        this.itemVector = itemVector;
    }

    @Override
    public String toString() {
        return itemVector.toString();
    }

    @Override
    public boolean doesPlayerHave(SBPlayer player, float multiplier) {
        return itemVector.multiply(multiplier).doesPlayerHave(player);
    }

    @Override
    public boolean takeItemFrom(SBPlayer player, float multiplier) {
        return itemVector.multiply(multiplier).takeItemFrom(player);
    }

    @Override
    public String getNotEnoughMessage() {
        return ChatColor.RED + "You don't have the required items!";
    }

}
