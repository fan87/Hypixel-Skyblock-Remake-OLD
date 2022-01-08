
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
import me.fan87.commonplugin.item.init.ItemsVANILLA;

public class CollectionFeather extends SBCollection {
    public CollectionFeather() {
        super(ItemsVANILLA.FEATHER, new CollectionPattern(50, 100, 250, 1000, 2500, 5000, 10000, 25000, 50000), 9, CollectionType.FARMING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
