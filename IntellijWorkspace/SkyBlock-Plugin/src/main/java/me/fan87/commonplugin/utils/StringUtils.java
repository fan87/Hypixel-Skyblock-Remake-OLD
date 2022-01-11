package me.fan87.commonplugin.utils;

import java.util.Random;

public class StringUtils {
    private static final Random random = new Random();

    public interface CharsProvider {
        String getChars();
    }

    public static final CharsProvider ALPHABET_PROVIDER = () -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final CharsProvider NUMBER_PROVIDER = () -> "1234567890";
    public static final CharsProvider ALPHABET_NUMBER_PROVIDER = () -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final CharsProvider NPC_NAME_PROVIDER = () -> "abcdefghijklmnopqrstuvwxyz1234567890";
    public static final CharsProvider BASE64_PROVIDER = () -> "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890+/";

    public static String generateRandomString(CharsProvider charsProvider, int length) {
        String chars = charsProvider.getChars();
        if (chars.length() == 0) throw new IllegalArgumentException("Char Provider is providing empty string!");
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            out.append(chars.charAt(random.nextInt(chars.length() - 1)));
        }
        return out.toString();
    }

}
