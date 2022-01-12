package me.fan87.lpskyblock.features;

import me.fan87.commonplugin.features.SBFeature;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import me.fan87.commonplugin.events.Subscribe;

public class LPDisplayNameChanger extends SBFeature {
    public LPDisplayNameChanger() {
        super("LuckPerm Prefix Support", "Changes your display name if you have luckperm installed", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe()
    public void onJoin(PlayerJoinEvent event) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(event.getPlayer().getUniqueId());
        if (user != null) {
            String prefix = user.getCachedData().getMetaData().getPrefix();
            if (prefix == null) prefix = ""; else prefix = prefix.replace("&", ChatColor.COLOR_CHAR + "");
            String suffix = user.getCachedData().getMetaData().getSuffix();
            if (suffix == null) suffix = ""; else suffix = suffix.replace("&", ChatColor.COLOR_CHAR + "");
            String color = "";
            boolean was = false;
            for (char c : prefix.toCharArray()) {
                if (c == ChatColor.COLOR_CHAR) {
                    was = true;
                    continue;
                }
                if (was) {
                    color += "§" + c;
                    was = !was;
                }
            }
            event.getPlayer().setDisplayName(color + event.getPlayer().getDisplayName());
            Scoreboard scoreboard = event.getPlayer().getScoreboard();
            Team team = scoreboard.getTeam("SB" + event.getPlayer().getUniqueId().toString().substring(0, 14));
            if (team == null) {
                team = scoreboard.registerNewTeam("SB" + event.getPlayer().getUniqueId().toString().substring(0, 14));
            }
            team.setPrefix(prefix);
            team.setSuffix(suffix);
            team.addPlayer(event.getPlayer());
        }
    }
}
