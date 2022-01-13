package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.players.SBPlayer;

@Getter
@AllArgsConstructor
public class SBPlayerJoinEvent {

    private SBPlayer player;

}
