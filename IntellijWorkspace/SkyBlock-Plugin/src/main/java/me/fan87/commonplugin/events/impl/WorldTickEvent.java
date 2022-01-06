package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.world.SBWorld;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.World;

@Getter
@AllArgsConstructor
public class WorldTickEvent {

    private final WorldsManager.WorldType worldType;
    private final World world;
    private final SBWorld sbWorld;

}
