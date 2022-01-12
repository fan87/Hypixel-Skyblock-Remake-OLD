package me.fan87.commonplugin.features.impl.stat;

import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.inventory.ItemStack;
import me.fan87.commonplugin.events.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FarmingFortuneListener extends SBFeature {
    public FarmingFortuneListener() {
        super("Farming Fortune Listener", "Code that modifies the item drop amount", false);
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
                (block.getType() == Material.MELON_BLOCK) ||
                (block.getType() == Material.POTATO && ((Ageable) block).getAge() == 7) ||
                (block.getType() == Material.CARROT && ((Ageable) block).getAge() == 7) ||
                (block.getType() == Material.COCOA && ((Ageable) block).getAge() >= 8) ||
                (block.getType() == Material.WHEAT && ((Ageable) block).getAge() == 7)
        ) {
            Random random = event.getRandom();
            List<ItemStack> drops = new ArrayList<>();
            for (ItemStack drop : event.getDrops()) {
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
