package me.fan87.commonplugin.item;

import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.item.impl.ItemSkyBlockMenu;
import net.minecraft.server.v1_8_R3.ItemStack;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SBItems {

    @Getter
    private static final Map<String, SBCustomItem> registeredItems = new HashMap<>();


    public static SBCustomItem SKYBLOCK_MENU;



    public static void registerItem(SBCustomItem item) {
        if (getItem(item.getNamespace()) == null) {
            registeredItems.put(item.getNamespace(), item);
            EventManager.EVENT_BUS.register(item);
        } else {
            throw new IllegalArgumentException("Name already taken!");
        }
    }

    public static SBCustomItem getItem(String namespace) {
        if (!registeredItems.containsKey(namespace)) return null;
        return registeredItems.get(namespace);
    }

    private final SkyBlock skyBlock;

    public SBItems(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;


        SKYBLOCK_MENU = new ItemSkyBlockMenu(skyBlock);
        registerItem(SKYBLOCK_MENU);
    }

    @Subscribe
    public void updateItemDrop(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.DROPPED_ITEM) {
            Item entity = (Item) event.getEntity();
            entity.setItemStack(new SBItemStack(entity.getItemStack()).getItemStack());
        }
    }

    @Subscribe
    public void updateInventories(PacketPlaySendEvent event) {
        Object rawNMSPacket = event.getNMSPacket().getRawNMSPacket();
        Field[] fields = rawNMSPacket.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(ItemStack.class)) {
                try {
                    skyBlock.getPlayersManager().getPlayer(event.getPlayer()).updateInventory();
                    field.setAccessible(true);
                    ItemStack item = ((ItemStack) field.get(rawNMSPacket));
                    if (item == null) continue;
                    CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(item);
                    if (craftItemStack.getType() == Material.AIR) continue;
                    SBItemStack itemStack = new SBItemStack(craftItemStack);
                    field.set(rawNMSPacket, CraftItemStack.asNMSCopy(itemStack.getDisplayItemStack()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (field.getType().isArray() && field.getType().equals(ItemStack[].class)) {
                try {
                    skyBlock.getPlayersManager().getPlayer(event.getPlayer()).updateInventory();
                    field.setAccessible(true);
                    ItemStack[] itemStacks = (ItemStack[]) field.get(rawNMSPacket);
                    for (int i = 0; i < itemStacks.length; i++) {
                        if (itemStacks[i] == null) continue;
                        CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemStacks[i]);
                        if (craftItemStack.getType() == Material.AIR) continue;
                        SBItemStack itemStack = new SBItemStack(craftItemStack);
                        itemStacks[i] = CraftItemStack.asNMSCopy(itemStack.getDisplayItemStack());
                    }
                    field.set(rawNMSPacket, itemStacks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
