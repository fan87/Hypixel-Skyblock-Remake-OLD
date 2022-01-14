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
    public static final CharsProvider INVISIBLE_CHAR_PROVIDER = () -> "\uD83D\uDC42\uD83D\uDD78\uD83D\uDD57\uD83D\uDCFF\uD83C\uDFBE\uD83D\uDE2C\uD83C\uDF6A\uD83D\uDE27\uD83D\uDE9E\uD83D\uDEA6\uD83C\uDF0A\uD83C\uDFDB\uD83D\uDC1A\uD83D\uDE43\uD83D\uDC19\uD83D\uDE95\uD83C\uDF8A\uD83D\uDCE6\uD83C\uDF4F\uD83D\uDCB0\uD83D\uDC48\uD83C\uDF27\uD83D\uDC12\uD83D\uDCA2\uD83C\uDFCE\uD83C\uDF61\uD83D\uDD1F\uD83D\uDD13\uD83C\uDF53";

    public static String generateRandomString(CharsProvider charsProvider, int length) {
        String chars = charsProvider.getChars();
        if (chars.length() == 0) throw new IllegalArgumentException("Char Provider is providing empty string!");
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            out.append(chars.charAt(random.nextInt(chars.length() - 1)));
        }
        return out.toString();
    }

    public static String generateJoinedText(String input, CharsProvider provider) {
        StringBuilder output = new StringBuilder();
        Random random = new Random();
        char[] chars = input.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (random.nextInt(50) < 45) {
                output.append(provider.getChars().charAt(random.nextInt(provider.getChars().length() - 1))).append(chars[index]);
            } else {
                output.append(chars[index]);
            }
        }
        return output.toString();
    }
    
    
}
