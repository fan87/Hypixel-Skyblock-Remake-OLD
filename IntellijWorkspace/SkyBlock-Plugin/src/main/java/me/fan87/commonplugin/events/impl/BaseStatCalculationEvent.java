package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.stats.SBStat;

@Getter
@Setter
@AllArgsConstructor
public class BaseStatCalculationEvent {

    private SBStat stat;
    private SBPlayer player;
    private double baseValue;

}
