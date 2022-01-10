package me.fan87.lpskyblock.features;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Team;
import org.greenrobot.eventbus.Subscribe;

public class LPChatMessage extends SBFeature {
    public LPChatMessage() {
        super("LuckPerm Chat Message", "Changes the formatting of luckperm chat message", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -100)
    public void onChat(AsyncPlayerChatEvent event) {
        for (Team team : event.getPlayer().getScoreboard().getTeams()) {
            for (String player : team.getEntries()) {
                if (player.equals(event.getPlayer().getName())) {
                    event.setFormat(team.getPrefix() + event.getFormat());
                }
            }
        }
    }
}
