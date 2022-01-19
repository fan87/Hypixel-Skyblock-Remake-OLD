package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;


@Getter
@Setter
@AllArgsConstructor
public class PlayerTickEvent {

    private final SBPlayer player;

}
