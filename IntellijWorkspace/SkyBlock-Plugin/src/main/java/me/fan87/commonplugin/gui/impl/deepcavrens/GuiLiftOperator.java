package me.fan87.commonplugin.gui.impl.deepcavrens;

import me.fan87.commonplugin.features.impl.LiftOperatorRegister;
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
import org.bukkit.inventory.ItemStack;

public class GuiLiftOperator extends Gui {
    private SBPlayer player;
    public GuiLiftOperator(SBPlayer player) {
        super("Lift", 6);
        this.player = player;
    }

    @Override
    public void init() {
        fill(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        ItemStack empty = new ItemStackBuilder(Material.INK_SACK, 8)
                .addAllItemFlags()
                .setDisplayName(ChatColor.RED + "???")
                .addLore(ChatColor.RED + "You need to discover this area first before you can travel there using the lift!", true)
                .build();
        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().GUNPOWDER_MINES)) {
            set(2, 2, new GuiItem(new ItemStackBuilder(Material.GOLD_INGOT)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Gunpowder Mines")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Gunpowder Mines" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().GUNPOWDER_MINES);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(2, 2, new GuiItem(empty));
        }

        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().LAPIS_QUARRY)) {
            set(4, 2, new GuiItem(new ItemStackBuilder(Material.INK_SACK, 4)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Lapis Quarry")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Lapis Quarry" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().LAPIS_QUARRY);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(4, 2, new GuiItem(empty));
        }
        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().PIGMENS_DEN)) {
            set(6, 2, new GuiItem(new ItemStackBuilder(Material.REDSTONE)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Pigmen's Den")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Pigmen's Den" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().PIGMENS_DEN);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(6, 2, new GuiItem(empty));
        }
        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().SLIMEHILL)) {
            set(8, 2, new GuiItem(new ItemStackBuilder(Material.EMERALD)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Slimehill")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Slimehill" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().SLIMEHILL);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(8, 2, new GuiItem(empty));
        }
        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().DIAMOND_RESERVE)) {
            set(2, 4, new GuiItem(new ItemStackBuilder(Material.DIAMOND)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Diamond Reserve")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Diamond Reserve" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().DIAMOND_RESERVE);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(2, 4, new GuiItem(empty));
        }
        if (player.hasBeenTo(player.getSkyBlock().getAreasManager().OBSIDIAN_SANCTUARY)) {
            set(4, 4, new GuiItem(new ItemStackBuilder(Material.OBSIDIAN)
                    .addAllItemFlags()
                    .setDisplayName(ChatColor.GREEN + "Obsidian Sanctuary")
                    .addLore(ChatColor.GRAY + "Teleports you to the " + ChatColor.AQUA + "Obsidian Sanctuary" + ChatColor.GRAY + "!", true)
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to travel!")
                    .build(), new ButtonHandler() {
                @Override
                public void handleClick(InventoryClickEvent event) {
                    Location location = player.getSkyBlock().getFeaturesManager().getFeature(LiftOperatorRegister.class).liftPoints.get(player.getSkyBlock().getAreasManager().OBSIDIAN_SANCTUARY);
                    player.getPlayer().teleport(location);
                }
            }));
        } else {
            set(4, 4, new GuiItem(empty));
        }
    }
}
