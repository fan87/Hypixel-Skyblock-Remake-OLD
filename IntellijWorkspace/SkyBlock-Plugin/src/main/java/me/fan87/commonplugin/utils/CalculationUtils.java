package me.fan87.commonplugin.utils;

public class CalculationUtils {

    /**
     * Calculate the Init Damage. For more information, please check Hypixel SkyBlock Wiki <a href="https://hypixel-skyblock.fandom.com/wiki/Damage_Calculation"></a> <br>
     * You'll need this to calculate final damage
     * @param weaponDamage The damage value of the weapon you are holding
     * @param unNormalizedStrength Raw strength value
     * @return Calculated init damage
     */
    public static double getInitDamage(double weaponDamage, double unNormalizedStrength) {
        return (5 + weaponDamage) * (1 + unNormalizedStrength/100d);
    }

    /**
     * Basically same as InitDamage, it's required
     * @see CalculationUtils#getInitDamage(double, double)
     * @param enchantmentsMultiplier Multiplier of the enchants
     * @param combatLevel Combat level player is having
     * @param weaponBonus Multiplier of the weapon
     * @return Damage Multiplier Variable
     */
    public static double getDamageMultiplier(double enchantmentsMultiplier, int combatLevel, double weaponBonus) {
        return 1 + (combatLevel * 0.04) + enchantmentsMultiplier + weaponBonus;
    }

    /**
     * Calculate the final damage took to the target entity
     * @param initDamage Result of {@link CalculationUtils#getInitDamage(double, double)}
     * @param damageMultiplier Result of {@link CalculationUtils#getDamageMultiplier(double, int, double)}
     * @param armorBonus Multiplier value on player's armors
     * @param criticalDamage Critical Damage stat raw value (UnNormalized)
     * @return The final damage
     */
    public static double getFinalDamage(double initDamage, double damageMultiplier, double armorBonus, double criticalDamage) {
        double v = initDamage * damageMultiplier * armorBonus * (1 + criticalDamage / 100d);
        return v;
    }

}
