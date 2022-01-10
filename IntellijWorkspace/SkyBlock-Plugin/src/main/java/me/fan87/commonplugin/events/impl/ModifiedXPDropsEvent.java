package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import org.bukkit.event.block.BlockBreakEvent;

@Getter
@Setter
@AllArgsConstructor
public class ModifiedXPDropsEvent extends Cancellable {

    private int xp;
    private BlockBreakEvent blockBreakEvent;

}
