package me.fan87.commonplugin.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.annotations.Unit;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ability.AbilityActiveEvent;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBMap;
import org.bukkit.ChatColor;

import java.util.Map;

@Getter
@AllArgsConstructor
public final class SBAbility {

    private final AbilityType abilityType;
    private final String description;
    private final String name;
    private final int manaCost;
    @Unit(unitType = "Second")
    private final int coolDown;


    private final Map<SBPlayer, Long> canUseTime = new SBMap<SBPlayer, Long>() {
        @Override
        public Long getDefaultValue() {
            return 0L;
        }
    };

    public boolean canPlayerUse(SBPlayer player) {
        return System.currentTimeMillis() > canUseTime.get(player);
    }

    public void setCoolDown(SBPlayer player, long ms) {
        canUseTime.put(player, System.currentTimeMillis() + ms);
    }


    @AllArgsConstructor
    @Getter
    public static class AbilityType {
        public static final AbilityType PASSIVE = new AbilityType(ChatColor.GOLD + "Ability: %s");
        public static final AbilityType RIGHT_CLICK = new AbilityType(ChatColor.GOLD + "Ability: %s " + ChatColor.YELLOW + ChatColor.BOLD + "RIGHT CLICK");
        public static final AbilityType SNEAK = new AbilityType(ChatColor.GOLD + "Ability: %s " + ChatColor.YELLOW + ChatColor.BOLD + "SNEAK");

        private final String displayName;

        public void onActive(SBPlayer player, SBItemStack itemStack, SBAbility ability) {
            EventManager.post(new AbilityActiveEvent(player, itemStack, ability));
        }

    }

}
