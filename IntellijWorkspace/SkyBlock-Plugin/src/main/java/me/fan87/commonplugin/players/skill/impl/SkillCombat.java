package me.fan87.commonplugin.players.skill.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

import java.util.List;

public class SkillCombat extends SBSkill {


    @Override
    public SkillType getSkillType() {
        return SkillType.COMBAT;
    }

    @Override
    public List<SBReward> getRewards(int level, SBPlayer player) {
        return super.getRewards(level, player);
    }

    @Override
    public String getFortuneDescription(SBPlayer player) {
        return "Deal %s" + ChatColor.RESET + " more damage to mobs.";
    }

    @Override
    public double getFortuneValue(int level) {
        if (level <= 50) {
            return level*4;
        }
        return 50*4 + (level-50);
    }

    @Override
    public String getNamespace() {
        return "COMBAT";
    }

}
