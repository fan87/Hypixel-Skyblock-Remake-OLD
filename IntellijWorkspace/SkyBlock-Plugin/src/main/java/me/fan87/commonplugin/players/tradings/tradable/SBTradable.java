package me.fan87.commonplugin.players.tradings.tradable;

import me.fan87.commonplugin.players.SBPlayer;

public abstract class SBTradable {

    public abstract String toString();
    public abstract boolean doesPlayerHave(SBPlayer player, float multiplier);
    public abstract boolean takeItemFrom(SBPlayer player, float multiplier);

    public abstract String getNotEnoughMessage();

}
