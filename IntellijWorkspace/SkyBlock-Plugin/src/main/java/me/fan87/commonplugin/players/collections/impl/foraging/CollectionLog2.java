
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.foraging;

import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionLog2 extends SBCollection {
    public CollectionLog2() {
        super(SBItems.LOG__2, new CollectionPattern(50, 100, 250, 500, 1000, 2000, 5000, 10000, 25000, 50000), 10, CollectionType.FORAGING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
