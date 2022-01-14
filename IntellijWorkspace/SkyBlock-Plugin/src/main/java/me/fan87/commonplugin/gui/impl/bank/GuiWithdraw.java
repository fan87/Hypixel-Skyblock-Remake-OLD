package me.fan87.commonplugin.gui.impl.bank;

import me.fan87.commonplugin.features.impl.api.SignInputAPI;
import me.fan87.commonplugin.gui.ButtonHandler;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

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
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                player.getBankAccount().withdraw(player, player.getBankAccount().getBankCoins());
            }
        }));
        set(4, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setAmount(32)
                .setDisplayName(ChatColor.GREEN + "Half the account")
                .addLore(ChatColor.DARK_GRAY + "Bank withdrawal")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()/2d))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                player.getBankAccount().withdraw(player, player.getBankAccount().getBankCoins()/2.0);
            }
        }));
        set(6, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Withdraw 20%")
                .addLore(ChatColor.DARK_GRAY + "Bank withdrawal")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore(ChatColor.GRAY + "Amount to withdraw: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()*0.2))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                player.getBankAccount().withdraw(player, player.getBankAccount().getBankCoins()*0.2d);
            }
        }));
        set(8, 2, new GuiItem(new ItemStackBuilder(Material.SIGN)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Specific amount")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), event -> {
            SignInputAPI.showSignEditor(player, content -> {
                try {
                    int v = Integer.parseInt(content[0]);
                    if (v < 0) throw new IllegalStateException("Fuck off");
                    player.getBankAccount().withdraw(player, v);
                } catch (Exception e) {
                    player.getPlayer().sendMessage(ChatColor.RED + "Please input a valid number!");
                }
            }, "", "^^^^^^^^^^^^^^^", "Enter the amount", "to withdraw!");
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
