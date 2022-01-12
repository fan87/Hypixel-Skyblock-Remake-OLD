package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillFarming extends SBSkill {

    @Override
    public SkillType getSkillType() {
        return SkillType.FARMING;
    }

    @Override
    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getHealth(), getHealthBonus(level)));
        RewardBaseStat fortune = new RewardBaseStat(player.getStats().getFarmingFortune(), getFortuneValue(level) - getFortuneValue(level-1));
        rewards.add(fortune);
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Grants %s " + player.getStats().getFarmingFortune().getDisplayName() + ChatColor.RESET + ", which increases your chance of multiple crops";
    }

    @Override
    public double getFortuneValue(int level) {
        return level*4;
    }

    @Override
    public String getNamespace() {
        return "FARMING";
    }

    public int getTotalHealthBonus(int level) {
        if (level <= 14) return level*2;
        if (level <= 19) return 14*2 + 3*(level - 14);
        if (level <= 25) return 14*2 + 5*3 + (level-19)*4;
        return 14*2 + 5*3 + 6*4 + (level - 25)*5;
    }

    public int getHealthBonus(int level) {
        if (level <= 14) return 2;
        if (level <= 19) return 3;
        if (level <= 25) return 4;
        return 5;
    }
}
