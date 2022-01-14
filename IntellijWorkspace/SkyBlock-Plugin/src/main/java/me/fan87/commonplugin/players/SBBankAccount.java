package me.fan87.commonplugin.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;

public class SBBankAccount {

    @Getter
    @JsonProperty("coins")
    private int bankCoins;

    public void withdraw(SBPlayer player, double coins) {
        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Withdrawing coins...");
        double withdrawCoins = Math.min(coins, bankCoins);
        if (withdrawCoins < 1) {
            player.getPlayer().sendMessage(ChatColor.RED + "You cannot withdraw this little coins!");
            return;
        }
        bankCoins -= withdrawCoins;
        player.purseCoins += withdrawCoins;
        player.getPlayer().sendMessage(ChatColor.GREEN + String.format("Withdrew %s%s coin%s%s! There's now %s%s coin%s%s left in the account!",
                ChatColor.GOLD,
                NumberUtils.formatLargeNumber(withdrawCoins, false),
                withdrawCoins>1?"s":"",
                ChatColor.GREEN,
                ChatColor.GOLD,
                NumberUtils.formatLargeNumber(bankCoins, false),
                bankCoins>1?"s":"",
                ChatColor.GREEN
        ));
        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 8.0f, 4.0f);
    }

    public boolean silentWithdraw(SBPlayer player, double coins) {
        double withdrawCoins = Math.min(coins, bankCoins);
        if (withdrawCoins < 1) {
            return false;
        }
        bankCoins -= withdrawCoins;
        player.purseCoins += withdrawCoins;
        return true;
    }

    public void deposit(SBPlayer player, double coins) {
        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "Depositing coins...");
        double depositCoins = Math.min(coins,player. purseCoins);
        if (depositCoins < 1) {
            player.getPlayer().sendMessage(ChatColor.RED + "You can't deposit this little!");
            return;
        }
        bankCoins += depositCoins;
        player.purseCoins -= depositCoins;
        player.getPlayer().sendMessage(ChatColor.GREEN + String.format("Deposited %s%s coin%s%s! There's now %s%s coin%s%s in the account!",
                ChatColor.GOLD,
                NumberUtils.formatLargeNumber(depositCoins, false),
                depositCoins>1?"s":"",
                ChatColor.GREEN,
                ChatColor.GOLD,
                NumberUtils.formatLargeNumber(bankCoins, false),
                bankCoins>1?"s":"",
                ChatColor.GREEN
        ));
        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 8.0f, 4.0f);
    }

    public boolean silentDeposit(SBPlayer player, double coins) {
        double depositCoins = Math.min(coins, player.purseCoins);
        if (depositCoins < 1) {
            return false;
        }
        bankCoins += depositCoins;
        player.purseCoins -= depositCoins;
        return true;
    }

}
