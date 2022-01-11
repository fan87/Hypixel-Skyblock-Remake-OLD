package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.world.SBWorld;

@Getter
@AllArgsConstructor
public class PlayerPostPortalEvent {

    private SBWorld from;
    private SBWorld to;
    private SBPlayer player;

}
