package me.fan87.commonplugin.features.impl.greif;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class WorldInteractDisabler extends SBFeature {

    public WorldInteractDisabler() {
        super("World Interact Disabler", "So players can't break/place blocks. Not that the priority is very high, so other listeners can setCancelled(false)", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = 10)
    public void worldInteractDisabler(PlayerInteractEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getPlayer().getWorld().getName());
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (world.canPlayerBuild(player)) return;
        if (!player.isBuilding() && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
        }
    }

    @Subscribe(priority = 10)
    public void breakBlockDisabler(BlockBreakEvent event) {
        SBWorld world = skyBlock.getWorldsManager().getWorld(event.getPlayer().getWorld().getName());
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (world.canPlayerBuild(player)) return;
        if (!player.isBuilding()) {
            event.setCancelled(true);
        }
    }
}
