package me.fan87.commonplugin.features.impl.resources;

import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.greenrobot.eventbus.Subscribe;

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

    @Subscribe(priority = 200)
    public void onResourceLevel(BlockBreakEvent event) {
        if (event.isCancelled()) return;
        SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
        if (event.getBlock().getType() == Material.LOG || event.getBlock().getType() == Material.LOG_2) {
            player.getSkills().skillForaging.addExp(6, player);
        }
    }

}
