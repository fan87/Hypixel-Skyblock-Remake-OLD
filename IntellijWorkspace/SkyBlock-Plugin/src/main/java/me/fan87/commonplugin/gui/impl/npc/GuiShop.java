package me.fan87.commonplugin.gui.impl.npc;

import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.SneakyThrows;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.SBTrading;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import me.fan87.commonplugin.players.tradings.tradable.impl.CoinTradable;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayOutSetSlot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.lang.reflect.Field;

public class GuiShop extends Gui {
    private final SBPlayer player;
    private ShopItem[] items;
    public GuiShop(SBPlayer player, String name, ShopItem... items) {
        super(name, 6);
        this.player = player;
        this.items = items;
    }

    @Override
    public void init() {
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        for (int i = 0; i < items.length; i++) {
            int x = 2 + i % 7;
            int y = 2 + i / 7;
            set(x, y, items[i].getDisplayItem(player));
        }
        if (player.peekSoldItem() == null || true) {
            set(5, 6, new GuiItem(new ItemStackBuilder(Material.HOPPER)
                    .setDisplayName(ChatColor.GREEN + "Sell Item")
                    .addLore(ChatColor.GRAY + "Click items in your inventory to sell them to this shop!", true)
                    .build()
            ));
        } else {
            set(5, 6, new BuyBackItem(player.peekSoldItem()).getDisplayItem(player));
        }
    }

