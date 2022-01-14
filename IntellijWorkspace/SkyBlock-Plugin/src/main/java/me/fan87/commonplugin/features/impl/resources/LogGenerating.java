package me.fan87.commonplugin.features.impl.resources;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.impl.api.BlockDropFirer;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import me.fan87.commonplugin.events.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    @Subscribe()
    public void blockBreakEvent(BlockBreakEvent event) {
        SBArea area = skyBlock.getAreasManager().getAreaOf(event.getBlock().getLocation());
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (skyBlock.getAreasManager().canMineLogs(area) &&
                (event.getBlock().getType() == Material.LOG ||
                        event.getBlock().getType() == Material.LOG_2 ||
                        event.getBlock().getType() == Material.LEAVES ||
                        event.getBlock().getType() == Material.LEAVES_2)) {
            generated--;
            Bukkit.getScheduler().runTaskLater(skyBlock, () -> event.getBlock().setType(Material.AIR), 0);

            if (!minedStones.containsKey(event.getBlock().getLocation())) {
                minedStones.put(event.getBlock().getLocation(), new MinedLog(event.getBlock().getType(), System.currentTimeMillis(), event.getBlock().getData()));
            }
            BlockDropFirer feature = skyBlock.getFeaturesManager().getFeature(BlockDropFirer.class);
            feature.breakBlock(player, event);
        }
    }

    int ticks = 0;

    @Subscribe()
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
