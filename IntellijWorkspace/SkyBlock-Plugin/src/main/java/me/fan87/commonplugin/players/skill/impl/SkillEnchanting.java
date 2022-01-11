package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillEnchanting extends SBSkill {
    @Override
    public SkillType getSkillType() {
        return SkillType.ENCHANTING;
    }

    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getIntelligence(), getIntelligenceBonus(level)));
        rewards.add(new RewardBaseStat(player.getStats().getAbilityDamage(), getAbilityDamageBonus(level)));
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Gain %s" + ChatColor.GREEN + ChatColor.WHITE + " more experience orbs from any source.";
    }

    @Override
    public double getFortuneValue(int level) {
        return level*4;
    }

    @Override
    public String getNamespace() {
        return "ENCHANTING";
    }

    public int getTotalIntelligenceBonus(int level) {
        if (level <= 14) return level;
        return 14 + (level-14)*2;
    }

    public int getIntelligenceBonus(int level) {
        if (level <= 14) return 1;
        return 2;
    }

    public double getTotalAbilityDamageBonus(int level) {
        return level*0.5;
    }

    public double getAbilityDamageBonus(int level) {
        return 0.5;
    }
}
