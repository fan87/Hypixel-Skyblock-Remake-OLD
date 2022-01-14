package me.fan87.commonplugin.features.impl.resources;

import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.skill.SBPlayerSkills;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.privateisland.PrivateIsland;
import org.bukkit.Material;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import static org.bukkit.Material.*;


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
        Material type = event.getBlockBreakEvent().getBlock().getType();
        SBPlayerSkills skills = event.getPlayer().getSkills();
        if (type == STONE) skills.skillMining.addExp(1, event.getPlayer());
        if (type == COBBLESTONE) skills.skillMining.addExp(1, event.getPlayer());
        if (type == ICE) skills.skillMining.addExp(0.2, event.getPlayer());
        if (type == NETHERRACK) skills.skillMining.addExp(0.5, event.getPlayer());
        if (type == SAND) skills.skillMining.addExp(3, event.getPlayer());
        if (type == GRAVEL) skills.skillMining.addExp(4, event.getPlayer());
        if (type == QUARTZ) skills.skillMining.addExp(5, event.getPlayer());
        if (type == ENDER_STONE) skills.skillMining.addExp(3, event.getPlayer());
        if (type == COAL_ORE) skills.skillMining.addExp(5, event.getPlayer());
        if (type == IRON_ORE) skills.skillMining.addExp(5, event.getPlayer());
        if (type == GOLD_ORE) skills.skillMining.addExp(6, event.getPlayer());
        if (type == GOLD_BLOCK) skills.skillMining.addExp(20, event.getPlayer());
        if (type == GLOWSTONE) skills.skillMining.addExp(7, event.getPlayer());
        if (type == LAPIS_ORE) skills.skillMining.addExp(7, event.getPlayer());
        if (type == REDSTONE_ORE) skills.skillMining.addExp(7, event.getPlayer());
        if (type == EMERALD_ORE) skills.skillMining.addExp(9, event.getPlayer());
        if (type == DIAMOND_ORE) skills.skillMining.addExp(10, event.getPlayer());
        if (type == DIAMOND_BLOCK) skills.skillMining.addExp(15, event.getPlayer());
        if (type == OBSIDIAN) skills.skillMining.addExp(20, event.getPlayer());
        if (type == WHEAT && ((Ageable) event.getBlockBreakEvent().getBlock()).getAge() == 7) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == POTATO && ((Ageable) event.getBlockBreakEvent().getBlock()).getAge() == 7) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == CARROT && ((Ageable) event.getBlockBreakEvent().getBlock()).getAge() == 7) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == MELON_BLOCK) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == PUMPKIN) skills.skillFarming.addExp(4.5, event.getPlayer());
        if (type == SUGAR_CANE) skills.skillFarming.addExp(2.0, event.getPlayer());
        if (type == CACTUS && ((Ageable) event.getBlockBreakEvent().getBlock()).getAge() == 7) skills.skillFarming.addExp(2.0, event.getPlayer());
        if (type == COCOA && ((Ageable) event.getBlockBreakEvent().getBlock()).getAge() >= 8) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == RED_ROSE) skills.skillForaging.addExp(1.0, event.getPlayer());
        if (type == LOG) skills.skillForaging.addExp(6.0, event.getPlayer());
        if (type == LOG_2) skills.skillForaging.addExp(6.0, event.getPlayer());
    }

    @Subscribe(priority = -100)
    public void onKillEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getEntity().getKiller());


    }

}
