package me.fan87.commonplugin.players.reward.impl;

import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.skill.SBSkill;
import org.bukkit.ChatColor;

/**
 * This part of code is for displaying only, I need a better way to make it functional, and stop hard coding like a stupid asshole
 */
public abstract class RewardFortune extends SBReward {

    private final SBSkill skill;

    public RewardFortune(SBSkill skill) {
        this.skill = skill;
    }

    public abstract String getDescription(SBSkill skill);

    @Override
    public String toString() {
        return ChatColor.GREEN + skill.getSkillType().getFortuneName() + "\n" + ChatColor.WHITE + getDescription(skill);
    }

    @Override
    public void trigger(SBPlayer player) {

    }
}
