package me.fan87.commonplugin.features.impl.gameplay.vanillabehavior;

import me.fan87.commonplugin.features.SBFeature;
import org.bukkit.event.player.PlayerJoinEvent;
import org.greenrobot.eventbus.Subscribe;

public class VanillaJoinMessageRemover extends SBFeature {
    public VanillaJoinMessageRemover() {
        super("Vanilla Join Message Remover", "Removes the vanilla join message", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
    }
}
