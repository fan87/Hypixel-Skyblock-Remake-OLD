package me.fan87.commonplugin.features.impl.resources;

import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import static org.bukkit.Material.*;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.skill.SBPlayerSkills;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerFishEvent;

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
    public void onResourceLevel(BlockDropEvent event) {
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
        if (type == WHEAT) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == POTATO) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == CARROT) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == MELON) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == PUMPKIN) skills.skillFarming.addExp(4.5, event.getPlayer());
        if (type == SUGAR_CANE) skills.skillFarming.addExp(2.0, event.getPlayer());
        if (type == CACTUS) skills.skillFarming.addExp(2.0, event.getPlayer());
        if (type == COCOA) skills.skillFarming.addExp(4.0, event.getPlayer());
        if (type == RED_ROSE) skills.skillFarming.addExp(1.0, event.getPlayer());
        if (type == LOG) skills.skillFarming.addExp(6.0, event.getPlayer());
        if (type == LOG_2) skills.skillFarming.addExp(6.0, event.getPlayer());
    }

    @Subscribe(priority = -100)
    public void onKillEvent(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getEntity().getKiller());
    }

}
