package me.fan87.commonplugin.features.impl.hypixel;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.greenrobot.eventbus.Subscribe;

public class PerWorldChat extends SBFeature {
    public PerWorldChat() {
        super("Per World Chat", "Make you can only talk to players in the same world as you (Except /tell)", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -120)
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        for (Player player : event.getPlayer().getWorld().getPlayers()) {
            player.sendMessage(String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage()));
        }
    }

}
