package me.fan87.commonplugin.features.impl.hypixel;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.ChatColor;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.greenrobot.eventbus.Subscribe;

public class ChatFormatChanger extends SBFeature {
    public ChatFormatChanger() {
        super("Chat Format Changer", "Changes the chat messages formatting to hypixel's. You can disable it if you have some plugins that do it for you.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -99)
    public void onChat(AsyncPlayerChatEvent event) {
        event.setFormat("%s" + (event.getPlayer().hasPermission("skyblock.highlightChat")? ChatColor.WHITE : ChatColor.GRAY) + ": %s");
    }
}
