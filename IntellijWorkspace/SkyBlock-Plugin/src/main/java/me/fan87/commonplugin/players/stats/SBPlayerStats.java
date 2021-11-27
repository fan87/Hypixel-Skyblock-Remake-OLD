package me.fan87.commonplugin.players.stats;

import lombok.Getter;
import me.fan87.commonplugin.players.stats.impl.*;

@Getter
public class SBPlayerStats {

    private final StatHealth health = new StatHealth();
    private final StatDefence defence = new StatDefence();
    private final StatSpeed speed = new StatSpeed();
    private final StatStrength strength = new StatStrength();
    private final StatCritChance critChance = new StatCritChance();
    private final StatCritDamage critDamage = new StatCritDamage();
    private final StatIntelligence intelligence = new StatIntelligence();
    private final StatMiningSpeed miningSpeed = new StatMiningSpeed();
    private final StatBonusAttackSpeed bonusAttackSpeed = new StatBonusAttackSpeed();
    private final StatSeaCreatureChance seaCreatureChance = new StatSeaCreatureChance();
    private final StatMagicFind magicFind = new StatMagicFind();
    private final StatPetLuck petLuck = new StatPetLuck();
    private final StatTrueDefence trueDefence = new StatTrueDefence();
    private final StatFerocity ferocity = new StatFerocity();
    private final StatAbilityDamage abilityDamage = new StatAbilityDamage();
    private final StatMiningFortune miningFortune = new StatMiningFortune();
    private final StatFarmingFortune farmingFortune = new StatFarmingFortune();
    private final StatForagingFortune foragingFortune = new StatForagingFortune();
    private final StatPristine pristine = new StatPristine();

}
