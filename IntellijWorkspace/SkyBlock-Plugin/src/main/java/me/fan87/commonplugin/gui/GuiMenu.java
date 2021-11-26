package me.fan87.commonplugin.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import me.fan87.commonplugin.players.PlayersManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GuiMenu implements InventoryProvider {

    PlayersManager playersManager;
    public GuiMenu(PlayersManager playersManager) {
        this.playersManager = playersManager;
    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        inventoryContents.fill(ClickableItem.of(GuiItemProvider.backgroundGlassPane(), event -> {
            System.out.println(event.getWhoClicked());
            event.setCancelled(true);
        }));
        inventoryContents.set(SlotPos.of(1, 4), ClickableItem.of(GuiItemProvider.getMenuSkull(playersManager.getPlayer(player)), event -> {
            event.setCancelled(true);
        }));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
