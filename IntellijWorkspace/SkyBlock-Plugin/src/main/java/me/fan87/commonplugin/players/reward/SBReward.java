package me.fan87.commonplugin.players.reward;

import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.LoreUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class SBReward {

    @Getter
    @Setter
    private boolean shown = true;

    public List<String> toLore() {
        List<String> strings = LoreUtils.splitLoreForLine(toString());
        List<String> out = new ArrayList<>();
        for (String string : strings) {
            if (out.isEmpty()) out.add(string); else {
                out.add(" " + string);
            }
        }
        return out;
    }
    public abstract String toString();
    public abstract void trigger(SBPlayer player);

}
