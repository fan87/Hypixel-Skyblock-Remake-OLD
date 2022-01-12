package me.fan87.commonplugin.gui;

import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import me.fan87.commonplugin.events.Subscribe;

import java.util.Arrays;

public abstract class Gui {

    @Getter
    private GuiItem[] items;
    @Getter
    protected int size;
    @Getter
    @Setter
    private String title;
    private Inventory inventory;

    public abstract void init();

    public Gui(String title, int rows) {
        this.items = new GuiItem[rows*9];
        this.title = title.substring(0, Math.min(32, title.length()));
        this.size = rows*9;
        EventManager.register(this);
    }

    public boolean canMove(InventoryClickEvent inventoryClickEvent) {
        return false;
    }

    @Subscribe()
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
            if (!canMove(event)) {
                event.setCancelled(true);
            }
            for (int i = 0; i < items.length; i++) {
                if (i == event.getRawSlot()) {
                    if (items[i] == null) continue;
                    if (items[i].getHandler() == null) continue;
                    items[i].getHandler().handleClick(event);
                }
            }
        }
    }

    @Subscribe()
    public void onClose(InventoryCloseEvent event) {
        if (event.getInventory().equals(inventory)) {
            destroy();
            onGuiClose(event);
        }
    }

    protected void onGuiClose(InventoryCloseEvent event) {

    }

    public Gui fillBorder(GuiItem item) {
        fill(1, 1, 9, 1, item);
        fill(1, size/9, 1, 1, item);
        fill(9, size/9, 9, 0, item);
        fill(9, size/9, 0, size/9, item);
        updateInventory();
        return this;
    }

    public Gui fill(GuiItem item) {
        Arrays.fill(items, item);
        if (inventory != null) {
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, item.getItemStack());
            }
        }
        updateInventory();
        return this;
    }

    public static int getSlotNumberByXY(int x, int y) {
        return (y - 1) * 9 + (x - 1);
    }

    public Gui set(int x, int y, GuiItem item) {
        int slot = getSlotNumberByXY(x, y);
        slot = Math.min(size - 1, Math.max(0, slot));
        items[slot] = item;
        if (inventory != null) {
            inventory.setItem(slot, item.getItemStack());
        }
        updateInventory();
        return this;
    }

    public void updateInventory() {

        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("HypixelSkyBlock-Common"), new Runnable() {
            @Override
            public void run() {
                if (inventory != null) {
                    for (HumanEntity viewer : inventory.getViewers()) {
                        if (viewer instanceof Player) {
                            CraftPlayer player = (CraftPlayer) viewer;
                            player.updateInventory();
//                            List<ItemStack> items = new ArrayList<>();
//                            for (int i = 0; i < getInventory().getSize(); i++) {
//                                CraftItemStack item = (CraftItemStack) getInventory().getItem(i);
//                                items.add(item==null?CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.AIR)):CraftItemStack.asNMSCopy(item));
//                            }
//                            player.getHandle().playerConnection.sendPacket(new PacketPlayOutWindowItems(player.getHandle().activeContainer.windowId, items));
                        }
                    }
                }
            }
        }, 1);
    }

    public Gui fill(int fromX, int fromY, int toX, int toY, GuiItem item) {
        fromX--;
        fromY--;
        toX--;
        toY--;
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
                if (inventory != null) {
                    inventory.setItem(i, item.getItemStack());
                }
            }
        }
        updateInventory();
        return this;
    }

    public void open(Player player) {
//        player.getServer().getPlayer("fan87").openInventory(getInventory());
        Bukkit.getScheduler().runTaskLater(SkyBlock.getPlugin(SkyBlock.class), () -> {
            player.openInventory(getInventory());
        }, 0);
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
        EventManager.unregister(this);
    }

}
