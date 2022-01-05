package me.fan87.commonplugin.utils;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoreUtils {

    public static List<String> splitLoreForLine(String input, String linePrefix, String lineSuffix) {
        char[] array = input.toCharArray();
        List<String> out = new ArrayList<>();
        String currentColor = "";
        boolean wasColorChar = false;
        String currentLine = linePrefix;
        String currentWord = "";
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            if (wasColorChar) {
                wasColorChar = false;
                Pattern pattern = Pattern.compile("[0-9a-fkmolnr]");
                if (pattern.matcher(c + "").matches()) {
                    if (c == 'r') {
                        currentColor = ChatColor.COLOR_CHAR + "r";
                    } else {
                        currentColor += ChatColor.COLOR_CHAR + "" + c;
                    }
                }
                currentWord += ChatColor.COLOR_CHAR + "" + c;
                continue;
            }
            if (c == '\n') {
                currentLine += currentWord;
                currentWord = "";
                out.add(currentLine + lineSuffix);
                currentLine = linePrefix + currentColor + currentWord;
                continue;
            }
            if (c == ' ') {
                if (new ChatComponentText(currentLine + currentWord).getText().length() > 32) {
                    out.add(currentLine + lineSuffix);
                    currentLine = linePrefix + currentColor + currentWord;
                } else {
                    currentLine += currentWord + " ";
                }
                currentWord = "";
                continue;
            }
            if (c == ChatColor.COLOR_CHAR) {
                wasColorChar = true;
                continue;
            }
            currentWord += c;
        }
        currentLine += currentWord;
        out.add(currentLine + lineSuffix);
        return out;
    }

    public static List<String> splitLoreForLine(String input) {
        return splitLoreForLine(input, "", "");
    }

}
