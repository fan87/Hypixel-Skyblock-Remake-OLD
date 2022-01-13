package me.fan87.commonplugin.gui.impl.bank;

import me.fan87.commonplugin.gui.ButtonHandler;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiWithdraw extends Gui {
    private SBPlayer player;
    public GuiWithdraw(SBPlayer player) {
        super("Bank Withdrawal", 4);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(2, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setAmount(64)
                .setDisplayName(ChatColor.GREEN + "Everything in your account")
                .addLore(ChatColor.DARK_GRAY + "Bank withdrawal")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankCoins())
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + player.getBankCoins())
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                if (player.getBankCoins() <= 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You don't have any Coins in your bank account!");
                } else {
                    player.setCoins(player.getCoins() + player.getBankCoins());
                    player.getPlayer().sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins(), false) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins(), false) + " coins " + ChatColor.GREEN + "in your account!");
                    player.setBankCoins(0);
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 8.0f, 4.0f);
                    new GuiBank(player).open(player .getPlayer());
                }
            }
        }));
        set(4, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setAmount(32)
                .setDisplayName(ChatColor.GREEN + "Half the account")
                .addLore(ChatColor.DARK_GRAY + "Bank withdrawal")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankCoins())
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + player.getBankCoins()/2)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                if (player.getBankCoins() <= 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You don't have any Coins in your bank account!");
                } else {
                    player.setCoins(player.getCoins() + player.getBankCoins()/2);
                    player.getPlayer().sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins()/2, false) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins()/2, false) + " coins " + ChatColor.GREEN + "in your account!");
                    player.setBankCoins(player.getBankCoins()/2);
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 8.0f, 4.0f);
                    new GuiBank(player).open(player .getPlayer());
                }
            }
        }));
        set(6, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Withdraw 20%")
                .addLore(ChatColor.DARK_GRAY + "Bank withdrawal")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankCoins())
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + player.getBankCoins()*0.2)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                if (player.getBankCoins() <= 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You don't have any Coins in your bank account!");
                } else {
                    player.setCoins(player.getCoins() + player.getBankCoins()*0.2d);
                    player.getPlayer().sendMessage(ChatColor.GREEN + "You have withdrawn " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins()*0.2d, false) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins()*0.8d, false) + " coins " + ChatColor.GREEN + "in your account!");
                    player.setBankCoins(player.getBankCoins()*0.8d);
                    player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 8.0f, 4.0f);
                    new GuiBank(player).open(player .getPlayer());
                }
            }
        }));
        set(5, 4, new GuiItem(new ItemStackBuilder(Material.ARROW)
                .setDisplayName(ChatColor.GREEN + "Go Back")
                .addLore(ChatColor.GRAY + "To Bank Account")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                new GuiBank(player).open(player.getPlayer());
            }
        }));
    }
}
