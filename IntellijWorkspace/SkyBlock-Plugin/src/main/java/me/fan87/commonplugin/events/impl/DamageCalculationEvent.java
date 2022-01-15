package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DamageCalculationEvent {

    private double armorMultiplier;
    private double weaponBonus;
    private double enchantmentBonus;

}
