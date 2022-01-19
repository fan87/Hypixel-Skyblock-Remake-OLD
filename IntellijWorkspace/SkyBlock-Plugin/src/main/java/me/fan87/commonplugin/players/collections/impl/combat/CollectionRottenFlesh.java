
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.combat;

import me.fan87.commonplugin.item.init.ItemsVanilla;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionRottenFlesh extends SBCollection {
    public CollectionRottenFlesh() {
        super(ItemsVanilla.ROTTEN_FLESH, new CollectionPattern(50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000, 100000), 10, CollectionType.COMBAT);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
