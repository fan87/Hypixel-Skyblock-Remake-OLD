package me.fan87.commonplugin.features.impl.stat;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ForagingFortuneListener extends SBFeature {
    public ForagingFortuneListener() {
        super("Foraging Fortune Listener", "Code that modifies the item drop amount", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -100)
    public void onBlockDrop(BlockDropEvent event) {
        Block block = event.getBlockBreakEvent().getBlock();
        SBPlayer player = event.getPlayer();
        if (
                block.getType() == Material.LOG ||
                block.getType() == Material.LOG_2
        ) {
            Random random = event.getRandom();
            List<ItemStack> drops = new ArrayList<>();
            for (ItemStack drop : new ArrayList<>(event.getDrops())) {
                int amount = drop.getAmount();
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
                    drops.add(clone);
                }
            }
            event.setDrops(drops);
        }
    }

}
