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
    private final int level;

    public RewardFortune(SBSkill skill, int level) {
        this.skill = skill;
        this.level = level;
    }

    public abstract SBPlayer getPlayer();

    public String getDescription(SBSkill skill) {
        return skill.getFortuneDisplayWithLevel(level, getPlayer());
    }

    @Override
    public String toString() {
        return ChatColor.YELLOW + skill.getSkillType().getFortuneName() + " " + skill.getLevelDisplay(level) + "\n" + ChatColor.WHITE + getDescription(skill);
    }

    @Override
    public void trigger(SBPlayer player) {

    }
}
