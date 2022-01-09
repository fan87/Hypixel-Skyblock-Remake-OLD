package me.fan87.commonplugin.features.impl;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.SBPlayerDeathEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.greenrobot.eventbus.Subscribe;

public class DeathProcessor extends SBFeature {

    public DeathProcessor() {
        super("Death Processor", "Changes the death behavior", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -999)
    public void playerDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (((Player) event.getEntity()).getHealth() - event.getDamage() < 1) {
                event.setCancelled(true);
                Location spawnLocation = event.getEntity().getWorld().getSpawnLocation();
                spawnLocation.setYaw(180f);
                event.getEntity().teleport(spawnLocation);
                ((Player) event.getEntity()).setHealth(((Player) event.getEntity()).getMaxHealth());
                SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) event.getEntity()).getPlayer());
                SBPlayerDeathEvent event1 = new SBPlayerDeathEvent(player, player.getCoins() / 2d);
                EventManager.EVENT_BUS.postSticky(event1);
                if (event1.getLoseCoins() > 0) {
                    player.getPlayer().sendMessage(ChatColor.RED + "You died and lost " + NumberUtils.formatNumber(event1.getLoseCoins()) + " coins!");
                }
                player.setCoins(player.getCoins() - event1.getLoseCoins());
            }
        }

    }

}
