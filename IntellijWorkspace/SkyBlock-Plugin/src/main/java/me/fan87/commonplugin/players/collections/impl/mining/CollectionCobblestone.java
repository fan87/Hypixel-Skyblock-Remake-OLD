
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.mining;

import me.fan87.commonplugin.item.init.ItemsVanilla;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class CollectionCobblestone extends SBCollection {
    public CollectionCobblestone() {
        super(ItemsVanilla.COBBLESTONE, new CollectionPattern(50, 100, 250, 1000, 2500, 5000, 10000, 25000, 40000, 70000), 10, CollectionType.MINING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
