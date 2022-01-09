package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;

@AllArgsConstructor
@Getter
@Setter
public class SBPlayerDeathEvent {

    private final SBPlayer player;
    private final double loseCoins;

}
