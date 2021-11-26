package me.fan87.commonplugin.players.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class SBStat {

    public SBStat() {
        baseValue = getDefaultValue();
    }

    @Getter
    @Setter
    private double baseValue;

    @Getter
    @Setter
    private List<StatBonus> bonusValue = new ArrayList<>();

    public double getTotalBonusValue() {
        double value = 0;
        for (StatBonus statBonus : new ArrayList<>(bonusValue)) {
            if (System.currentTimeMillis() > statBonus.expirationTime) {
                bonusValue.remove(statBonus);
                continue;
            }
            value += statBonus.getValue();
        }
        return value;
    }

    public double getValue() {
        return getBaseValue() + getTotalBonusValue();
    }

    public abstract String getValueDisplay(double value) ;


    public abstract double getDefaultValue();

    public abstract String getNamespace();
    public abstract String getDescription(SBPlayer player);
    public abstract String getBaseDescription();
    public abstract String getBonusDescription();
    public abstract String getName();
    public abstract String getIcon();
    public abstract String getColor();
    public abstract ItemStack getIconItemStack();
    public abstract String getPerPlayerDescription(SBPlayer player);

    public String getDisplayName() {
        return getColor() + getIcon() + " " + getName();
    }


    public abstract void onTick(SBPlayer player);

    @AllArgsConstructor
    @Getter
    public static class StatBonus {
        long expirationTime;
        double value;
    }


}
