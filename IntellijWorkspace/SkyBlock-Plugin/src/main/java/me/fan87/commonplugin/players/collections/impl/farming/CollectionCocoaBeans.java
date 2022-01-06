package me.fan87.commonplugin.players.collections.impl.farming;

import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;
import org.bukkit.Material;

public class CollectionCocoaBeans extends SBCollection {
    public CollectionCocoaBeans() {
        super(Material.COCOA, CollectionPattern.CARROT, 9, CollectionType.FARMING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
