package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillFishing extends SBSkill {
    @Override
    public SkillType getSkillType() {
        return SkillType.FISHING;
    }

    @Override
    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getHealth(), getHealthBonus(level)));
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Increases the chance to find treasure when fishing by " + ChatColor.GREEN + "%s%" + ChatColor.WHITE + ".";
    }

    @Override
    public double getFortuneValue(int level) {
        return level*0.2d;
    }


    @Override
    public String getNamespace() {
        return "FISHING";
    }

    public double getSeaCreatureChance(int level) {
        return level*0.2;
    }

    public int getHealthBonus(int level) {
        if (level <= 14) return level*2;
        if (level <= 19) return 14*2 + 3*(level - 14);
        if (level <= 25) return 14*2 + 5*3 + (level-19)*4;
        return 14*2 + 5*3 + 6*4 + (level - 25)*5;
    }

}
