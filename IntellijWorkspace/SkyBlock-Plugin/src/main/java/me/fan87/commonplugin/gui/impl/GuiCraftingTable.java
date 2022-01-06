package me.fan87.commonplugin.gui.impl;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.gui.ButtonHandler;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.recipes.SBRecipe;
import me.fan87.commonplugin.utils.InventoryUtils;
import net.minecraft.server.v1_8_R3.PacketPlayInWindowClick;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.greenrobot.eventbus.Subscribe;

public class GuiCraftingTable extends Gui {
    private SkyBlock skyBlock;
    private SBPlayer player;

    public GuiCraftingTable(SkyBlock skyBlock, SBPlayer player) {
        super("Craft Item", 6);
        this.skyBlock = skyBlock;
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        fill(0, 6, 9, 6, new GuiItem(GuiItemProvider.backgroundGlassPane(14)));
        fill(2, 2, 4, 4, new GuiItem(new ItemStack(Material.AIR)));
        set(7, 3, new GuiItem(new ItemStack(Material.AIR)));
        set(5, 6, new GuiItem(GuiItemProvider.getPreviousPageItem("SkyBlock Menu"), event -> {
            new GuiSkyBlockMenu(player).open(player.getPlayer());
        }));
    }

    public void setOutputSlot(ItemStack outputSlot) {
        if (!outputSlot.equals(getInventory().getItem(getSlotNumberByXY(7, 3)))) {
            set(7, 3, new GuiItem(new ItemStack(outputSlot), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    if (event.getCursor() != null) {
                        takeItem(event);
                    } else {
                        event.setCancelled(true);
                    }
                }
            }));
        }
    }

    public ItemStack[] getInputSlots() {
        ItemStack[] content = new ItemStack[9];
        int i = 0;
        for (int y = 2; y < 5; y++) {
            for (int x = 2; x < 5; x++) {
                content[i++] = getInventory().getItem(getSlotNumberByXY(x, y));
            }
        }
        return content;
    }

    @Override
    public boolean canMove(InventoryClickEvent inventoryClickEvent) {
        if (
                inventoryClickEvent.getCurrentItem() != null
                && inventoryClickEvent.getCurrentItem().getItemMeta() != null
                && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName() != null
                && inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals(" ")) return false;
        return true;
    }

    SBRecipe recipe;

    private void updateOutputSlot() {
        Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("HypixelSkyBlock-Common"), new Runnable() {
            @Override
            public void run() {
                ItemStack[] content = new ItemStack[9];
                int i = 0;
                for (int y = 2; y < 5; y++) {
                    for (int x = 2; x < 5; x++) {
                        content[i++] = getInventory().getItem(getSlotNumberByXY(x, y));
                    }
                }
                for (SBRecipe craftingRecipe : skyBlock.getRecipesManager().getCraftingRecipes()) {
                    if (craftingRecipe.match(content, 3, 3)) {
                        recipe = craftingRecipe;
                        setOutputSlot(craftingRecipe.getOutput());
                        return;
                    }
                }
                setOutputSlot(new ItemStack(Material.AIR));
            }
        }, 1);
    }

    @Subscribe
    public void onClick(PacketPlayReceiveEvent event) {
        if (event.getNMSPacket().getRawNMSPacket() instanceof PacketPlayInWindowClick) {
            if (getInventory().getViewers().size() > 0 && event.getPlayer().equals(getInventory().getViewers().get(0))) {
                updateOutputSlot();
            }
        }
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        for (int y = 2; y < 5; y++) {
            for (int x = 2; x < 5; x++) {
                InventoryUtils.giveItem(((Player) event.getPlayer()), getInventory().getItem(getSlotNumberByXY(x, y)));
            }
        }

    }

    private void takeItem(InventoryClickEvent event) {
        ItemStack[] content = new ItemStack[9];
        int i = 0;
        for (int y = 2; y < 5; y++) {
            for (int x = 2; x < 5; x++) {
                content[i++] = getInventory().getItem(getSlotNumberByXY(x, y));
            }
        }

        if (event.isShiftClick()) {
            int amount = 0;
            while (recipe.action(content, 3, 3)) {
                for (int i1 = 0; i1 < content.length; i1++) {
                    int x = i1 % 3;
                    int y = i1 / 3;
                    set(x + 2, y + 2, new GuiItem(content[i1]));
                }
                amount += recipe.getOutput().getAmount();
            }
            event.setCancelled(true);
            while (amount > 0) {
                ItemStack clone = recipe.getOutput().clone();
                clone.setAmount(Math.min(amount, clone.getMaxStackSize()));
                amount -= clone.getMaxStackSize();
                clone = new SBItemStack(clone).getItemStack();
                InventoryUtils.giveItem(((Player) event.getWhoClicked()), clone);
            }

            set(7, 3, new GuiItem(new ItemStack(Material.AIR)));
            recipe = null;
        } else {
            if (recipe != null && recipe.action(content, 3, 3)) {
                for (int i1 = 0; i1 < content.length; i1++) {
                    int x = i1 % 3;
                    int y = i1 / 3;
                    set(x + 2, y + 2, new GuiItem(content[i1]));
                }
                recipe = null;
            }
        }
    }

}
