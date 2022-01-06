package me.fan87.commonplugin.players.reward.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.skill.SBSkill;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;

public class RewardSkillExp extends SBReward {

    private final SBSkill.SkillType skillType;
    private final int amount;

    public RewardSkillExp(SBSkill.SkillType skillType, int amount) {
        this.skillType = skillType;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return ChatColor.DARK_GRAY + "+" + ChatColor.DARK_AQUA + NumberUtils.formatNumber(amount) + ChatColor.GRAY + " " + skillType.getName() + " Experience";
    }

    @Override
    public void trigger(SBPlayer player) {
        for (SBSkill skill : player.getSkills().getSkills()) {
            if (skill.getSkillType() == skillType) {
                skill.addExp(skill.getExp(), player);
            }
        }
    }
}
