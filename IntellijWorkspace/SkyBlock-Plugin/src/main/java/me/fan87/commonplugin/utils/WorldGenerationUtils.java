package me.fan87.commonplugin.utils;

public class WorldGenerationUtils {

    /**
     * chunkData[(x * 16 + z) * 128 + y] = blockId;
     * @param x X, max 16
     * @param y Y, max 256
     * @param z Z, max 16
     * @return (x * 16 + z) * 128 + y
     */
    public static int xyzToByteLoc(int x, int y, int z) {
        return (x * 16 + z) * 128 + y;
    }

    /**
     * @return Empty chunk data byte array
     */
    public static byte[] generateEmptyChunkData() {
        return new byte[65536];
    }

}
