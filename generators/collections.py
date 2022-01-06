# Collections Generator by fan87
# You need Items Generator in order to get this working


import requests
import json

content = json.loads(requests.get("https://api.hypixel.net/resources/skyblock/collections").text)


sample = """

package me.fan87.commonplugin.players.collections.impl.%PACKAGE_NAME%;

import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;
import org.bukkit.Material;

public class CollectionCarrot extends SBCollection {
    public CollectionCarrot() {
        super(Material.%MATERIAL%, CollectionPattern.CARROT, 9, CollectionType.FARMING);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}

"""