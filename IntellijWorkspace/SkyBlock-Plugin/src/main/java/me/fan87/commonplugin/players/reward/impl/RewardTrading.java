package me.fan87.commonplugin.players.reward.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.tradings.SBTrading;
import org.bukkit.ChatColor;

public class RewardTrading extends SBReward {

    private final SBTrading trading;

    public RewardTrading(SBTrading trading) {
        this.trading = trading;
    }

    @Override
    public String toString() {
        return trading.getTo().getItem().getRarity().getColor() + trading.getTo().getItem().getDisplayName() + ChatColor.GRAY + " Trade";
    }

    @Override
    public void trigger(SBPlayer player) {
        player.unlockTrading(trading);
    }
}
