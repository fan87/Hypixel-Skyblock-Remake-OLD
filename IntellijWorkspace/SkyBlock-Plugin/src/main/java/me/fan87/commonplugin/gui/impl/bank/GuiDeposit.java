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

public class GuiDeposit extends Gui {
    private SBPlayer player;
    public GuiDeposit(SBPlayer player) {
        super("Bank Deposit", 4);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(2, 2, new GuiItem(new ItemStackBuilder(Material.CHEST)
                .addAllItemFlags()
                .setAmount(64)
                .setDisplayName(ChatColor.GREEN + "Your whole purse")
                .addLore(ChatColor.DARK_GRAY + "Bank deposit")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore(ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getPurseCoins()))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                player.getBankAccount().deposit(player, player.getPurseCoins());
            }
        }));
        set(5, 2, new GuiItem(new ItemStackBuilder(Material.CHEST)
                .addAllItemFlags()
                .setAmount(32)
                .setDisplayName(ChatColor.GREEN + "Half your purse")
                .addLore(ChatColor.DARK_GRAY + "Bank deposit")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore(ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getPurseCoins()/2))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                player.getBankAccount().deposit(player, player.getPurseCoins()/2d);
            }
        }));
        set(8, 2, new GuiItem(new ItemStackBuilder(Material.SIGN)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Specific amount")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()))
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), event -> {
            SignInputAPI.showSignEditor(player, content -> {
                try {
                    int v = Integer.parseInt(content[0]);
                    if (v < 0) throw new IllegalStateException("Fuck off");
                    player.getBankAccount().deposit(player, v);
                } catch (Exception e) {
                    player.getPlayer().sendMessage(ChatColor.RED + "Please input a valid number!");
                }
            }, "", "^^^^^^^^^^^^^^^", "Enter the amount", "to deposit");
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
