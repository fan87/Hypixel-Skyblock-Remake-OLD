package me.fan87.commonplugin.players.reward.impl;

import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import org.bukkit.ChatColor;

public class RewardCoins extends SBReward {

    @Getter
    private long amount;

    public RewardCoins(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return ChatColor.DARK_GRAY + "+" + ChatColor.GOLD + amount + ChatColor.GRAY + " Coins";
    }

    @Override
    public void trigger(SBPlayer player) {
        player.addCoins(amount);
    }
}
