package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.gui.impl.GuiCraftingTable;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.greenrobot.eventbus.Subscribe;

public class CraftingTableReplacer extends SBFeature {
    public CraftingTableReplacer() {
        super("Crafting Table Gui Replacer", "Changes the vanilla crafting table gui.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onGuiOpen(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.WORKBENCH && !event.getPlayer().isSneaking()) {
            event.setCancelled(true);
            Player vanillaPlayer = event.getPlayer();
            SBPlayer player = skyBlock.getPlayersManager().getPlayer(vanillaPlayer);
            new GuiCraftingTable(skyBlock, player).open(vanillaPlayer);
        }
    }
}
