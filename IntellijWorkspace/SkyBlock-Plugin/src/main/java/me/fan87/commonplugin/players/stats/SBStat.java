package me.fan87.commonplugin.players.stats;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
