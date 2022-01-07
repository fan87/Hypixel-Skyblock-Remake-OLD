# Collections Generator by fan87


import requests
import json
from difflib import SequenceMatcher
import datetime
import re
import os

sample = """
//////////////////////////////////////////////////////////////////////////
//                                                                      //
// Automatically generated with Hypixel SkyBlock API                    //
// Generator made by fan87                                              //
//                                                                      //
// GitHub Repository: https://github.com/fan87/Hypixel-Skyblock-Remake  //
//                                                                      //
//////////////////////////////////////////////////////////////////////////
package me.fan87.commonplugin.players.collections.impl.%CATEGORY_LOWER%;

import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.players.collections.SBCollection;
import me.fan87.commonplugin.players.reward.SBReward;

public class Collection%CLASS_NAME% extends SBCollection {
    public Collection%CLASS_NAME%() {
        super(SBItems.%ITEM%, new CollectionPattern(%TIER_REQUIREMENTS%), %MAX_TIER%, CollectionType.%CATEGORY%);
    }

    @Override
    protected SBReward[] getLevelReward(int level) {
        return new SBReward[0];
    }
}
"""

data = json.loads(requests.get("https://api.hypixel.net/resources/skyblock/collections").text)
os.system("rm -r collections")
try:
    os.makedirs("collections")
except:
    pass

def format_class_name(input: str) -> str:
    ro = ""
    for s in input.split("_"):
        if len(s) < 2:
            ro += s
            continue
        ro += s[0].upper() + s[1:].lower()
    return ro

for category in data["collections"]:
    collections = data["collections"][category]["items"]
    for item in collections:
        collection = collections[item]
        item = item.replace(":", "__")
        requires = ""
        for tier in collection["tiers"]:
            requires += str(tier["amountRequired"]) + ", "
        requires = requires[0:-2]
        try:
            os.makedirs("collections/" + category.lower())
        except:
            pass
        open("collections/" + category.lower() + "/Collection" + format_class_name(item) + ".java", "w").write(sample.replace("%CATEGORY_LOWER%", category.lower())
                    .replace("%CATEGORY%", category)
                    .replace("%MAX_TIER%", str(collection["maxTiers"]))
                    .replace("%TIER_REQUIREMENTS%", requires)
                    .replace("%ITEM%", item)
                    .replace("%CLASS_NAME%", format_class_name(item))
                    )
        print(f'@JsonProperty(\"{item.replace("__", ":")}\")')
        print("public Collection" + format_class_name((item)) + " " + item + " = new Collection" + format_class_name(item) + "();")