package me.fan87.commonplugin.item;

import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.item.impl.ItemSkyBlockMenu;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayOutWindowItems;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
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
    public void updateInventories(PacketPlaySendEvent event) {
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayOutWindowItems) {
            skyBlock.getPlayersManager().getPlayer(event.getPlayer()).updateInventory();
            PacketPlayOutWindowItems packet = ((PacketPlayOutWindowItems) event.getNMSPacket().getRawNMSPacket());
            try {
                Field field = PacketPlayOutWindowItems.class.getDeclaredField("b");
                field.setAccessible(true);
                ItemStack[] itemStacks = (ItemStack[]) field.get(packet);
                for (int i = 0; i < itemStacks.length; i++) {
                    CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemStacks[i]);
                    if (craftItemStack == null || craftItemStack.getType() == Material.AIR) continue;
                    SBItemStack itemStack = new SBItemStack(craftItemStack);
                    itemStacks[i] = CraftItemStack.asNMSCopy(itemStack.getDisplayItemStack());
                }
                field.set(packet, itemStacks);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
