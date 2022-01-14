package me.fan87.commonplugin.players.tradings.tradable.impl;

import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;

public class CoinTradable extends SBTradable {

    @Getter
    private double amount;

    public CoinTradable(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return ChatColor.GOLD.toString() + NumberUtils.formatNumber(amount) + " Coin" + (amount > 1?"s":"");
    }

    @Override
    public boolean doesPlayerHave(SBPlayer player, float multiplier) {
        return player.getPurseCoins() >= amount*multiplier;
    }

    @Override
    public boolean takeItemFrom(SBPlayer player, float multiplier) {
        if (!doesPlayerHave(player, multiplier)) return false;
        player.setPurseCoins(player.getPurseCoins() - amount*multiplier);
        return true;
    }

    @Override
    public String getNotEnoughMessage() {
        return ChatColor.RED + "You don't have enough Coins!";
    }
}
