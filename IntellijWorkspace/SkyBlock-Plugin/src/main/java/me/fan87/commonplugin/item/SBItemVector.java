package me.fan87.commonplugin.item;

import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.InventoryUtils;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

@Getter
public class SBItemVector {

    private final SBCustomItem item;
    private final int amount;

    public SBItemVector(SBCustomItem item, int amount) {
        this.item = item;
        this.amount = amount;
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount of item cannot be less than 1 (Got " + amount + ")");
        }
    }

    @Override
    public String toString() {
        return this.getItem().getRarity().getColor() + this.getItem().getDisplayName() + (amount!=1? ChatColor.DARK_GRAY + " x" + amount :"");
    }

    public boolean doesPlayerHave(SBPlayer player) {
        int amount = 0;
        for (int i = 0; i < player.getPlayer().getInventory().getSize(); i++) {
            ItemStack item = player.getPlayer().getInventory().getItem(i);
            if (item == null || item.getType() == Material.AIR) continue;
            SBItemStack itemStack = new SBItemStack(item);
            if (itemStack.getType().getItem().getNamespace().equals(this.item.getNamespace()) && itemStack.canBeUsedForCrafting()) {
                amount += item.getAmount();
                if (amount >= this.amount) return true;
            }
        }
        return false;
    }

    public boolean takeItemFrom(SBPlayer player) {
        if (!doesPlayerHave(player)) {
            return false;
        }
        int amount = this.amount;
        for (int i = 0; i < player.getPlayer().getInventory().getSize(); i++) {
            ItemStack item = player.getPlayer().getInventory().getItem(i);
            if (item == null || item.getType() == Material.AIR) continue;
            SBItemStack itemStack = new SBItemStack(item);
            if (itemStack.getType().getItem() == this.item && itemStack.canBeUsedForCrafting()) {
                int take = Math.min(amount, item.getAmount());
                amount -= take;
                if (item.getAmount() - take == 0) {
                    item.setType(Material.AIR);
                    player.getPlayer().getInventory().setItem(i, item);
                } else {
                    item.setAmount(item.getAmount() - take);
                }
                if (amount == 0) return true;
            }
        }
        return true;
    }

    public boolean giveItem(SBPlayer player) {
        if (InventoryUtils.isInventoryFull(player.getPlayer().getInventory())) {
            return false;
        }
        ItemStack build = new ItemStackBuilder(item.newItemStack()).setAmount(amount).build();
        InventoryUtils.giveItem(player.getPlayer(), new SBItemStack(build).getItemStack());
        return true;
    }

    public ItemStack getDisplayItem() {
        ItemStack clone = new ItemStackBuilder(new SBItemStack(item.newItemStack()).getDisplayItemStack())
                .setDisplayName(toString())
                .setAmount(amount)
                .build().clone();
        return clone;
    }

    public SBItemVector multiply(float multiplier) {
        return new SBItemVector(item, (int) Math.ceil(multiplier*amount));
    }

}
