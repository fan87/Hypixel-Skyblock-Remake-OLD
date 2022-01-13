package me.fan87.basiccommandspack.gui;

import me.fan87.commonplugin.gui.GuiItem;
import me.fan87.commonplugin.gui.GuiItemProvider;
import me.fan87.commonplugin.gui.impl.GuiSkyBlockMenu;
import me.fan87.commonplugin.gui.impl.types.GuiList;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.NPCPlayer;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.ItemStackBuilder;
import me.fan87.commonplugin.utils.SBNamespace;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class GuiNpcMenu extends GuiList {

    private SBPlayer player;

    public GuiNpcMenu(SBPlayer player, int page) {
        super("Spawn NPC", page, new ArrayList<>());
        this.player = player;
    }

    @Override
    public void init() {
        for (SBNamespace sbNamespace : player.getSkyBlock().getNpcManager().getNpcList().keySet()) {
            Class<? extends AbstractNPC> clazz = player.getSkyBlock().getNpcManager().getNpcList().get(sbNamespace);
            NPCPlayer.NPCRegistry registry = NPCPlayer.getNPCRegistry(clazz);
            assert registry != null;
            ItemStack build = new ItemStackBuilder(Material.SKULL_ITEM, 3)
                    .setSkullTexture(registry.skin())
                    .addLore(ChatColor.GRAY + "Location: " + ChatColor.BLUE + registry.world().getName())
                    .addLore(ChatColor.GRAY + "Provider: " + ChatColor.BLUE + (registry.addonName().equals("default")?ChatColor.GRAY + "Built-in":player.getSkyBlock().getAddon(registry.addonName()).getName()))
                    .addLore(ChatColor.GRAY + "Namespace: " + ChatColor.GRAY + sbNamespace.toString())
                    .addLore("")
                    .addLore(ChatColor.YELLOW + "Click to spawn!")
                    .setDisplayName(ChatColor.GREEN + registry.name()).build();
            getContents().add(new GuiItem(build, event -> {
                player.getPlayer().closeInventory();
                try {
                    Location location = player.getPlayer().getLocation();
                    player.getWorld().addNPC(location.toVector(), sbNamespace);
                    player.getPlayer().sendMessage(ChatColor.GREEN + "Successfully spawned " + ChatColor.YELLOW + registry.name() + ChatColor.GREEN + "!");
                } catch (Exception e) {
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintWriter writer  = new PrintWriter(outputStream);
                    e.printStackTrace(writer);
                    writer.close();
                    String s = new String(outputStream.toByteArray());
                    for (String s1 : s.split("\n")) {
                        player.getPlayer().sendMessage(ChatColor.RED + s1);
                    }
                }
            }));
        }
        fillBorder(new GuiItem(GuiItemProvider.backgroundGlassPane()));
        renderGoBackItems(new GuiSkyBlockMenu(player), player.getPlayer());
        putItems();
    }

    @Override
    public void goPage(int page) {
        new GuiNpcMenu(player, page).open(player.getPlayer());
    }
}
