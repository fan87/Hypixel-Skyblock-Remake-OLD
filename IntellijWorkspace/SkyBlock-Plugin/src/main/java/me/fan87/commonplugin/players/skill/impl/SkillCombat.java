package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.skill.SBSkill;

import java.util.List;

public class SkillCombat extends SBSkill {

    public SkillCombat(SBPlayer player, int currentExp) {
        super(player, currentExp);
    }

    @Override
    public SkillType getSkillType() {
        return SkillType.COMBAT;
    }

    @Override
    public List<SBReward> getRewards(int level) {
        return super.getRewards(level);
    }

    @Override
    public String getNamespace() {
        return "COMBAT";
    }

}
