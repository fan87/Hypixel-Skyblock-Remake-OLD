package me.fan87.commonplugin.players;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.events.EventManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PlayersManager {

    @Getter
    private final List<SBPlayer> loadedPlayers = new ArrayList<>();

    @Getter
    private final SkyBlock skyBlock;

    public PlayersManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.EVENT_BUS.register(this);
        for (Player onlinePlayer : skyBlock.getServer().getOnlinePlayers()) {
            addPlayer(onlinePlayer);
        }
    }

    @Subscribe(priority = 6969)
    public void onJoin(PlayerJoinEvent event) {
        addPlayer(event.getPlayer());
    }

    public void addPlayer(Player player) {
        try {
            SBPlayer sbPlayer = SBPlayer.newPlayer(player, skyBlock);
            loadedPlayers.add(sbPlayer);
        } catch (Exception e) {
            e.printStackTrace();
            if (player.isOp()) {
                try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    PrintWriter writer = new PrintWriter(out);
                    e.printStackTrace(writer);
                    writer.close();
                    String message = out.toString();
                    String m = "";
                    for (String s : message.split("\n")) {
                        m += ChatColor.RED + s + "\n";
                    }
                    player.kickPlayer(ChatColor.RED + "Something went wrong while logging you in!\nPlease contact the plugin developer!\n\n" + ChatColor.RED + m);
                } catch (Exception ignored) {}
            } else {
                player.kickPlayer(ChatColor.RED + "Failed to log you in! Please contact the server admin!\n(You will be able to see the error message after getting OP)");
            }
        }
    }

    @Subscribe(priority = -6969)
    public void removePlayer(PlayerQuitEvent event) {
        SBPlayer player = getPlayer(event.getPlayer());
        player.save();
        EventManager.EVENT_BUS.unregister(loadedPlayers.remove(player));
    }

    public SBPlayer getPlayer(Player player) {
        return loadedPlayers.stream().filter(sbPlayer -> player.equals(sbPlayer.getPlayer())).findFirst().orElse(null);
    }

}
