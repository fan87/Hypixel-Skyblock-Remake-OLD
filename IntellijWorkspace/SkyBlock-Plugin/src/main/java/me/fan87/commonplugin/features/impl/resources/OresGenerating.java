package me.fan87.commonplugin.features.impl.resources;

import com.google.common.primitives.Ints;
import lombok.*;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.world.*;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

import java.io.*;
import java.util.*;

public class OresGenerating extends SBFeature {

    private final List<OreSpawn> oreSpawns = new ArrayList<>();
    private final Map<Location, MinedStone> minedStones = new HashMap<>();
    private int generated;

    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class OreSpawn {
        private Location location;
        private SBArea area;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class MinedStone {
        private Material material;
        private long mineTime;
    }

    public OresGenerating() {
        super("Ores Generating", "Generates ores on islands.", true);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void blockBreakEvent(BlockBreakEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getBlock().getWorld().getName());
        SBArea area = skyBlock.getAreasManager().getAreaOf(event.getBlock().getLocation());
        boolean canMine = skyBlock.getAreasManager().getOresToGenerate(area).size() > 0;
        if (canMine && skyBlock.getAreasManager().getOresToGenerate(area).contains(event.getBlock().getType())) {
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
            generated--;
            Random random = new Random();
            for (ItemStack drop : event.getBlock().getDrops()) {
                Location spawnLocation = event.getBlock().getLocation();
                double d0 = (double)(random.nextFloat() * 0.5) + (double)(1.0F - 0.5) * 0.5D;
                double d1 = (double)(random.nextFloat() * 0.5) + (double)(1.0F - 0.5) * 0.5D;
                double d2 = (double)(random.nextFloat() * 0.5) + (double)(1.0F - 0.5) * 0.5D;
                spawnLocation = spawnLocation.add(d0, d1, d2);
                for (int i = -1; i < (int) player.getStats().getMiningFortune().getValue(player)/100; i++) {
                    event.getBlock().getWorld().dropItem(spawnLocation, drop);
                }
                if (random.nextInt(99) + 1 < player.getStats().getMiningFortune().getValue(player) % 100) {
                    event.getBlock().getWorld().dropItem(spawnLocation, drop);
                }
            }
            for (int i = 0; i < event.getExpToDrop(); i++) {
                event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
            }
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.STONE), 0);

            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(Material.STONE, System.currentTimeMillis()));
            }
        }
        if (canMine && (event.getBlock().getType() == Material.STONE && event.getBlock().getData() == 0)) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), drop);
            }
            for (int i = 0; i < event.getExpToDrop(); i++) {
                event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
            }
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.COBBLESTONE), 0);
            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(Material.STONE, System.currentTimeMillis()));
            }
        }

        if (canMine && (event.getBlock().getType() == Material.COBBLESTONE) && event.getBlock().getData() == 0) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), drop);
            }
            for (int i = 0; i < event.getExpToDrop(); i++) {
                event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
            }
            event.setCancelled(true);
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.BEDROCK), 0);
            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(event.getBlock().getType(), System.currentTimeMillis()));
            }
        }
    }

    int ticks = 0;

    @Subscribe
    public void onTick(ServerTickEvent event) {
        ticks++;
        if (ticks % 100 == 0 && generated < oreSpawns.size()/100) {
            Random random = new Random();
            System.out.println(oreSpawns.size());
            for (int i = 0; i < oreSpawns.size()/1000; i++) {
                OreSpawn oreSpawn = oreSpawns.get(random.nextInt(oreSpawns.size()));
                if (oreSpawn.getLocation().getBlock().getType() == Material.STONE) {
                    List<Material> oresToGenerate = skyBlock.getAreasManager().getOresToGenerate(oreSpawn.getArea());
                    oreSpawn.getLocation().getBlock().setType(oresToGenerate.get(random.nextInt(oresToGenerate.size())));
                    generated++;
                }
            }
        }
        Set<Location> locations = minedStones.keySet();
        for (Location location : new ArrayList<>(locations)) {
            if (System.currentTimeMillis() - minedStones.get(location).mineTime > 5000) {
                location.getBlock().setType(minedStones.get(location).getMaterial());
                minedStones.remove(location);
            }
        }
    }

    @Subscribe
    public void onChunkUnload(ChunkUnloadEvent event) {
        event.setCancelled(true);
    }

    @Subscribe
    public void onWorldUnloaded(WorldUnloadEvent event) {
        oreSpawns.removeIf(oreSpawn -> oreSpawn.getLocation().getWorld() == event.getWorld());
    }

    @Subscribe
    public void onWorldLoaded(WorldLoadEvent event) {
        Bukkit.getScheduler().runTaskLater(skyBlock, new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                if (hasCache(event.getWorld())) {
                    File cacheFile = getCacheFile(event.getWorld());
                    FileInputStream inputStream = new FileInputStream(cacheFile);
                    byte[] buffer = new byte[4];
                    int posIndex = 0;
                    int[] intBuffer = new int[3];

                    try {
                        while (inputStream.read(buffer) != -1) {
                            intBuffer[posIndex++] = buffer[0] << 24 | (buffer[1] & 0xFF) << 16 | (buffer[2] & 0xFF) << 8 | (buffer[3] & 0xFF);
                            if (posIndex == 3) {
                                posIndex = 0;
                                Location location = new Location(event.getWorld(), intBuffer[0], intBuffer[1], intBuffer[2]);
                                SBArea area = skyBlock.getAreasManager().getAreaOf(location);
                                if (event.getWorld().getBlockAt(location).getType() != Material.SPONGE) {
                                    throw new RuntimeException("A block is not sponge! Exiting...");
                                }
                                oreSpawns.add(new OreSpawn(location, area));
                            }
                        }
                        for (OreSpawn oreSpawn : oreSpawns) {
                            oreSpawn.getLocation().getBlock().setType(Material.STONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Corrupted cache file! Deleting...");
                        getCacheFile(event.getWorld()).delete();
                        skyBlock.getServer().reload();
                    }
                } else {
                    saveCache(event.getWorld());
                }
            }
        }, 1);
    }

    @SneakyThrows
    public void saveCache(World world) {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(getCacheFile(world)));
        byte[] buffer = new byte[3*4*oreSpawns.size()];
        byte[] array = new byte[0];
        for (int i = 0; i < oreSpawns.size(); i++) {
            Location location = oreSpawns.get(i).getLocation();
            array = Ints.toByteArray(location.getBlockX());
            for (int k = 0; k < 4; k++) {
                buffer[i*12 + k] = array[k];
            }
            array = Ints.toByteArray(location.getBlockY());
            for (int k = 0; k < 4; k++) {
                buffer[i*12 + k + 4] = array[k];
            }
            array = Ints.toByteArray(location.getBlockZ());
            for (int k = 0; k < 4; k++) {
                buffer[i*12 + k + 8] = array[k];
            }
        }
        outputStream.write(buffer);
        outputStream.close();
    }

    @Subscribe
    public void onChunkLoaded(ChunkLoadEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getWorld().getName());
        if (!hasCache(event.getWorld()) && world.getWorldType() == WorldsManager.WorldType.SKYBLOCK_HUB || world.getWorldType() == WorldsManager.WorldType.GOLD_MINE || world.getWorldType() == WorldsManager.WorldType.DEEP_CAVERNS) {
            Chunk chunk = event.getChunk();
            SBArea area = skyBlock.getAreasManager().getAreaOf(new Location(event.getWorld(), chunk.getX() * 16, 71, chunk.getZ() * 16));
            if (skyBlock.getAreasManager().getOresToGenerate(area).size() > 0) {
                System.out.println("Generating " + chunk.getX() + " / " + chunk.getZ());
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        long l = System.currentTimeMillis();
                        if (skyBlock.getAreasManager().getOresToGenerate(area).size() > 0) {
                            for (int y = 0; y < 256; y++) {
                                SBArea a = skyBlock.getAreasManager().getAreaOf(new Location(event.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16));
                                Block block = event.getChunk().getBlock(x, y, z);
                                if (block.getType() == Material.SPONGE) {
                                    oreSpawns.add(new OreSpawn(new Location(event.getWorld(), chunk.getX()*16 + x, y, chunk.getZ()*16 + z), a));
                                    block.setType(Material.STONE);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean hasCache(World world) {
        File cache = new File("cache");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        File file = new File(cache, world.getName() + ".ores");
        return file.exists() && FileUtils.sizeOf(file) > 0;
    }

    @SneakyThrows
    public File getCacheFile(World world) {
        File cache = new File("cache");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        File file = new File(cache, world.getName() + ".ores");
        if (!file.exists()) file.createNewFile();
        return file;
    }
}
