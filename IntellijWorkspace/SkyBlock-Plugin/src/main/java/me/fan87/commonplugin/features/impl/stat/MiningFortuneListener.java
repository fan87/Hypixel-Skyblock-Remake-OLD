package me.fan87.commonplugin.features.impl.stat;

import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import me.fan87.commonplugin.events.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MiningFortuneListener extends SBFeature {
    public MiningFortuneListener() {
        super("Mining Fortune Listener", "Code that modifies the item drop amount", false);
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
                block.getType() == Material.COAL_ORE ||
                block.getType() == Material.IRON_ORE ||
                block.getType() == Material.GOLD_ORE ||
                block.getType() == Material.LAPIS_ORE ||
                block.getType() == Material.REDSTONE_ORE ||
                block.getType() == Material.EMERALD_ORE ||
                block.getType() == Material.DIAMOND_ORE ||
                block.getType() == Material.QUARTZ_ORE
        ) {
            Random random = event.getRandom();
            List<ItemStack> drops = new ArrayList<>();
            for (ItemStack drop : event.getDrops()) {
                Material type = drop.getType();
                int data = drop.getDurability();
                if (
                        type == Material.COAL ||
                        type == Material.IRON_INGOT ||
                        type == Material.GOLD_INGOT ||
                        (type == Material.INK_SACK && data == 4) ||
                        type == Material.REDSTONE ||
                        type == Material.EMERALD ||
                        type == Material.DIAMOND ||
                        type == Material.QUARTZ
                ) {

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
            }
            event.setDrops(drops);
        }

    }

}
