package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillForaging extends SBSkill {
    @Override
    public SkillType getSkillType() {
        return SkillType.FORAGING;
    }

    @Override
    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getStrength(), getStrengthBonus(level)));
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Grants %s " + player.getStats().getForagingFortune().getDisplayName() + ChatColor.RESET + ", which increases your chance of multiple crops";
    }

    @Override
    public double getFortuneValue(int level) {
        return level*4;
    }

    @Override
    public String getNamespace() {
        return "FORAGING";
    }

    public int getTotalStrengthBonus(int level){
        if (level <= 14) return level;
        return 14 + (level-14)*2;
    }

    public int getStrengthBonus(int level) {
        if (level <= 14) return level;
        return 2;
    }
}
