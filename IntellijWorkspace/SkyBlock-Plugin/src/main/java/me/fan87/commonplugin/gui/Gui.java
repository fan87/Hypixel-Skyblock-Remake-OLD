package me.fan87.commonplugin.gui;

import lombok.Getter;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;

public abstract class Gui {

    @Getter
    private GuiItem[] items;
    @Getter
    protected int size;
    @Getter
    private String title;
    private Inventory inventory;

    public abstract void init();

    public Gui(String title, int rows) {
        this.items = new GuiItem[rows*9];
        this.title = title;
        this.size = rows*9;
        EventManager.EVENT_BUS.register(this);
    }

    @Subscribe
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
            event.setCancelled(true);
            for (int i = 0; i < items.length; i++) {
                if (i == event.getRawSlot()) {
                    items[i].getHandler().handleClick(event);
                }
            }
        }
    }

    @Subscribe
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(inventory)) {
            destroy();
        }
    }

    public Gui fillBorder(GuiItem item) {
        for (int i = 0; i < items.length; i++) {
            int x = i % 9;
            int y = i / 9;
            if (x == 0 || x == 8) {
                items[i] = item;
            }
            if (y == 0 || y == size/9) {
                items[i] = item;
            }
        }
        return this;
    }

    public Gui fill(GuiItem item) {
        Arrays.fill(items, item);
        return this;
    }

    public Gui set(int x, int y, GuiItem item) {
        int slot = (y - 1) * 9 + (x - 1);
        slot = Math.min(size, Math.max(0, slot));
        items[slot] = item;
        return this;
    }

    public Gui fill(int fromX, int fromY, int toX, int toY, GuiItem item) {
        if (fromX > toX) {
            int temp = fromX;
            fromX = toX;
            toX = temp;
        }
        if (fromY > toY) {
            int temp = fromY;
            fromY = toY;
            toY = temp;
        }
        for (int i = 0; i < items.length; i++) {
            int x = i % 9;
            int y = i / 9;
            if (x >= fromX && x <= toX
               && y >= fromY && y <= toY) {
                items[i] = item;
            }
        }
        return this;
    }

    public void open(Player player) {
        player.openInventory(getInventory());
    }

    public Inventory getInventory() {
        if (this.inventory != null) return this.inventory;
        init();
        Inventory inventory = Bukkit.createInventory(null, size, title);
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                inventory.setItem(i, items[i].getItemStack());
            }
        }
        this.inventory = inventory;
        return inventory;
    }

    public void renderGoBackItems(Gui previousPage, Player player) {
        String title = previousPage.getTitle();

        set(4, 6, new GuiItem(GuiItemProvider.getPreviousPageItem(title), event -> {
            previousPage.open(player);
        }));
        set(5, 6, new GuiItem(GuiItemProvider.getClosePageItem(), event -> {
            player.closeInventory();
        }));
    }

    public void destroy() {
        EventManager.EVENT_BUS.unregister(this);
    }

}
