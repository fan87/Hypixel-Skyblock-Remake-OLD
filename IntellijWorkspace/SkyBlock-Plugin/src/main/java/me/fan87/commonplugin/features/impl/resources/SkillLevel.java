package me.fan87.commonplugin.features.impl.resources;

import me.fan87.commonplugin.events.impl.BlockDropEvent;
import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.Material;
import me.fan87.commonplugin.events.Subscribe;

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
        if (event.getBlockBreakEvent().getBlock().getType() == Material.LOG || event.getBlockBreakEvent().getBlock().getType() == Material.LOG_2) {
            event.getPlayer().getSkills().skillForaging.addExp(6, event.getPlayer());
        }
    }

}
