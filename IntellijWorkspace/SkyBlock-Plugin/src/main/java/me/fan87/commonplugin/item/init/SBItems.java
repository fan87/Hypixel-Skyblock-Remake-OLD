package me.fan87.commonplugin.item.init;

import de.tr7zw.changeme.nbtapi.NBTItem;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketPlaySendEvent;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.item.impl.ItemSkyBlockMenu;
import me.fan87.commonplugin.item.impl.ItemVanilla;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.SBNamespace;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.PacketPlayInHeldItemSlot;
import org.bukkit.ChatColor;
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
    private static final Map<SBNamespace, SBCustomItem> registeredItems = new HashMap<>();

    static {
        new ItemsVANILLA(SkyBlock.getPlugin(SkyBlock.class));
    }

    public static SBCustomItem SKYBLOCK_MENU;


    private final SkyBlock skyBlock;

    public SBItems(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;

        SKYBLOCK_MENU = new ItemSkyBlockMenu(skyBlock);
        registerItem(SKYBLOCK_MENU);


    }
    
    public static ItemVanilla getVanillaItem(Material material, short damage) {
        for (SBCustomItem item : registeredItems.values()) {
            if (item instanceof ItemVanilla && item.getMaterial() == material && item.getDurability() == damage) {
                return (ItemVanilla) item;
            }
        }
        for (SBCustomItem item : registeredItems.values()) {
            if (item instanceof ItemVanilla && item.getMaterial() == material) {
                return (ItemVanilla) item;
            }
        }
        return null;
    }

    public static SBCustomItem getItemByName(String name) {
        for (SBCustomItem value : registeredItems.values()) {
            if (value.getDisplayName().equals(name)) return value;
        }
        return null;
    }

    protected static void registerItem(SBCustomItem item) {
        if (getItem(item.getNamespace()) == null) {
            registeredItems.put(new SBNamespace("default", item.getNamespace()), item);
            EventManager.EVENT_BUS.register(item);
        } else {
            throw new IllegalArgumentException("Name already taken: " + item.getNamespace());
        }
    }

    public static void registerItem(String addonNamespace, SBCustomItem item) {
        if (getItem(item.getNamespace()) == null) {
            registeredItems.put(new SBNamespace(addonNamespace, item.getNamespace()), item);
            EventManager.EVENT_BUS.register(item);
        } else {
            throw new IllegalArgumentException("Name already taken: " + item.getNamespace());
        }
    }

    public static SBCustomItem getItem(String namespace) {
        SBNamespace sbNamespace = new SBNamespace("default", namespace);
        if (namespace.contains("::")) {
            sbNamespace.setNamespace(namespace.split("::")[1]);
            sbNamespace.setAddonName(namespace.split("::")[0]);
        }
        if (!registeredItems.containsKey(sbNamespace)) {
            return null;
        }
        return registeredItems.get(sbNamespace);
    }

    public static SBNamespace getNamespaceOf(SBCustomItem item) {
        for (SBNamespace sbNamespace : registeredItems.keySet()) {
            if (registeredItems.get(sbNamespace) == item) {
                return sbNamespace;
            }
        }
        return new SBNamespace("unknown", item.getNamespace());
    }


    public static SBCustomItem getItem(SBNamespace namespace) {
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
    public void onReceive(PacketPlayReceiveEvent event) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (player == null) return;
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayInHeldItemSlot) {
            player.updateInventory();
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
                    if (player == null) return;
                    player.updateInventory();
                    field.setAccessible(true);
                    ItemStack item = ((ItemStack) field.get(rawNMSPacket));
                    if (item == null) continue;
                    CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(item);
                    if (craftItemStack.getType() == Material.AIR) continue;
                    SBItemStack itemStack = new SBItemStack(craftItemStack);
                    org.bukkit.inventory.ItemStack displayItemStack = itemStack.getDisplayItemStack();
                    if (player.isDebugging() && itemStack.getType().getType() == SBMaterial.ItemType.CUSTOM) {
                        NBTItem nbt = new NBTItem(displayItemStack, true);
                        displayItemStack = new ItemStackBuilder(displayItemStack)
                                .setDisplayName(displayItemStack.getItemMeta().getDisplayName() + ChatColor.RESET + String.format(" (#%04d/%d)", displayItemStack.getType().getId(), displayItemStack.getDurability()))
                                .addLore(ChatColor.DARK_GRAY.toString() + SBItems.getNamespaceOf(itemStack.getType().getItem()))
                                .addLore(ChatColor.DARK_GRAY + "NBT: " + nbt.getKeys().size() + " tag(s)")
                                .build();
                    }
                    field.set(rawNMSPacket, CraftItemStack.asNMSCopy(displayItemStack));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (field.getType().isArray() && field.getType().equals(ItemStack[].class)) {
                try {
                    SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
                    player.updateInventory();
                    field.setAccessible(true);
                    ItemStack[] itemStacks = (ItemStack[]) field.get(rawNMSPacket);
                    for (int i = 0; i < itemStacks.length; i++) {
                        if (itemStacks[i] == null) continue;
                        CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(itemStacks[i]);
                        if (craftItemStack.getType() == Material.AIR) continue;
                        SBItemStack itemStack = new SBItemStack(craftItemStack);
                        org.bukkit.inventory.ItemStack displayItemStack = itemStack.getDisplayItemStack();
                        if (player.isDebugging() && itemStack.getType().getType() == SBMaterial.ItemType.CUSTOM) {
                            NBTItem nbt = new NBTItem(displayItemStack, true);
                            displayItemStack = new ItemStackBuilder(displayItemStack)
                                    .setDisplayName(displayItemStack.getItemMeta().getDisplayName() + ChatColor.RESET + String.format(" (#%04d/%d)", displayItemStack.getType().getId(), displayItemStack.getDurability()))
                                    .addLore(ChatColor.DARK_GRAY.toString() + SBItems.getNamespaceOf(itemStack.getType().getItem()))
                                    .addLore(ChatColor.DARK_GRAY + "NBT: " + nbt.getKeys().size() + " tag(s)")
                                    .build();
                        }
                        itemStacks[i] = CraftItemStack.asNMSCopy(displayItemStack);
                    }
                    field.set(rawNMSPacket, itemStacks);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
