package me.fan87.commonplugin.item;

import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.item.impl.ItemSkyBlockMenu;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import me.fan87.commonplugin.item.init.*;
import me.fan87.commonplugin.players.SBPlayer;
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

    private final SkyBlock skyBlock;

    public SBItems(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;

        SKYBLOCK_MENU = new ItemSkyBlockMenu(skyBlock);
        registerItem(SKYBLOCK_MENU);

        new ItemsMATERIAL_A(skyBlock);
        new ItemsMATERIAL_C(skyBlock);
        new ItemsMATERIAL_B(skyBlock);
        new ItemsBOW(skyBlock);
        new ItemsBOOTS(skyBlock);
        new ItemsSPADE(skyBlock);
        new ItemsPICKAXE(skyBlock);
        new ItemsAXE(skyBlock);
        new ItemsSWORD(skyBlock);
        new ItemsHELMET(skyBlock);
        new ItemsLEGGINGS(skyBlock);
        new ItemsSHEARS(skyBlock);
        new ItemsCHESTPLATE(skyBlock);
        new ItemsHOE(skyBlock);
        new ItemsFISHING_ROD(skyBlock);
        new ItemsARROW(skyBlock);
        new ItemsPET_ITEM(skyBlock);
        new ItemsREFORGE_STONE(skyBlock);
        new ItemsCOSMETIC(skyBlock);
        new ItemsACCESSORY(skyBlock);
        new ItemsTRAVEL_SCROLL(skyBlock);
        new ItemsBAIT(skyBlock);
        new ItemsDUNGEON_PASS(skyBlock);
        new ItemsARROW_POISON(skyBlock);
        new ItemsWAND(skyBlock);
        new ItemsDRILL(skyBlock);
        new ItemsFISHING_WEAPON(skyBlock);
        new ItemsGAUNTLET(skyBlock);
    }
    
    public static ItemVanilla getVanillaItem(Material material, short damage) {
        for (SBCustomItem item : registeredItems.values()) {
            if (item instanceof ItemVanilla && item.getMaterial() == material && item.getDamage() == damage) {
                return (ItemVanilla) item;
            }
        }
        return null;
    }


    public static void registerItem(SBCustomItem item) {
        if (getItem(item.getNamespace()) == null) {
            registeredItems.put(item.getNamespace(), item);
            EventManager.EVENT_BUS.register(item);
        } else {
            throw new IllegalArgumentException("Name already taken: " + item.getNamespace());
        }
    }

    public static SBCustomItem getItem(String namespace) {
        if (!registeredItems.containsKey(namespace)) return null;
        return registeredItems.get(namespace);
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
                    SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
                    player.updateInventory();
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
