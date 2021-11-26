package me.fan87.commonplugin.players;

import de.tr7zw.changeme.nbtapi.NBTItem;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.GuiMenu;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBItems;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.SpigotTimings;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Field;

public class SBPlayer {

    @Getter
    private final Player player;

    @Getter
    private SBPlayerStats stats = new SBPlayerStats();

    private SkyBlock skyBlock;


    public SBPlayer(Player player, SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        this.player = player;
        EventManager.EVENT_BUS.register(this);
    }

    public void updateInventory() {
        stats = new SBPlayerStats();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType() == Material.AIR) continue;
            ItemStack item = player.getInventory().getItem(i);
            SBItemStack sbItemStack = new SBItemStack(item);
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                sbItemStack.updatePlayerStats(this, i);
            } else {
                if (sbItemStack.getType().getItem().activateForSlot(i, this)) {
                    sbItemStack.updatePlayerStats(this, i);
                }
            }
        }

    }


    public CraftPlayer getCraftPlayer() {
        return (CraftPlayer) player;
    }

    @Subscribe
    public void onTick(ServerTickEvent event) {
        tickStats();
    }


    @Subscribe
    public void skyblockMenuClick(InventoryClickEvent event) {
        if (event.getHotbarButton() != -1) {
            ItemStack item = event.getWhoClicked().getInventory().getItem(event.getHotbarButton());
            if (item != null && item.getType() != Material.AIR) {
                SBItemStack itemStack = new SBItemStack(item);
                if (itemStack.getType().getItem() == SBItems.SKYBLOCK_MENU) {
                    event.setCancelled(true);
                    openSkyBlockMenu();
                }
            }
        }
        if (event.getCursor() != null && event.getCursor().getType() != Material.AIR) {
            ItemStack item = event.getCursor();
            SBItemStack itemStack = new SBItemStack(item);
            SBMaterial type = itemStack.getType();
            if (type.getType() == SBMaterial.ItemType.CUSTOM && type.getItem() == SBItems.SKYBLOCK_MENU) {
                event.setCancelled(true);
                openSkyBlockMenu();
            }
        }
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
            ItemStack item = event.getCurrentItem();
            SBItemStack itemStack = new SBItemStack(item);
            SBMaterial type = itemStack.getType();
            if (type.getType() == SBMaterial.ItemType.CUSTOM && type.getItem() == SBItems.SKYBLOCK_MENU) {
                event.setCancelled(true);
                openSkyBlockMenu();
            }
        }
    }

    public void tickStats() {
        for (Field declaredField : stats.getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                ((SBStat) declaredField.get(stats)).onTick(this);
            } catch (Exception e) {
                System.out.println("Failed to update stat of " + player.getUniqueId());
                e.printStackTrace();
            }
        }
    }

    public boolean isItemActive(SBCustomItem customItem) {
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            if (player.getInventory().getItem(i) == null || player.getInventory().getItem(i).getType() == Material.AIR) continue;
            ItemStack item = player.getInventory().getItem(i);
            SBItemStack sbItemStack = new SBItemStack(item);
            if (sbItemStack.getType().getType() != SBMaterial.ItemType.CUSTOM) {
                continue;
            } else {
                if (sbItemStack.getType().getItem().activateForSlot(i, this) && sbItemStack.getType().getItem() == customItem) {
                    return true;
                }
            }
        }
        return false;
    }


    public void openSkyBlockMenu() {
        SmartInventory.builder()
                .manager(new InventoryManager(skyBlock))
                .id("myInventory")
                .provider(new GuiMenu(skyBlock.getPlayersManager()))
                .size(6, 9)
                .title("SkyBlock Menu")
                .build().open(getPlayer());
    }

}
