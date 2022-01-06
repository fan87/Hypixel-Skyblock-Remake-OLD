package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.skill.SBSkill;

import java.util.List;

public class SkillFarming extends SBSkill {
    public SkillFarming(SBPlayer player, double currentExp) {
        super(player, currentExp);
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.FARMING;
    }

    @Override
    public List<SBReward> getRewards(int level) {
        return super.getRewards(level);
    }

    @Override
    public String getNamespace() {
        return "FARMING";
    }
}
