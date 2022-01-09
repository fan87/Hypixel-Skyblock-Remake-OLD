package me.fan87.commonplugin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

public class IngameDate {

    @Getter
    private final long ingameTime;

    public IngameDate(long ingameTime) {
        this.ingameTime = ingameTime;
    }

    public long getTotalDays() {
        return ingameTime/1200000;
    }

    public int getDayOfYear() {
        return (int) (getTotalDays() % 372L) + 1;
    }

    public int getYear() {
        return (int) (getTotalDays()/372L);
    }

    public int getMonth() {
        return (getDayOfYear() - 1)/31 + 1;
    }

    public int getDayOfMonth() {
        return (getDayOfYear() - 1) % 31 + 1;
    }

    public MonthDisplay getMonthDisplay() {
        for (MonthDisplay value : MonthDisplay.values()) {
            if (value.getMonth() == getMonth()) {
                return value;
            }
        }
        return MonthDisplay.UNKNOWN;
    }

    public String getDayOfMonthDisplay() {
        return ChatColor.RESET + NumberUtils.ordinal(getDayOfMonth());
    }

    public long getMSOfDay() {
        return ingameTime % 1200000;
    }
    public int getHour() {
        return (int) getMSOfDay()/50000 + 1;
    }
    public int getMin() {
        long msOfHour = getMSOfDay() % 50000;
        for (int i = 0; i < 6; i++) {
            if (msOfHour < (50000/6)*(i + 1) && msOfHour >= (50000/6)*i) {
                return (i + 1) * 10;
            }
        }
        return 0;
    }

    public String getTimeDisplay() {
        return String.format("%d:%2d", getHour()%12, getMin()) + (getHour() >= 12?"pm":"am") + (getHour()>=6&&getHour()<=19?ChatColor.AQUA + " ☽": ChatColor.YELLOW + " ☀");
    }

    @Getter
    @AllArgsConstructor
    public enum MonthDisplay {
        UNKNOWN("Unknown", 0),
        EARLY_SPRING("Early Spring", 1),
        SPRING("Spring", 2),
        LATE_SPRING("Late Spring", 3),
        EARLY_SUMMER("Early Summer", 4),
        SUMMER("Summer", 5),
        LATE_SUMMER("Late Summer", 6),
        EARLY_AUTUMN("Early Autumn", 7),
        AUTUMN("Autumn", 8),
        LATE_AUTUMN("Late Autumn", 9),
        EARLY_WINTER("Early Winter", 10),
        WINTER("Winter", 11),
        LATE_WINTER("Late Winter", 12);

        private String name;
        private int month;
    }

}
