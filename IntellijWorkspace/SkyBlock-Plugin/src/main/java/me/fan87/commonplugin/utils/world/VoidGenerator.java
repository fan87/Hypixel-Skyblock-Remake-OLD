package me.fan87.commonplugin.utils.world;

import me.fan87.commonplugin.utils.WorldGenerationUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VoidGenerator extends ChunkGenerator {

    @Override
    public boolean canSpawn(World world, int x, int z) {
        return true;
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 4, 0);
    }

    @Override
    public byte[] generate(World world, Random random, int x, int z) {
        byte[] out = WorldGenerationUtils.generateEmptyChunkData();
        if (x == 0 && z == 0) {
            out[WorldGenerationUtils.xyzToByteLoc(0, 3, 0)] = 8;
        }
        return out;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new ArrayList<>();
    }
}
