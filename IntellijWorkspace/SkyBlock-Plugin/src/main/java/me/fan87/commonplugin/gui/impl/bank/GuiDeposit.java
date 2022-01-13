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

public class GuiDeposit extends Gui {
    private SBPlayer player;
    public GuiDeposit(SBPlayer player) {
        super("Bank Deposit", 4);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(3, 2, new GuiItem(new ItemStackBuilder(Material.CHEST)
                .addAllItemFlags()
                .setAmount(64)
                .setDisplayName(ChatColor.GREEN + "Your whole purse")
                .addLore(ChatColor.DARK_GRAY + "Bank deposit")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankCoins())
                .addLore(ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + player.getCoins())
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                if (player.getCoins() <= 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You can't deposit this little!");
                } else {
                    player.setBankCoins(player.getBankCoins() + player.getCoins());
                    player.getPlayer().sendMessage(ChatColor.GREEN + "You have deposit " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getCoins(), false) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins(), false) + " coins " + ChatColor.GREEN + "in your account!");
                    player.setCoins(0);
                    new GuiBank(player).open(player .getPlayer());
                }
            }
        }));
        set(6, 2, new GuiItem(new ItemStackBuilder(Material.CHEST)
                .addAllItemFlags()
                .setAmount(32)
                .setDisplayName(ChatColor.GREEN + "Half your purse")
                .addLore(ChatColor.DARK_GRAY + "Bank deposit")
                .addLore("")
                .addLore(ChatColor.GRAY + "Current balance: " + ChatColor.GOLD + player.getBankCoins())
                .addLore(ChatColor.GRAY + "Amount to deposit: " + ChatColor.GOLD + player.getCoins()/2)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to deposit coins!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                if (player.getCoins() <= 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You can't deposit this little!");
                } else {
                    player.setBankCoins(player.getBankCoins() + player.getCoins()/2);
                    player.getPlayer().sendMessage(ChatColor.GREEN + "You have deposit " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getCoins(), false) + " coins" + ChatColor.GREEN + "! You now have " + ChatColor.GOLD + NumberUtils.formatLargeNumber(player.getBankCoins(), false) + " coins " + ChatColor.GREEN + "in your account!");
                    player.setCoins(player.getCoins()/2);
                    new GuiBank(player).open(player .getPlayer());
                }
            }
        }));
    }
}
