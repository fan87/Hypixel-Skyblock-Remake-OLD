
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.fishing;

import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionRawFish extends SBCollection {
    public CollectionRawFish() {
        super(ItemsVANILLA.RAW_FISH, new CollectionPattern(20, 50, 100, 250, 500, 1000, 2500, 15000, 30000, 45000, 60000), 11, CollectionType.FISHING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
