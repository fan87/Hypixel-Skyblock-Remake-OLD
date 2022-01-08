package me.fan87.commonplugin.recipes;

import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;
import me.fan87.commonplugin.players.reward.impl.RewardRecipe;
import org.bukkit.inventory.ItemStack;

public abstract class SBRecipe {

    public abstract boolean match(ItemStack[] items, int width, int height);
    public abstract ItemStack getOutput();
    public abstract RecipeType getType();
    public abstract boolean action(ItemStack[] items, int width, int height);
    public abstract SBCustomItem getOutputType();
    public abstract boolean isUnlockable();
    public SBCollection relatedCollection(SBPlayer player) {
        for (SBCollection collection : player.getCollections().getCollections()) {
            for (SBReward[] value : collection.getRewardsMap().values()) {
                for (SBReward sbReward : value) {
                    if (sbReward instanceof RewardRecipe) {
                        if (((RewardRecipe) sbReward).getItem() == getOutputType()) return collection;
                    }
                }
            }
        }
        return null;
    }

    public enum RecipeType {
        CRAFTING_TABLE;
    }

}
