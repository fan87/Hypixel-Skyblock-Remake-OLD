package me.fan87.commonplugin.features.impl.api;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.events.impl.XPDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockDropFirer extends SBFeature {
    public BlockDropFirer() {
        super("Block Drop Firer", "Listen to block break event, and post BlockDropEvent", false);
    }
    private Random random = new Random();

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -500)
    public void onBreak(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        event.setCancelled(true);
        breakBlock(player, event);
        event.getBlock().setType(Material.AIR);
    }

    public void breakBlock(SBPlayer player, BlockBreakEvent event) {
        List<ItemStack> drops = new ArrayList<>(event.getBlock().getDrops(player.getPlayer().getItemInHand()));
        BlockDropEvent e = new BlockDropEvent(player, drops, event, random, true);
        EventManager.post(e);
        if (!e.isCancelled()) {
            for (ItemStack newDrop : e.getDrops()) {
                event.getBlock().getLocation().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5, 0.5, 0.5), newDrop);
            }
        }
        XPDropEvent e2 = new XPDropEvent(player, event.getExpToDrop(), event, random);
        EventManager.post(e2);
        if (!e2.isCancelled()) {
            if (e2.getXp() > 0) {
                ExperienceOrb spawn = event.getBlock().getWorld().spawn(event.getBlock().getLocation().add(0.5, 0.5, 0.5), ExperienceOrb.class);
                spawn.setExperience(e2.getXp());
            }
        }
    }

}
