package me.fan87.commonplugin.item.init;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.impl.talismans.ItemZombieTalisman;

import java.lang.reflect.Field;

public class ItemsAcessory {

    public final static ItemZombieTalisman ZOMBIE_TALISMAN = new ItemZombieTalisman(SkyBlock.getPlugin(SkyBlock.class));

    public ItemsAcessory(SkyBlock skyBlock) {
        for (Field declaredField : getClass().getDeclaredFields()) {
            try {
                SBItems.registerItem((SBCustomItem) declaredField.get(null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
