package me.fan87.commonplugin.players.tradings;

import lombok.Getter;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardTrading;
import me.fan87.commonplugin.players.tradings.tradable.SBTradable;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.SBNamespace;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

@Getter
public class SBTrading {

    private final SBNamespace namespace;
    private final SBTradable[] from;
    private final SBItemVector to;
    private final boolean unlockedByDefault;

    public SBTrading(SBNamespace namespace, SBTradable[] from, SBItemVector to, boolean unlockedByDefault) {
        this.namespace = namespace;
        this.from = from;
        this.to = to;
        this.unlockedByDefault = unlockedByDefault;
    }

    public SBCollection.ObtainMethod getRelatedCollection(SBPlayer player) {
        for (SBCollection collection : player.getCollections().getCollections()) {
            for (int level = 1; level < collection.getMaxLevel() + 1; level++) {
                for (SBReward reward : collection.getRewards(level)) {
                    if (reward instanceof RewardTrading) {
                        return new SBCollection.ObtainMethod(level, collection);
                    }
                }
            }
        }
        return null;
    }

    protected boolean isUnlocked(SBPlayer player) {
        return player.isTradingUnlocked(this);
    }

    public String getNotEnoughMessage() {
        for (SBTradable sbTradable : from) {
            return sbTradable.getNotEnoughMessage();
        }
        return ChatColor.RED + "You can't buy this item right now! Please try again later!";
    }

    public boolean silentConfirmTrade(SBPlayer player) {
        if (to.doesPlayerHave(player)) {
            to.takeItemFrom(player);
            return to.giveItem(player);
        } else {
            return false;
        }
    }

    public boolean confirmTrade(SBPlayer player, float multiplier) {
        boolean have = true;
        for (SBTradable sbTradable : from) {
            if (!sbTradable.doesPlayerHave(player, multiplier)) {
                have = false;
                break;
            }
        }
        if (have) {
            if (to.giveItem(player)) {
                for (SBTradable sbTradable : from) {
                    sbTradable.takeItemFrom(player, multiplier);
                }
                player.getPlayer().sendMessage(ChatColor.GREEN + "You bought " + to + ChatColor.GREEN + "!");
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.NOTE_PLING, 4.048f, 8f);
                return true;
            } else {
                player.getPlayer().sendMessage(ChatColor.RED + "You don't have any inventory space!");
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                return false;
            }
        } else {
            player.getPlayer().sendMessage(getNotEnoughMessage());
            player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
            return false;
        }
    }

    public GuiItem getDisplayItem(SBPlayer player) {
        ItemStackBuilder builder = new ItemStackBuilder(Material.INK_SACK, 8)
                .setDisplayName(org.bukkit.ChatColor.RED + "???")
                .addLore(org.bukkit.ChatColor.GRAY + "Progress through your item collections and explore the world to unlock new trades!", true);
        if (isUnlocked(player)) {
            builder.setItemStack(this.getTo().getDisplayItem());
            builder.addLore("");
            builder.addLore(org.bukkit.ChatColor.GRAY + "Cost");
            for (SBTradable sbTradable : this.getFrom()) {
                builder.addLore(sbTradable.toString());
            }
            builder.addLore("");
            builder.addLore(org.bukkit.ChatColor.YELLOW + "Click to trade!");
            builder.addLore(org.bukkit.ChatColor.YELLOW + "Right-Click for more trading options!");
        }
        return new GuiItem(builder.build(), event -> {
            if (isUnlocked(player)) {
                this.confirmTrade(player, 1f);
            } else {
                player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.VILLAGER_NO, 1f, 1f);
                player.getPlayer().sendMessage(org.bukkit.ChatColor.RED + "This item is locked!");
            }
        });
    }


}
