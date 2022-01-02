package me.fan87.commonplugin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;

public class ColorUtils {

    @Getter
    @AllArgsConstructor
    public static class RainbowStyle {
        public static final RainbowStyle CRITICAL = new RainbowStyle(new ChatColor[] {ChatColor.WHITE, ChatColor.WHITE, ChatColor.YELLOW, ChatColor.GOLD, ChatColor.RED});

        ChatColor[] colors;
    }

    public static String generateRainbowText(String input, RainbowStyle rainbowStyle) {
        StringBuilder output = new StringBuilder();
        char[] chars = input.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            ChatColor color = rainbowStyle.colors[index % rainbowStyle.colors.length];
            output.append(color).append(chars[index]);
        }
        return output.toString();
    }

}
