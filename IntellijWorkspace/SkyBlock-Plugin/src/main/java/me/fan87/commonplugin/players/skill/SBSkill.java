package me.fan87.commonplugin.players.skill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.utils.ExpUtils;

import java.util.List;

public abstract class SBSkill {

    @Getter
    private long exp = 0;
    @Getter
    private final SBPlayer player;

    public SBSkill(SBPlayer player, int currentExp) {
        this.exp = currentExp;
        this.player = player;
    }

    public void setExp(long exp) {
        int old = getLevel();
        this.exp = exp;
        while (getLevel() > old) {
            old++;
            levelUp(old);
        }
    }

    private void levelUp(int newLevel) {
        for (SBReward reward : getRewards(newLevel)) {
            reward.trigger(getPlayer());
        }
    }

    public long getExtraExp() {
        return getExp() - ExpUtils.getTotalSkillExp(getLevel());
    }

    public int getLevel() {
        return ExpUtils.getCurrentSkillLevel(exp);
    }



    public abstract SkillType getSkillType();
    public abstract List<SBReward> getRewards(int level);
    public abstract String getNamespace();

    @Getter
    @AllArgsConstructor
    public enum SkillType {
        FARMING("Farming", "Harvest crops and shear sheep to earn Farming XP!", "Increase your Farming Level to unlock Perks, statistic bonuses, and more!"),
        MINING("Mining", "Spelunk islands for ores and valuable materials to earn Mining XP!", "Increase your Mining Level to unlock Perks, statistic bonuses, and more!"),
        COMBAT("Combat", "Fight mobs and players to earn Combat XP!", "Increase your Combat Level to unlock Perks, statistic bonuses, and more!"),
        FORAGING("Foraging", "Cut trees and forage for other plants to earn Foraging XP!", "Increase your Foraging Level to unlock Perks, statistic bonuses, and more!"),
        FISHING("Fishing", "Visit your local pond to fish and earn Fishing XP!", "Increase your Fishing Level to unlock Perks, statistic bonuses, and more!"),
        ENCHANTING("Enchanting", "Enchant items to earn Enchanting XP!", "Increase your Enchanting Level to unlock Perks, statistic bonuses, and more!"),
        ALCHEMY("Alchemy", "Brew potions to earn Alchemy XP!", "Increase your Alchemy Level to unlock Perks, statistic bonuses, and more!")
        ;

        private String name;
        private String obtainMethod;
        private String perks;
    }

}
