
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.farming;

import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionWheat extends SBCollection {
    public CollectionWheat() {
        super(ItemsVANILLA.WHEAT, new CollectionPattern(50, 100, 250, 500, 1000, 2500, 10000, 15000, 25000, 50000, 100000), 11, CollectionType.FARMING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
