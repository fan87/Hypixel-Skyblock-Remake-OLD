package me.fan87.commonplugin.utils;

import org.bukkit.ChatColor;

public class NumberUtils {

    public static boolean isBetween(double input, double from, double to) {
        return input > from && input < to;
    }

    public static String formatNumber(double number) {
        return String.format("%,.1f", Math.round(number * 10d) / 10d);
    }

    private static String[] large = new String[] {
            "k",
            "m",
            "b",
            "t",
            "qa",
            "qi",
            "sx",
            "sp",
            "oct", // Why do I feel like it will 64 bit overflow lol
    };
    public static String formatLargeNumber(double number, boolean forceDecPoint) {
        int unit = -1;
        while (number > 1000) {
            if (forceDecPoint || number/1000d <= 10) {
                number = Math.floor(number/100d)/10d;
            } else {
                number = Math.floor(number/1000d);
            }
            unit++;
        }
        if (unit == -1) {
            double d = Math.floor(number / 100d) / 10d;
            return Double.toString(d).endsWith(".0")?(int) d + "":d + "";
        }
        return (Double.toString(number).endsWith(".0")?(int) number + "":number) + large[unit];
    }

    public static String valueChangeDisplay(int oldValue, int newValue) {
        if (oldValue <= 0) {
            return ChatColor.GREEN + "+" + newValue;
        } else {
            return ChatColor.GREEN + "+" + ChatColor.DARK_GRAY + oldValue + "➜" + ChatColor.GREEN + newValue;
        }
    }

}
