package me.fan87.commonplugin.players.reward.impl;

import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.reward.SBReward;

public class RewardRecipe extends SBReward {

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
        return null;
    }

    @Override
    public void trigger(SBPlayer player) {

    }
}
