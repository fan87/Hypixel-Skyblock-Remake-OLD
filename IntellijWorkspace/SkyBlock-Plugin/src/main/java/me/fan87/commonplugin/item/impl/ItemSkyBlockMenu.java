package me.fan87.commonplugin.item.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.greenrobot.eventbus.Subscribe;

public class ItemSkyBlockMenu extends SBCustomItem {


    public ItemSkyBlockMenu(SkyBlock skyBlock) {
        super("SKYBLOCK_MENU", "§aSkyBlock Menu §7(Right Click)", "View all of your SkyBlock progress, including your Skills, Collections, Recipes, and more!\n\n" + ChatColor.YELLOW + "Click to open!", Material.NETHER_STAR, skyBlock);
    }



    @Override
    public boolean activateForSlot(int slot, SBPlayer player) {
        if (player.getPlayer().getInventory().getHeldItemSlot() == slot) {
            return true;
        }
        return false;
    }

    @Subscribe
    public void onBlockInteract(PlayerInteractEntityEvent event) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (player != null) {
            if (player.isItemActive(this)) {
                player.openSkyBlockMenu();
                return;
            }
        }
    }


    @Subscribe
    public void onRightClick(PlayerInteractEvent event) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (player != null) {
            if (player.isItemActive(this)) {
                player.openSkyBlockMenu();
                return;
            }
        }
    }

}
