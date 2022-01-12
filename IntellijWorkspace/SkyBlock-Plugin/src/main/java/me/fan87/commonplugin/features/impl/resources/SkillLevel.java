package me.fan87.commonplugin.features.impl.resources;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.privateisland.PrivateIsland;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class SkillLevel extends SBFeature {
    public SkillLevel() {
        super("Skill Level", "Level up skill", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -100)
    public void onPlace(BlockPlaceEvent event) {
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (player.getWorld().getWorldName().equals(player.getPrivateIsland().getWorldName())) {
            player.getPrivateIsland().setBlockManuallyPlaced(event.getBlock().getLocation(), true);
        }
    }

    @Subscribe(priority = -100)
    public void onResourceLevel(BlockDropEvent event) {
        SBWorld world = event.getPlayer().getWorld();
        if (event.getPlayer().getPrivateIsland().getWorldName().equals(world.getWorldName())) {
            PrivateIsland privateIsland = event.getPlayer().getPrivateIsland();
            if (privateIsland.isBlockManuallyPlaced(event.getBlockBreakEvent().getBlock().getLocation())) {
                privateIsland.setBlockManuallyPlaced(event.getBlockBreakEvent().getBlock().getLocation(), false);
                return;
            }
        }
        if (event.getBlockBreakEvent().getBlock().getType() == Material.LOG || event.getBlockBreakEvent().getBlock().getType() == Material.LOG_2) {
            event.getPlayer().getSkills().skillForaging.addExp(6, event.getPlayer());
        }
    }

}
