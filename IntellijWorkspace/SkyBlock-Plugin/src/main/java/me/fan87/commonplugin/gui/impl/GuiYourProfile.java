package me.fan87.commonplugin.gui.impl;

import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;
import me.fan87.commonplugin.utils.LoreUtils;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.fan87.commonplugin.events.Subscribe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GuiYourProfile extends Gui {

    private SBPlayer player;
    private boolean yours = true;

    public GuiYourProfile(SBPlayer player) {
        super("Your SkyBlock Profile", 6);
        this.player = player;
    }

    public GuiYourProfile(SBPlayer player, boolean yours){

        this(player);
        this.yours = yours;
        if (!yours) {
            this.setTitle("Viewing " + player.getPlayer().getName() + "'s" + " SkyBlock Profile");
        }

    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {

    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(5, 1, new GuiItem(GuiItemProvider.getMenuSkull(player, yours)));

        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());

        int slot = 0;
        for (Field declaredField : player.getStats().getClass().getDeclaredFields()) {
            try {
                declaredField.setAccessible(true);
                if (declaredField.get(player.getStats()) instanceof SBStat) {
                    SBStat stat = ((SBStat) declaredField.get(player.getStats()));
                    ItemStack iconItemStack = stat.getIconItemStack();
                    ItemMeta itemMeta = iconItemStack.getItemMeta();
                    itemMeta.setDisplayName(stat.getColor() + stat.getIcon() + " " + stat.getName() + " " + ChatColor.WHITE + stat.getValueDisplay(stat.getValue(player)));
                    List<String> lores = new ArrayList<>();
                    lores.addAll(LoreUtils.splitLoreForLine(ChatColor.GRAY + stat.getDescription(player).replaceAll(ChatColor.RESET + "", ChatColor.RESET + "" + ChatColor.GRAY)));
                    lores.add("");
                    lores.add(ChatColor.GRAY + "Base " + stat.getName() + ": " + ChatColor.GREEN + stat.getValueDisplay(stat.getBaseValue()));
                    for (String s : LoreUtils.splitLoreForLine(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + stat.getBaseDescription().replaceAll(ChatColor.RESET + "", ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.ITALIC))) {
                        lores.add("  " + s);
                    }
                    lores.add(" ");
                    lores.add(ChatColor.GRAY + "Bonus " + stat.getName() + ": " + ChatColor.DARK_GRAY + "+" + ChatColor.YELLOW + stat.getValueDisplay(stat.getTotalBonusValue(player)));
                    for (String s : LoreUtils.splitLoreForLine(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + stat.getBonusDescription().replaceAll(ChatColor.RESET + "", ChatColor.RESET + "" + ChatColor.DARK_GRAY + "" + ChatColor.ITALIC))) {
                        lores.add("  " + s);
                    }
                    if (!stat.getExampledDescription(player).equals("")) {
                        lores.add("");
                        lores.addAll(LoreUtils.splitLoreForLine(ChatColor.GRAY + stat.getExampledDescription(player).replaceAll(ChatColor.RESET + "", ChatColor.RESET + "" + ChatColor.GRAY)));
                    }
                    itemMeta.setLore(lores);
                    iconItemStack.setItemMeta(itemMeta);
                    set(slot % 7 + 2, slot/7 + 3, new GuiItem(iconItemStack));
                    slot++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
