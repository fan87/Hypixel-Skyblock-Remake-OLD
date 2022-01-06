package me.fan87.commonplugin.players.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.BaseStatCalculationEvent;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class SBStat {

    public SBStat() {
        baseValue = getDefaultValue();
    }

    /**
     * The base value of the stat, for example: Speed is 100
     */
    @Setter
    private double baseValue;

    /**
     * All bonus values, including expire time etc.
     * @apiNote Bonus value won't be removed even if it's expired, unless you observe it
     */
    @Getter
    private final List<StatBonus> bonusValue = new ArrayList<>();

    public double getBaseValue(SBPlayer player) {
        BaseStatCalculationEvent event = new BaseStatCalculationEvent(this, player, baseValue);
        return event.getBaseValue();
    }


    /**
     * Calculate total bonus value, Basically add every stat bonus together and see if it's expired
     * @see SBStat#baseValue
     * @return Sum of bonus values
     */
    public double getTotalBonusValue(SBPlayer player) {
        double value = 0;
        for (StatBonus statBonus : new ArrayList<>(bonusValue)) {
            if (System.currentTimeMillis() > statBonus.expirationTime) {
                bonusValue.remove(statBonus);
                continue;
            }
            value += statBonus.getValue();
        }
        if (getMaxValue() > 0 && getBaseValue(player) + value > getMaxValue()) {
            value = Math.max(0, getMaxValue() - getBaseValue(player));
        }
        return value;
    }

    /**
     * Get total value
     * @see SBStat#getBaseValue(SBPlayer)
     * @see SBStat#getTotalBonusValue(SBPlayer)
     * @return getBaseValue() + getTotalBonusValue()
     */
    public double getValue(SBPlayer player) {
        return getBaseValue(player) + getTotalBonusValue(player);
    }

    /**
     * Basically equals value.toString(), but stat will do it for you and do other stuff like {@link Math#round(double)}
     * @param value Value to convert to string
     * @return The toStringed value
     */
    public abstract String getValueDisplay(double value);

    /**
     * Get the default value of base value, for example: speed is 100
     * @see SBStat#getBaseValue(SBPlayer)
     * @return Default base value
     */
    public abstract double getDefaultValue();

    ////////// To people reading sourcecode //////////
    // Most of them are for rendering, don't worry  //
    // about them                                   //
    //////////////////////////////////////////////////

    /**
     * Get the unique name, basically name space
     * @return Name space
     */
    public abstract String getNamespace();
    public abstract String getDescription(SBPlayer player);
    public abstract String getBaseDescription();
    public abstract String getBonusDescription();
    public abstract String getName();
    public abstract String getIcon();
    public abstract String getColor();
    public abstract ItemStack getIconItemStack();
    public abstract String getExampledDescription(SBPlayer player);

    /**
     * Returns the max possible total value,
     * @return Max total value, < 0 means no limit
     */
    protected double getMaxValue() {
        return -1;
    }

    /**
     * Get the display name that'll be used on SkyBlock Menu etc.
     * @return Display Name, not related to the value
     */
    public String getDisplayName() {
        return getColor() + getIcon() + " " + getName();
    }

    /**
     * What would happen on server tick
     * @param player Owner of the stat
     */
    public abstract void onTick(SBPlayer player);

    @AllArgsConstructor
    @Getter
    public static class StatBonus {
        /**
         * When will it expire
         */
        private final long expirationTime;

        /**
         * Value of the bonus
         */
        private final double value;
    }


}
