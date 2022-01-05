package me.fan87.commonplugin.utils;

import lombok.SneakyThrows;
import sun.misc.IOUtils;

import java.io.InputStream;

public class LangUtils {

    /**
     * Get vanilla text, for example: tile.dirt.name will return Dirt as logn as it's in en_US.lang
     * @param original Namespace or whatever you want to call it
     * @return The translated name, if it's not found it will return `original` directly
     */
    @SneakyThrows
    public static String getName(String original) {
        InputStream resourceAsStream = LangUtils.class.getClassLoader().getResourceAsStream("en_US.lang");
        byte[] bytes = IOUtils.readNBytes(resourceAsStream, Integer.MAX_VALUE);
        String content = new String(bytes);
        for (String s : content.split("\n")) {
            String key = "";
            String value = "";
            boolean isInKey = true;
            for (char c : s.toCharArray()) {
                if (c == '#') {
                    break;
                }
                if (isInKey) {
                    if (c == '=') {
                        isInKey = false;
                        if (!key.equals(original)) {
                            break;
                        }
                        continue;
                    }
                    key += c;
                } else {
                    value += c;
                }
            }
            if (value.length() > 0) {
                return value;
            }
        }
        return original;
    }

}
