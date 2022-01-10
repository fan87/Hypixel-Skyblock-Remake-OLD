package me.fan87.commonplugin.features.impl.resources;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ModifiedDropsEvent;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

import java.util.*;

public class LogGenerating extends SBFeature {

    private final Map<Location, MinedLog> minedStones = new HashMap<>();
    private int generated;



    @Getter
    @Setter
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class MinedLog {
        private Material material;
        private long mineTime;
        private byte data;
    }

    public LogGenerating() {
        super("Logs Generating", "Generates logs on islands.", true);
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
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        Random random = new Random();
        List<ItemStack> newDrops = new ArrayList<>();
        int amount = 0;
        if (skyBlock.getAreasManager().canMineLogs(area) &&
                (event.getBlock().getType() == Material.LOG ||
                        event.getBlock().getType() == Material.LOG_2 ||
                        event.getBlock().getType() == Material.LEAVES ||
                        event.getBlock().getType() == Material.LEAVES_2)) {
            for (ItemStack drop : event.getBlock().getDrops()) {
                amount = drop.getAmount();
                amount += (int) player.getStats().getForagingFortune().getValue(player)/100;
                if (random.nextInt(99) + 1 < player.getStats().getForagingFortune().getValue(player) % 100) {
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
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.AIR), 0);

            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedLog(event.getBlock().getType(), System.currentTimeMillis(), event.getBlock().getData()));
            }
        }
        ModifiedDropsEvent e = new ModifiedDropsEvent(newDrops, event);
        EventManager.EVENT_BUS.postSticky(e);
        if (!e.isCancelled()) {
            for (ItemStack newDrop : e.getDrops()) {
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation(), newDrop);
            }
        }
    }

    int ticks = 0;

    @Subscribe
    public void onTick(ServerTickEvent event) {
        ticks++;
        if (ticks % 200 != 0) return;
        Set<Location> locations = minedStones.keySet();
        for (Location location : new ArrayList<>(locations)) {
            if (System.currentTimeMillis() - minedStones.get(location).mineTime > 30000) {
                location.getBlock().setType(minedStones.get(location).getMaterial());
                location.getBlock().setData(minedStones.get(location).getData());
                minedStones.remove(location);
            }
        }
    }


}
