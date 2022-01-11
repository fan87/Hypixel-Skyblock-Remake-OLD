package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillAlchemy extends SBSkill {
    @Override
    public SkillType getSkillType() {
        return SkillType.ALCHEMY;
    }

    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getIntelligence(), getIntelligenceBonus(level)));
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Potions that you brew have a " + ChatColor.GREEN + "%s" + ChatColor.WHITE + " longer duration.";
    }

    @Override
    public double getFortuneValue(int level) {
        return level;
    }

    @Override
    public String getNamespace() {
        return "ALCHEMY";
    }

    public int getTotalIntelligenceBonus(int level) {
        if (level <= 14) return level;
        return 14 + (level-14)*2;
    }

    public int getIntelligenceBonus(int level) {
        if (level <= 14) return 1;
        return 2;
    }
}
