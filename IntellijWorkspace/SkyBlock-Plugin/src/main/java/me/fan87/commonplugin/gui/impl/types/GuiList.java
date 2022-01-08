package me.fan87.commonplugin.gui.impl.types;

import lombok.Getter;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public abstract class GuiList extends Gui {

    private final int currentPage;
    @Getter
    private final List<GuiItem> contents;

    public GuiList(String title, int currentPage, List<GuiItem> contents) {
        super(title, 6);
        this.contents = contents;
        this.currentPage = currentPage;
    }

    public void putItems() {
        setTitle(String.format("(%d/%d) %s", getCurrentPage(), getMaxPages(), getTitle()));
        for (int i = 0; i < contents.size(); i++) {
            GuiItem item = contents.get(i);
            if (i > getLastIndex()) break;
            if (i <= getFirstIndex()) {
                int[] position = getPositionOf(i - getFirstIndex());
                set(position[0], position[1], item);
            }
        }
        if (canGoNextPage()) {
            set(9, 6, new GuiItem(new ItemStackBuilder(Material.ARROW)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Next Page")
                    .addLore(ChatColor.YELLOW + "Page " + (getCurrentPage() + 1))
                    .build()));
        }
        if (canGoNextPage()) {
            set(1, 6, new GuiItem(new ItemStackBuilder(Material.ARROW)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Previous Page")
                    .addLore(ChatColor.YELLOW + "Page " + (getCurrentPage() - 1))
                    .build()));
        }
    }

    public int getMaxPages() {
        return (int) Math.ceil(contents.size() / 28d);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public boolean canGoPreviousPage() {
        return getCurrentPage() > 1;
    }

    public boolean canGoNextPage() {
        return getCurrentPage() < getMaxPages();
    }

    public int getFirstIndex() {
        return (getCurrentPage() - 1) * 28;
    }

    public int getLastIndex() {
        return getCurrentPage() * 28 - 1;
    }

    public int[] getPositionOf(int index) {
        return new int[] {
                index/7 + 2,
                index%7 + 2
        };
    }

    public abstract void goPage(int page);

}