    @Override
    public boolean canMove(InventoryClickEvent inventoryClickEvent) {
        if (inventoryClickEvent.getRawSlot() >= 54) {
            org.bukkit.inventory.ItemStack currentItem = inventoryClickEvent.getCurrentItem();
            org.bukkit.inventory.ItemStack cloned = currentItem.clone();
            SBItemStack itemStack = new SBItemStack(currentItem);
            if (itemStack.canSellToNpc()) {
                double price = itemStack.getSellPrice();
                if (player.setPurseCoins(player.getPurseCoins() + price)) {
                    inventoryClickEvent.getWhoClicked().sendMessage(ChatColor.GREEN + "You sold " + new SBItemVector(itemStack.getType().getItem(), currentItem.getAmount()) + ChatColor.GREEN + " for " + ChatColor.GOLD + price + " Coin" + (price>1?"s":"") + ChatColor.GREEN + "!");
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 4.048f, 8f);
                    currentItem.setType(Material.AIR);
                    inventoryClickEvent.getClickedInventory().setItem(inventoryClickEvent.getSlot(), currentItem);
//                    player.addSoldItem(new SBItemStack(cloned));
//                    set(5, 6, new BuyBackItem(new SBItemStack(cloned)).getDisplayItem(player));
                    Bukkit.getScheduler().runTask(player.getSkyBlock(), () -> player.getPlayer().updateInventory());
                }
            } else {
                inventoryClickEvent.getWhoClicked().sendMessage(ChatColor.RED + "This item cannot be sold!");
            }
        }
        return false;
    }

    @Override
    public void open(Player player) {
        super.open(player);
        CraftPlayer craftPlayer = (CraftPlayer) player;
    }

    @Override
    protected void onGuiClose(InventoryCloseEvent event) {
        Bukkit.getScheduler().runTaskLater(player.getSkyBlock(), player.getPlayer()::updateInventory, 0);
    }

    @Subscribe(priority = -1)
    @SneakyThrows
    public void onPacket(PacketPlaySendEvent event) {
        Object rawNMSPacket = event.getNMSPacket().getRawNMSPacket();
        Field[] fields = rawNMSPacket.getClass().getDeclaredFields();
        if (event.getPlayer() != player.getPlayer()) return;
        for (Field field : fields) {
            if (field.getType().equals(ItemStack.class)) {
                try {
                    if (player == null) return;
                    field.setAccessible(true);
                    ItemStack item = ((ItemStack) field.get(rawNMSPacket));
                    if (item == null) continue;
                    CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(item);
                    if (craftItemStack.getType() == Material.AIR) continue;
                    SBItemStack itemStack = new SBItemStack(craftItemStack);
                    org.bukkit.inventory.ItemStack displayItemStack = itemStack.getDisplayItemStack();
                    Field b = PacketPlayOutSetSlot.class.getDeclaredField("b");
                    b.setAccessible(true);
                    int slot = (int) b.get(rawNMSPacket);
                    if (itemStack.canSellToNpc() && (!(rawNMSPacket instanceof PacketPlayOutSetSlot) || slot < 54)) {
                        displayItemStack = new ItemStackBuilder(displayItemStack)
                                .setDisplayName(displayItemStack.getItemMeta().getDisplayName())
                                .addLore("")
                                .addLore(ChatColor.GRAY + "Sell Price")
                                .addLore(ChatColor.GOLD.toString() + NumberUtils.formatNumber(itemStack.getSellPrice()) + " Coins")
                                .addLore("")
                                .addLore(ChatColor.YELLOW + "Click to sell!")
                                .build();
                    }
                    field.set(rawNMSPacket, CraftItemStack.asNMSCopy(displayItemStack));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (field.getType().isArray() && field.getType().equals(ItemStack[].class)) {
                try {
                    field.setAccessible(true);
                    ItemStack[] itemStacks = (ItemStack[]) field.get(rawNMSPacket);
                    for (int i = 0; i < itemStacks.length; i++) {
                        if (itemStacks[i] == null) continue;
                        if (i < 54) continue;
                        CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemStacks[i]);
                        if (craftItemStack.getType() == Material.AIR) continue;
                        SBItemStack itemStack = new SBItemStack(craftItemStack);
                        org.bukkit.inventory.ItemStack displayItemStack = itemStack.getDisplayItemStack();
                        if (itemStack.canSellToNpc()) {
                            displayItemStack = new ItemStackBuilder(displayItemStack)
                                    .setDisplayName(displayItemStack.getItemMeta().getDisplayName())
                                    .addLore("")
                                    .addLore(ChatColor.GRAY + "Sell Price")
                                    .addLore(ChatColor.GOLD.toString() + NumberUtils.formatNumber(itemStack.getSellPrice()) + " Coins")
                                    .addLore("")
                                    .addLore(ChatColor.YELLOW + "Click to sell!")
                                    .build();
                        }
                        itemStacks[i] = CraftItemStack.asNMSCopy(displayItemStack);
                    }
                    field.set(rawNMSPacket, itemStacks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class BuyBackItem extends ShopItem {



        public BuyBackItem(SBItemStack itemStack) {
            super(new SBItemVector(itemStack.getType().getItem(), itemStack.getItemStack().getAmount()), new CoinTradable(itemStack.getSellPrice()));
        }


        @Override
        public boolean confirmTrade(SBPlayer player, float multiplier) {
            boolean have = true;
            for (SBTradable sbTradable : getFrom()) {
                if (!sbTradable.doesPlayerHave(player, multiplier)) {
                    have = false;
                    break;
                }
            }
            if (have) {
                if (getTo().giveItem(player)) {
                    for (SBTradable sbTradable : getFrom()) {
                        sbTradable.takeItemFrom(player, multiplier);
                    }
                    player.getPlayer().sendMessage(net.md_5.bungee.api.ChatColor.GREEN + "You bought back " + getTo() + net.md_5.bungee.api.ChatColor.GREEN + "!");
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 4.048f, 8f);
//                    player.pollSoldItem();
                    if (player.peekSoldItem() == null || true) {
                        set(5, 6, new GuiItem(new ItemStackBuilder(Material.HOPPER)
                                .setDisplayName(ChatColor.GREEN + "Sell Item")
                                .addLore(ChatColor.GRAY + "Click items in your inventory to sell them to this shop!", true)
                                .build()
                        ));
                    } else {
//                        set(5, 6, new BuyBackItem(player.peekSoldItem()).getDisplayItem(player));
                    }
                    return true;
                } else {
                    player.getPlayer().sendMessage(net.md_5.bungee.api.ChatColor.RED + "You don't have any inventory space!");
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                    return false;
                }
            } else {
                player.getPlayer().sendMessage(getNotEnoughMessage());
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                return false;
            }
        }

        @Override
        public GuiItem getDisplayItem(SBPlayer player) {
            ItemStackBuilder builder = new ItemStackBuilder(Material.INK_SACK, 8);
            builder.setItemStack(this.getTo().getDisplayItem());
            builder.addLore("");
            builder.addLore(org.bukkit.ChatColor.GRAY + "Cost");
            for (SBTradable sbTradable : this.getFrom()) {
                builder.addLore(sbTradable.toString());
            }
            builder.addLore("");
            builder.addLore(org.bukkit.ChatColor.YELLOW + "Click to trade!");
            builder.addLore(org.bukkit.ChatColor.YELLOW + "Right-Click for more trading options!");
            return new GuiItem(builder.build(), event -> {
                this.confirmTrade(player, 1f);
            });
        }
    }

    public static class ShopItem extends SBTrading {

        public ShopItem(SBItemVector to, SBTradable... from) {
            super(null, from, to, false);
        }

        @Override
        protected boolean isUnlocked(SBPlayer player) {
            return true;
        }
    }

}
