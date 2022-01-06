package me.fan87.commonplugin.players.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardCoins;
import me.fan87.commonplugin.players.reward.impl.RewardFortune;
import me.fan87.commonplugin.utils.ExpUtils;
import me.fan87.commonplugin.utils.NumberUtils;
import me.fan87.commonplugin.utils.RomanUtils;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class SBSkill {

    @Getter
    @JsonProperty("exp")
    private double exp = 0;



    public void setExp(double exp, SBPlayer player) {
        int old = getLevel();
        this.exp = exp;
        while (getLevel() > old) {
            old++;
            levelUp(old, player);
        }
    }

    private void levelUp(int newLevel, SBPlayer player) {
        for (SBReward reward : getRewards(newLevel, player)) {
            reward.trigger(player);
        }
    }

    public double getExtraExp() {
        return getExp() - ExpUtils.getTotalSkillExp(getLevel());
    }

    public double getRequiredExp(int targetLevel) {
        return ExpUtils.getExtraSkillExp(targetLevel);
    }

    public double getTotalRequiredExp(int targetLevel) {
        return ExpUtils.getTotalSkillExp(targetLevel);
    }

    public int getLevel() {
        return ExpUtils.getCurrentSkillLevel(exp);
    }

    public String getLevelDisplay(int level) {
        return RomanUtils.toRoman(level);
    }

    public List<String> getRewardLore(String title, int level, SBPlayer player) {
        List<String> out = new ArrayList<>();
        if (level == 0) return out;
        out.add(title);
        for (SBReward reward : getRewards(level, player)) {
            List<String> c = reward.toLore();
            List<String> n = new ArrayList<>();
            for (String s : c) {
                n.add(" " + s);
            }
            out.addAll(n);
        }
        return out;
    }

    public abstract SkillType getSkillType();
    public List<SBReward> getRewards(int level, SBPlayer player) {
        if (level == 0) return new ArrayList<>();
        List<SBReward> output = new ArrayList<>();
        output.add(new RewardFortune(this) {
            @Override
            public String getDescription(SBSkill skill) {
                int fortuneValue = getFortuneValue(level - 1);
                int newFortuneValue = getFortuneValue(level);
                return getFortuneDescription(player).replace("%s", NumberUtils.valueChangeDisplay(fortuneValue, newFortuneValue));
            }
        });
        output.add(new RewardCoins(ExpUtils.getSkillLevelUpCoins(level)));
        return output;
    }

    public abstract String getFortuneDescription(SBPlayer player);
    public abstract int getFortuneValue(int level);
    public abstract String getNamespace();

    @Getter
    @AllArgsConstructor
    public enum SkillType {
        FARMING("Farming", "Farmhand", Material.GOLD_HOE, (short) 0, "Harvest crops and shear sheep to earn Farming XP!", "Increase your Farming Level to unlock Perks, statistic bonuses, and more!"),
        MINING("Mining", "Spelunker", Material.IRON_PICKAXE, (short) 0, "Spelunk islands for ores and valuable materials to earn Mining XP!", "Increase your Mining Level to unlock Perks, statistic bonuses, and more!"),
        COMBAT("Combat", "Warrior", Material.IRON_SWORD, (short) 0, "Fight mobs and players to earn Combat XP!", "Increase your Combat Level to unlock Perks, statistic bonuses, and more!"),
        FORAGING("Foraging", "Logger", Material.SAPLING, (short) 3, "Cut trees and forage for other plants to earn Foraging XP!", "Increase your Foraging Level to unlock Perks, statistic bonuses, and more!"),
        FISHING("Fishing", "Treasure Hunter", Material.FISHING_ROD, (short) 0, "Visit your local pond to fish and earn Fishing XP!", "Increase your Fishing Level to unlock Perks, statistic bonuses, and more!"),
        ENCHANTING("Enchanting", "Conjurer", Material.ENCHANTMENT_TABLE, (short) 0, "Enchant items to earn Enchanting XP!", "Increase your Enchanting Level to unlock Perks, statistic bonuses, and more!"),
        ALCHEMY("Alchemy", "Brewer", Material.BREWING_STAND, (short) 0, "Brew potions to earn Alchemy XP!", "Increase your Alchemy Level to unlock Perks, statistic bonuses, and more!")
        ;

        private String name;
        private String fortuneName;
        private Material iconMaterial;
        private short iconData;
        private String obtainMethod;
        private String perks;
    }

}
