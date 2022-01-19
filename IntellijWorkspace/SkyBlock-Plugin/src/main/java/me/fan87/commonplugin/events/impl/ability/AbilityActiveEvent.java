package me.fan87.commonplugin.events.impl.ability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.item.SBAbility;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.players.SBPlayer;

@AllArgsConstructor
@Getter
public class AbilityActiveEvent {

    private final SBPlayer player;
    private final SBItemStack itemStack;
    private final SBAbility ability;


}
