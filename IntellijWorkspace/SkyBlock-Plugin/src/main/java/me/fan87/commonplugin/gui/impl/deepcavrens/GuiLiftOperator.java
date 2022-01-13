package me.fan87.commonplugin.gui.impl.deepcavrens;

import me.fan87.commonplugin.features.impl.npc.LiftOperatorRegister;
import me.fan87.commonplugin.gui.ButtonHandler;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiLiftOperator extends Gui {
    private SBPlayer player;
    public GuiLiftOperator(SBPlayer player) {
        super("Lift", 6);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        set(2, 2, new GuiItem(new ItemStackBuilder(Material.GOLD_INGOT)
                .addAllItemFlags()
                .setDisplayName(ChatColor.GREEN + "Gunpowder Mines")
                .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Gunpowder Mines" + ChatColor.GRAY + "!", true)
                .addLore("")
                .addLore(ChatColor.YELLOW + "Click to travel!")
                .build(), new ButtonHandler() {
            @Override
            public void handleClick(InventoryClickEvent event) {
                Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).points.get(player.getSkyBlock().getAreasManager().GUNPOWDER_MINES);
                player.getPlayer().teleport(location);
            }
        }));
    }
}
