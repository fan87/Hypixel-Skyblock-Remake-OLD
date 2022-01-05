package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@Getter
@Setter
@AllArgsConstructor
public class PlayerCriticalEvent extends Cancellable {

    private EntityDamageByEntityEvent event;

}
