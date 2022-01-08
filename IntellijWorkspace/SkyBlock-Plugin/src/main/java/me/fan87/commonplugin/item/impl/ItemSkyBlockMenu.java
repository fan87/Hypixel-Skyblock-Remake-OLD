package me.fan87.commonplugin.item.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.item.SBCustomItem;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.recipes.impl.SBShapedRecipe;
import me.fan87.commonplugin.recipes.recipeitem.impl.SBCustomRecipeItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

public class ItemSkyBlockMenu extends SBCustomItem {


    public ItemSkyBlockMenu(SkyBlock skyBlock) {
        super("SKYBLOCK_MENU", "§aSkyBlock Menu §7(Right Click)", "View all of your SkyBlock progress, including your Skills, Collections, Recipes, and more!\n\n" + ChatColor.YELLOW + "Click to open!", Material.NETHER_STAR, (short) 0, Rarity.COMMON, Category.MATERIAL, skyBlock, RecipeCategory.SPECIAL);
        skyBlock.getRecipesManager().getCraftingRecipes().add(new SBShapedRecipe(this, 1, true)
                .ingredient('B', new SBCustomRecipeItem(ItemsVANILLA.BEDROCK, 64))
                .ingredient('D', new SBCustomRecipeItem(ItemsVANILLA.DIAMOND, 64))
                        .shape(
                                " B ",
                                "BDB",
                                " B "
                        )
                );
    }

    @Subscribe
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().getInventory().setItem(8, new SBItemStack(this).getItemStack());
    }

    @Subscribe
    public void onTrashingMe(PlayerDropItemEvent event) {
        SBItemStack itemStack = new SBItemStack(event.getItemDrop().getItemStack());
        if (itemStack.getType().getItem() == this) {
            event.setCancelled(true);
            skyBlock.getPlayersManager().getPlayer(event.getPlayer()).openSkyBlockMenu();
        }
    }

    @Subscribe
    public void skyblockMenuClick(InventoryClickEvent event) {
        if (event.getHotbarButton() != -1) {
            ItemStack item = event.getWhoClicked().getInventory().getItem(event.getHotbarButton());
            if (item != null && item.getType() != Material.AIR) {
                SBItemStack itemStack = new SBItemStack(item);
                if (itemStack.getType().getItem() == SBItems.SKYBLOCK_MENU) {
                    event.setCancelled(true);
//                    skyBlock.getPlayersManager().getPlayer((Player) event.getWhoClicked()).openSkyBlockMenu();
                }
            }
        }
        if (event.getCursor() != null && event.getCursor().getType() != Material.AIR) {
            ItemStack item = event.getCursor();
            SBItemStack itemStack = new SBItemStack(item);
            SBMaterial type = itemStack.getType();
            if (type.getType() == SBMaterial.ItemType.CUSTOM && type.getItem() == SBItems.SKYBLOCK_MENU) {
                event.setCancelled(true);
//                skyBlock.getPlayersManager().getPlayer((Player) event.getWhoClicked()).openSkyBlockMenu();
            }
        }
        if (event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR) {
            ItemStack item = event.getCurrentItem();
            SBItemStack itemStack = new SBItemStack(item);
            SBMaterial type = itemStack.getType();
            if (type.getType() == SBMaterial.ItemType.CUSTOM && type.getItem() == SBItems.SKYBLOCK_MENU) {
                event.setCancelled(true);
//                skyBlock.getPlayersManager().getPlayer((Player) event.getWhoClicked()).openSkyBlockMenu();
            }
        }

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
