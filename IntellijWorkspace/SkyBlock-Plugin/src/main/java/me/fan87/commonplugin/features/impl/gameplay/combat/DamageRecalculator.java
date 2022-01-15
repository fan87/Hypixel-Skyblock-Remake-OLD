package me.fan87.commonplugin.features.impl.gameplay.combat;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.DamageCalculationEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.impl.gameplay.rendering.EntityDamageIndicator;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.CalculationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageRecalculator extends SBFeature {


    public DamageRecalculator() {
        super("Damage Recalculator", "Recalculates the damage taken to entity.", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -25)
    public void calculateDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            DamageCalculationEvent e = (DamageCalculationEvent) EventManager.post(new DamageCalculationEvent(1f, 0f,0f));
            SBPlayer player = skyBlock.getPlayersManager().getPlayer((Player) event.getDamager());
            double weaponDamage = 0;
            if (player.getHeldItem() != null) {
                weaponDamage = player.getHeldItem().getDamage();
            }
            double damage = CalculationUtils.getFinalDamage(
                    CalculationUtils.getInitDamage(event.getDamage() * 5, player.getStats().getStrength().getValue(player)),
                    CalculationUtils.getDamageMultiplier(e.getEnchantmentBonus(), player.getSkills().skillCombat.getLevel(), e.getWeaponBonus()),
                    e.getArmorMultiplier(),
                    EntityDamageIndicator.isCritical(event) ? player.getStats().getCritDamage().getValue(player) : 0
            ) / 5f;
            for (EntityDamageEvent.DamageModifier value : EntityDamageEvent.DamageModifier.values()) {
                try {
                    event.setDamage(value, 0);
                } catch (Exception ignored) {}
            }
            event.setDamage(EntityDamageEvent.DamageModifier.BASE, damage);
        }
    }
}
