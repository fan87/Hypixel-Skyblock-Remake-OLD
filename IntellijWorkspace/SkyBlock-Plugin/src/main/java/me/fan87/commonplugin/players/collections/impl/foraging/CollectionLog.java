
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.foraging;

import me.fan87.commonplugin.item.init.ItemsVanilla;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionLog extends SBCollection {
    public CollectionLog() {
        super(ItemsVanilla.LOG, new CollectionPattern(50, 100, 250, 500, 1000, 2000, 5000, 10000, 30000), 9, CollectionType.FORAGING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
