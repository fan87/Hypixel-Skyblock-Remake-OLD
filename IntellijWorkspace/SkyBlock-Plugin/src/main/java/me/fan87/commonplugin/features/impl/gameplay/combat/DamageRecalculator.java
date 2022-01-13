package me.fan87.commonplugin.features.impl.gameplay.combat;

import me.fan87.commonplugin.enchantment.SBEnchantment;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.DamageCalculationEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.features.impl.gameplay.rendering.EntityDamageIndicator;
import me.fan87.commonplugin.item.SBItemStack;
import me.fan87.commonplugin.item.SBMaterial;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.CalculationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DamageRecalculator extends SBFeature {


    public DamageRecalculator(String name, String description, boolean beta) {
        super(name, description, beta);
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
            DamageCalculationEvent e = (DamageCalculationEvent) EventManager.post(new DamageCalculationEvent(0, 0));
            SBPlayer player = skyBlock.getPlayersManager().getPlayer((Player) event.getDamager());
            ItemStack itemInHand = player.getPlayer().getItemInHand();
            SBItemStack itemStack = null;
            double damageMultiplier = 1;
            double damage = 5;
            if (itemInHand != null) {
                itemStack = new SBItemStack(itemInHand);
                if (itemStack.getType().getType() == SBMaterial.ItemType.CUSTOM) {
                    damage = itemStack.getType().getItem().getDamage(itemStack);
                    if (damage < 5) damage = 5;
                    for (SBEnchantment enchantment : itemStack.getEnchantments().keySet()) {
                        damageMultiplier *= enchantment.getDamageMultiplier(itemStack, itemStack.getEnchantmentLevel(enchantment), player);
                    }
                }
            }
            event.setDamage(CalculationUtils.getFinalDamage(
                    CalculationUtils.getInitDamage(event.getDamage(), player.getStats().getStrength().getValue(player)),
                    CalculationUtils.getDamageMultiplier(damageMultiplier, player.getSkills().skillCombat.getLevel(), e.getWeaponBonus()),
                    e.getArmorBonus(),
                    EntityDamageIndicator.isCritical(event)?player.getStats().getCritDamage().getValue(player)/100f:0
            ));
        }

    }
}
