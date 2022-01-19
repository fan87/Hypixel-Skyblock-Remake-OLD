package me.fan87.commonplugin.players.stats;

import lombok.*;
import me.fan87.commonplugin.players.stats.impl.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SBStatVector {

    private StatHealth health = new StatHealth();
    private StatDefence defence = new StatDefence();
    private StatSpeed speed = new StatSpeed();
    private StatStrength strength = new StatStrength();
    private StatCritChance critChance = new StatCritChance();
    private StatCritDamage critDamage = new StatCritDamage();
    private StatIntelligence intelligence = new StatIntelligence();
    private StatMiningSpeed miningSpeed = new StatMiningSpeed();
    private StatBonusAttackSpeed bonusAttackSpeed = new StatBonusAttackSpeed();
    private StatSeaCreatureChance seaCreatureChance = new StatSeaCreatureChance();
    private StatMagicFind magicFind = new StatMagicFind();
    private StatPetLuck petLuck = new StatPetLuck();
    private StatTrueDefence trueDefence = new StatTrueDefence();
    private StatFerocity ferocity = new StatFerocity();
    private StatAbilityDamage abilityDamage = new StatAbilityDamage();
    private StatMiningFortune miningFortune = new StatMiningFortune();
    private StatFarmingFortune farmingFortune = new StatFarmingFortune();
    private StatForagingFortune foragingFortune = new StatForagingFortune();
    private StatPristine pristine = new StatPristine();

    @SneakyThrows
    public SBStat[] getStats() {
        List<SBStat> stats = new ArrayList<>();
        for (Field declaredField : getClass().getDeclaredFields()) {
            if (declaredField.getType().getSuperclass() == SBStat.class) {
                stats.add((SBStat) declaredField.get(this));
            }
        }
        return stats.toArray(new SBStat[0]);
    }

}
