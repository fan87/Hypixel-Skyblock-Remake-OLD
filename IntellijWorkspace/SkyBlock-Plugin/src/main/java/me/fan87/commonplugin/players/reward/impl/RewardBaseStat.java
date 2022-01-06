package me.fan87.commonplugin.players.reward.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;

@Getter
@AllArgsConstructor
public class RewardBaseStat extends SBReward {

    private final SBStat stat;
    private final double baseExtraValues;

    @Override
    public String toString() {
        return ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + Math.floor(baseExtraValues) + " " + stat.getDisplayName();
    }

    @Override
    public void trigger(SBPlayer player) {
        for (SBStat sbStat : player.getStats().getStats()) {
            if (sbStat.getClass() == stat.getClass()) {
                sbStat.setBaseValue(sbStat.getBaseValue() + baseExtraValues);
            }
        }
    }
}
