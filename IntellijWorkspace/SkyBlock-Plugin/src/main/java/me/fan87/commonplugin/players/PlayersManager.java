package me.fan87.commonplugin.players;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class PlayersManager {

    private List<SBPlayer> loadedPlayers = new ArrayList<>();

    private SkyBlock skyBlock;

    public PlayersManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.EVENT_BUS.register(this);
        for (Player onlinePlayer : skyBlock.getServer().getOnlinePlayers()) {
            loadedPlayers.add(new SBPlayer(onlinePlayer, skyBlock));
        }
    }

    @Subscribe(priority = 6969)
    public void onJoin(PlayerJoinEvent event) {
        System.out.println("Player has been registered");
        loadedPlayers.add(new SBPlayer(event.getPlayer(), skyBlock));
    }

    @Subscribe(priority = -6969)
    public void removePlayer(PlayerQuitEvent event) {
        EventManager.EVENT_BUS.unregister(loadedPlayers.remove(getPlayer(event.getPlayer())));
    }

    public SBPlayer getPlayer(Player player) {
        return loadedPlayers.stream().filter(sbPlayer -> player.equals(sbPlayer.getPlayer())).findFirst().orElse(null);
    }

}
