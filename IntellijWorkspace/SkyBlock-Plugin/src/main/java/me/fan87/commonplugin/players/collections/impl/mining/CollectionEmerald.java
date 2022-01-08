
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.mining;

import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionEmerald extends SBCollection {
    public CollectionEmerald() {
        super(ItemsVANILLA.EMERALD, new CollectionPattern(50, 100, 250, 1000, 5000, 15000, 30000, 50000, 100000), 9, CollectionType.MINING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
