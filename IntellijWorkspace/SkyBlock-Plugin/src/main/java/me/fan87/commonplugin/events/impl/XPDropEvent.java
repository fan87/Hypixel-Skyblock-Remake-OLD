package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

@Getter
@AllArgsConstructor
public class XPDropEvent extends Cancellable {

    private SBPlayer player;
    @Setter
    private int xp;
    private BlockBreakEvent blockBreakEvent;
    private Random random;

}
