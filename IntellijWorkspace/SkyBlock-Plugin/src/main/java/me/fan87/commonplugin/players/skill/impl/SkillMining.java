package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardBaseStat;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillMining extends SBSkill {
    @Override
    public SkillType getSkillType() {return SkillType.MINING;}

    @Override
    public List<SBReward> getRewards(int level, SBPlayer player) {
        List<SBReward> rewards = super.getRewards(level, player);
        rewards.add(new RewardBaseStat(player.getStats().getDefence(), getDefenseBonus(level)));
        return rewards;
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {

        return "Grants %s " + player.getStats().getMiningFortune().getDisplayName() + ChatColor.RESET + ", which increases your chance of multiple crops";

    }


    @Override
    public int getFortuneValue(int level) {
        return level*4;
    }

    @Override
    public String getNamespace() {
        return "MINING";
    }

    public int getTotalDefenseBonus(int level) {
        if (level <= 14) return level;
        return 14 + (level-14)*2;
    }

    public int getDefenseBonus(int level){
        if (level <= 14) return 1;
        return 2;
    }

}
