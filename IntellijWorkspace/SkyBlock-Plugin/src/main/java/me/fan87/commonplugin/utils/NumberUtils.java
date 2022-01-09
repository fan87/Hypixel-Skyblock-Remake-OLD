package me.fan87.commonplugin.utils;

import org.bukkit.ChatColor;

public class NumberUtils {

    public static boolean isBetween(double input, double from, double to) {
        if (from > to) {
            double tmpFrom = from;
            from = to;
            to = tmpFrom;
        }
        return input > from && input < to;
    }

    public static String formatNumber(double number) {
        String format = number<1000?String.format("%,.1f", Math.round(number * 10d) / 10d):String.format("%,d", Math.round(number));
        if (format.endsWith(".0")) format = format.substring(0, format.length() - 2);
        return format;
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
            double d = Math.floor(number * 10d) / 10d;
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

    public static String getPercentageText(String title, double value, double max, String percentageIcon) {
        double d = Math.round(value / max * 1000d)/10d;
        d = Math.min(d, 100);
        String string = Double.toString(d);
        return ChatColor.GRAY + title + ChatColor.GRAY + ": " + (d>=100?ChatColor.GREEN:ChatColor.YELLOW) + (string.endsWith(".0")? string.substring(0, string.length() - 2): string) + percentageIcon;
    }

    public static String getPercentageText(String title, double value, double max) {
        return getPercentageText(title, value, max, ChatColor.GOLD + "%");
    }

    public static String generateProgressBar(int value, int max) {
        float a = value*1f/max;
        a = Math.min(1f, a);
        StringBuilder progressBar = new StringBuilder();
        progressBar.append(ChatColor.GREEN);
        for (int unused = 0; unused < Math.floor(a * 20); unused++) {
            progressBar.append("-");
        }
        progressBar.append(ChatColor.WHITE);
        for (int j = 0; j < 20 - Math.floor(a * 20); j++) {
            progressBar.append("-");
        }
        progressBar.append(" ").append(ChatColor.YELLOW).append(NumberUtils.formatNumber(value)).append(ChatColor.GOLD).append("/").append(ChatColor.YELLOW).append(NumberUtils.formatLargeNumber(max, false));
        return progressBar.toString();
    }

    // https://stackoverflow.com/questions/6810336/is-there-a-way-in-java-to-convert-an-integer-to-its-ordinal-name
    public static String ordinal(int i) {
        String[] suffixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (i % 100) {
            case 11:
            case 12:
            case 13:
                return i + "th";
            default:
                return i + suffixes[i % 10];

        }
    }

}
