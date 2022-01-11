package me.fan87.commonplugin.features.impl.resources;

import com.google.common.primitives.Ints;
import lombok.*;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ModifiedDropsEvent;
import me.fan87.commonplugin.events.impl.ModifiedXPDropsEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.Vec3d;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.*;

public class OresGenerating extends SBFeature {

    private final Map<String, List<OreSpawn>> oreSpawns = new HashMap<>();
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
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        Random random = new Random();
        List<ItemStack> newDrops = new ArrayList<>();
        int amount = 0;
        if (canMine && skyBlock.getAreasManager().getOresToGenerate(area).contains(event.getBlock().getType())) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                amount = drop.getAmount();
                amount += (int) player.getStats().getMiningFortune().getValue(player)/100;
                if (random.nextInt(99) + 1 < player.getStats().getMiningFortune().getValue(player) % 100) {
                    amount++;
                }
                int left = amount;
                while (left > 0) {
                    int count = Math.min(left, drop.getMaxStackSize());
                    left -= count;
                    ItemStack clone = drop.clone();
                    clone.setAmount(count);
                    newDrops.add(clone);
                }
            }
            generated--;
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.STONE), 0);

            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(Material.STONE, System.currentTimeMillis()));
            }
        }
        if (canMine && (event.getBlock().getType() == Material.STONE && event.getBlock().getData() == 0)) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                amount = drop.getAmount();
                int left = amount;
                while (left > 0) {
                    int count = Math.min(left, drop.getMaxStackSize());
                    left -= count;
                    ItemStack clone = drop.clone();
                    clone.setAmount(count);
                    newDrops.add(clone);
                }
            }
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.COBBLESTONE), 0);
            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(Material.STONE, System.currentTimeMillis()));
            }
        }

        if (canMine && (event.getBlock().getType() == Material.COBBLESTONE) && event.getBlock().getData() == 0) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                amount = drop.getAmount();
                int left = amount;
                while (left > 0) {
                    int count = Math.min(left, drop.getMaxStackSize());
                    left -= count;
                    ItemStack clone = drop.clone();
                    clone.setAmount(count);
                    newDrops.add(clone);
                }
            }
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.BEDROCK), 0);
            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedStone(event.getBlock().getType(), System.currentTimeMillis()));
            }
        }
        ModifiedDropsEvent e = new ModifiedDropsEvent(newDrops, event);
        EventManager.EVENT_BUS.postSticky(e);
        if (!e.isCancelled()) {
            for (ItemStack newDrop : e.getDrops()) {
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), newDrop);
            }
        }
        ModifiedXPDropsEvent e2 = new ModifiedXPDropsEvent(event.getExpToDrop(), event);
        EventManager.EVENT_BUS.postSticky(e2);
        if (!e2.isCancelled()) {
            if (e2.getXp() > 0) {
                ExperienceOrb spawn = event.getBlock().getWorld().spawn(event.getBlock().getLocation(), ExperienceOrb.class);
                spawn.setExperience(e2.getXp());
            }
        }
    }

    int ticks = 0;

    @Subscribe
    public void onTick(ServerTickEvent event) {
        ticks++;
        try {
            if (oreSpawns.size() == 0) return;
            for (World world : skyBlock.getServer().getWorlds()) {
                List<OreSpawn> spawns = this.oreSpawns.get(world.getName());
                if (spawns == null) continue;
                if (ticks % 100 == 0 && generated < spawns.size()*0.98) {
                    Random random = new Random();
                    for (int i = 0; i < spawns.size()/1000; i++) {
                        OreSpawn oreSpawn = spawns.get(random.nextInt(spawns.size()));
                        List<Material> oresToGenerate = skyBlock.getAreasManager().getOresToGenerate(oreSpawn.getArea());
                        if (oreSpawn.getLocation().getBlock().getType() == Material.STONE && oresToGenerate.size() > 0) {
                            oreSpawn.getLocation().getBlock().setType(oresToGenerate.get(random.nextInt(oresToGenerate.size())));
                            generated++;
                        }
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onChunkUnload(ChunkUnloadEvent event) {
        event.setCancelled(true);
    }

    @Subscribe
    public void onWorldUnloaded(WorldUnloadEvent event) {
        oreSpawns.remove(event.getWorld().getName());
    }

    @Subscribe
    public void onWorldLoaded(WorldLoadEvent event) {
        Bukkit.getScheduler().runTaskLater(skyBlock, () -> {
            skyBlock.sendMessage(ChatColor.YELLOW + "Init world: " + event.getWorld().getName());
            SBWorld world = skyBlock.getWorldsManager().getWorld(event.getWorld().getName());
            Vec3d centerPos = world.getSpawn();
            int radius = world.getPreScanArea();
            int count = 0;
            long lastTime = System.currentTimeMillis();
            double avg = 0;
            for (int x = -radius; x < radius; x++) {
                for (int y = -radius; y < radius; y++) {
                    if (event.getWorld().loadChunk((int) centerPos.getX() /16 + x, (int) centerPos.getZ() /16 + y, true)) {
                        Chunk chunkAt = event.getWorld().getChunkAt((int) centerPos.getX() / 16 + x, (int) centerPos.getZ() / 16 + y);
                        skyBlock.sendMessage(ChatColor.YELLOW + "Scanning Chunk " + event.getWorld().getName() + " ( " + count + " / " + (radius*2)*(radius*2) + ") ");
                        lastTime = System.currentTimeMillis();
                        scanChunk(chunkAt);
                        avg = (avg*count + (System.currentTimeMillis() - lastTime))/(count+1);
                        Duration duration = Duration.ofMillis((long) (((radius*2d)*(radius*2d) - count) * avg));
                        skyBlock.sendMessage(ChatColor.GREEN + "Scanned Chunk: " + event.getWorld().getName() +
                                ", took " + (System.currentTimeMillis() - lastTime) +
                                "ms  (Avg time: " + Math.ceil(avg) + String.format("  Predicted time: %02d:%02d:%02d)", duration.toHours(), duration.toMinutes(), duration.getSeconds() % 60));
                        count++;
                        skyBlock.sendMessage("");
                    }
                }
            }
            saveCache(event.getWorld());
        }, 1);
    }

    @SneakyThrows
    public void saveCache(World world) {
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(getCacheFile(world)));
        List<OreSpawn> oreSpawns = this.oreSpawns.get(world.getName());
        byte[] buffer = new byte[3*4* oreSpawns.size()];
        byte[] array = new byte[0];
        if (buffer.length > 0) {
            skyBlock.sendMessage(world.getName() + " / Buffer Size: " + buffer.length);
        }
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

    public void scanChunk(Chunk chunk) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(chunk.getWorld().getName());
        if (world.getWorldType() == WorldsManager.WorldType.SKYBLOCK_HUB || world.getWorldType() == WorldsManager.WorldType.GOLD_MINE || world.getWorldType() == WorldsManager.WorldType.DEEP_CAVERNS) {
            if (!hasCache(chunk.getWorld())) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        SBArea area = skyBlock.getAreasManager().getAreaOf(new Location(chunk.getWorld(), chunk.getX() * 16 + x, 64, chunk.getZ() * 16 + z));
                        if (skyBlock.getAreasManager().getOresToGenerate(area).size() > 0) {
                            for (int y = 0; y < 256; y++) {
                                SBArea a = skyBlock.getAreasManager().getAreaOf(new Location(chunk.getWorld(), chunk.getX() * 16 + x, y, chunk.getZ() * 16));
                                Block block = chunk.getBlock(x, y, z);
                                if (block.getType() == Material.SPONGE) {
                                    putOreSpawn(chunk.getWorld(), new OreSpawn(new Location(chunk.getWorld(), chunk.getX()*16 + x, y, chunk.getZ()*16 + z), a));
                                    block.setType(Material.STONE);
                                }
                            }
                        }
                    }
                }
            } else {
                loadCache(chunk.getWorld());
            }
        }
    }

    @SneakyThrows
    public void loadCache(World world) {
        File cacheFile = getCacheFile(world);
        FileInputStream inputStream = new FileInputStream(cacheFile);
        byte[] buffer = new byte[24];
        while (inputStream.read(buffer) != -1) {
            Vec3d vec3d = Vec3d.fromByteArray(buffer);
            Location location = new Location(world, vec3d.getX(), vec3d.getY(), vec3d.getZ());
            putOreSpawn(world, new OreSpawn(location, skyBlock.getAreasManager().getAreaOf(location)));
        }
    }

    public void putOreSpawn(World world, OreSpawn spawn) {
        if (oreSpawns.get(world.getName()) != null) {
            oreSpawns.get(world.getName()).add(spawn);
            return;
        }
        ArrayList<OreSpawn> value = new ArrayList<>();
        value.add(spawn);
        oreSpawns.put(world.getName(), value);
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
