package me.fan87.commonplugin.players.collections;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardSkillExp;
import me.fan87.commonplugin.players.skill.SBSkill;
import me.fan87.commonplugin.utils.RomanUtils;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public abstract class SBCollection {

    @Getter
    private final SBCustomItem item;
    @Getter
    private final CollectionPattern collectionPattern;
    @Getter
    private final int maxLevel;
    @Getter
    private final CollectionType collectionType;

    @JsonProperty("collected")
    @Getter
    @Setter
    private int collected = 0;

    public SBCollection(SBCustomItem item, CollectionPattern pattern, int maxLevel, CollectionType collectionType) {
        this.item = item;
        this.collectionPattern = pattern;
        this.maxLevel = maxLevel;
        this.collectionType = collectionType;
    }

    protected abstract SBReward[] getLevelReward(int level);

    public int getRequiredAmount(int level) {
        return collectionPattern.getAmount()[Math.min(level, getMaxLevel()) - 1];
    }



    public boolean isMaxedOut() {
        System.out.println(getLevel(getCollected()) + ">=" + getMaxLevel());
        return getLevel(getCollected()) >= getMaxLevel();
    }

    public int getLevel(int collected) {
        int currentLevel = 0;
        for (int i : collectionPattern.getAmount()) {
            if (collected < i) return currentLevel;
            currentLevel++;
        }
        return maxLevel;
    }

    public int getLevel() {
        return getLevel(collected);
    }

    public String getLevelDisplay(int level) {
        return RomanUtils.toRoman(level);
    }

    public SBReward[] getRewards(int level) {
        SBReward[] levelReward = getLevelReward(level);
        if (levelReward.length <= 0) {
            return new SBReward[] {
                new RewardSkillExp(getCollectionType().getSkillType(), getRequiredAmount(level)/10)
            };
        }
        return levelReward;
    }

    public List<String> getRewardsLore(int level) {
        List<String> out = new ArrayList<>();
        out.add(ChatColor.GRAY + "Rewards:");
        for (SBReward reward : getRewards(level)) {
            out.addAll(reward.toLore());
        }
        return out;
    }

    @Getter
    public static class CollectionPattern {
        public static CollectionPattern NORMAL = new CollectionPattern(50, 100, 250, 1000, 2500, 5000, 10000, 25000, 40000, 70000);
        public static CollectionPattern CACTUS = new CollectionPattern(100, 250, 500, 1000, 2500, 5000, 10000, 25000, 50000);
        public static CollectionPattern CARROT = new CollectionPattern(100, 250, 500, 1800, 5000, 10000, 25000, 50000, 100000);
        public static CollectionPattern COCOA = new CollectionPattern(75, 200, 500, 2000, 5000, 10000, 20000, 50000, 100000);

        public CollectionPattern(int... amount) {
            this.amount = amount;
        }
        private final int[] amount;
    }

    @Getter
    @AllArgsConstructor
    public enum CollectionType {
        FARMING("Farming", SBSkill.SkillType.FARMING),
        MINING("Mining", SBSkill.SkillType.MINING),
        COMBAT("Combat", SBSkill.SkillType.COMBAT),
        FORAGING("Foraging", SBSkill.SkillType.FORAGING),
        FISHING("Fishing", SBSkill.SkillType.FISHING);

        private String name;
        private SBSkill.SkillType skillType;

    }

}
