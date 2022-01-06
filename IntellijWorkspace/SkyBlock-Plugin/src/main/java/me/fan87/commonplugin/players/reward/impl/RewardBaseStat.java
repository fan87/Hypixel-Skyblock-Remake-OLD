package me.fan87.commonplugin.players.reward.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.ChatColor;

/**
 * Yes, I know, but unluckily this part is super hard coded. If you have a better method, please let me know (Either open a github issue, pull request or DM me on discord)
 * Trigger method does nothing
 */
@Getter
@AllArgsConstructor
public class RewardBaseStat extends SBReward {

    private final SBStat stat;
    private final double baseExtraValues;

    @Override
    public String toString() {
        return ChatColor.DARK_GRAY + "+" + ChatColor.GREEN + " " + stat.getDisplayName();
    }

    @Override
    public void trigger(SBPlayer player) {

    }
}
