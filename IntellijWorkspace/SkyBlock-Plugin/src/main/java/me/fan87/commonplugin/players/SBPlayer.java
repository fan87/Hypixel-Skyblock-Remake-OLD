package me.fan87.commonplugin.players;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.GuiYourProfile;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.stats.SBPlayerStats;
import me.fan87.commonplugin.players.stats.SBStat;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
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
        GuiSkyBlockMenu menu = new GuiSkyBlockMenu(this);
        getPlayer().openInventory(menu.getInventory());
    }

    public void openProfileMenu() {
        GuiYourProfile profileGui = new GuiYourProfile(this);
        profileGui.open(player.getPlayer());
    }

}
