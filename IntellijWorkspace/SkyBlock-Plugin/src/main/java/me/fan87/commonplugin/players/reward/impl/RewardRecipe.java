package me.fan87.commonplugin.players.reward.impl;

import lombok.Getter;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;
import org.bukkit.ChatColor;

public class RewardRecipe extends SBReward {

    @Getter
    private SBCustomItem item;

    /**
     * For SkyBlock API and Generation only!
     * @param itemName Item name instead of namespace
     */
    @Deprecated
    public RewardRecipe(String itemName) {
        this.item = SBItems.getItemByName(itemName);
    }

    public RewardRecipe(SBCustomItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item.getRarity().getColor() + item.getDisplayName() + ChatColor.GRAY + " Recipe";
    }

    @Override
    public void trigger(SBPlayer player) {
        player.unlockRecipe(item);
    }
}
