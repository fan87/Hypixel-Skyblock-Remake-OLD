package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import org.bukkit.event.entity.EntityDamageEvent;

@Getter
@Setter
@AllArgsConstructor
public class DamageIndicatorEvent extends Cancellable {

    private EntityDamageEvent event;

}
