package me.fan87.commonplugin.gui;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GuiItem {

    @Getter
    private ButtonHandler handler;
    @Getter
    private final ItemStack itemStack;

    public GuiItem(ItemStack itemStack, ButtonHandler handler) {
        this.handler = handler;
        this.itemStack = itemStack;
    }

    public GuiItem(ItemStack itemStack) {
        this.handler = new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {

            }
        };
        this.itemStack = itemStack;
    }


}
