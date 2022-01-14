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
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiBank extends Gui {
    private SBPlayer player;
    public GuiBank(SBPlayer player) {
        super("Bank", 4);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(3, 2, new GuiItem(new ItemStackBuilder(Material.CHEST)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Deposit Coins")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()), true)
                .addLore("")
                .addLore(ChatColor.GRAY + "Store coins in the bank to keep them safe while you go on adventures!", true)
                .addLore("")
                .addLore(ChatColor.GRAY + "You will earn " + ChatColor.AQUA + "2% " + ChatColor.GRAY + "interest every season for your first " + ChatColor.GOLD + "2 million " + ChatColor.GRAY + "banked coins.", true)
                .addLore("")
                .addLore(ChatColor.GRAY + "Until interest: " + ChatColor.AQUA + "21231817 years (Never, since I'm lazy to make it lol)", true)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to make a deposit!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                new GuiDeposit(player).open(player.getPlayer());
            }
        }));
        set(5, 2, new GuiItem(new ItemStackBuilder(Material.DISPENSER)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Withdraw Coins")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + NumberUtils.formatNumber(player.getBankAccount().getBankCoins()), true)
                .addLore("")
                .addLore(ChatColor.GRAY + "Take your coins out of the bank in order to spend them.", true)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to withdraw coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                new GuiWithdraw(player).open(player.getPlayer());
            }
        }));
    }
}
