package me.fan87.commonplugin.features.impl.gameplay;

import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.SBPlayerDeathEvent;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.NumberUtils;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import me.fan87.commonplugin.events.Subscribe;

import java.util.HashMap;
import java.util.Map;

public class DeathProcessor extends SBFeature {

    public DeathProcessor() {
        super("Death Processor", "Changes the death behavior", false);
    }

    public Map<Player, Map<Long, EntityDamageByEntityEvent>> damages = new HashMap<>();

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    @Subscribe(priority = -999)
    public void playerDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            if (((Player) event.getEntity()).getHealth() - event.getDamage() < 1 || event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.setCancelled(true);
                Location spawnLocation = event.getEntity().getWorld().getSpawnLocation();
                spawnLocation.setYaw(180f);
                event.getEntity().teleport(spawnLocation);
                ((Player) event.getEntity()).setHealth(((Player) event.getEntity()).getMaxHealth());
                SBPlayer player = skyBlock.getPlayersManager().getPlayer(((Player) event.getEntity()).getPlayer());
                player.getPlayer().setFireTicks(0);
                player.getPlayer().setFallDistance(0);
                for (Player p : event.getEntity().getWorld().getPlayers()) {
                    p.sendMessage(ChatColor.RED + " ☠ " + getDeathDamage(event));
                }
                SBPlayerDeathEvent event1 = new SBPlayerDeathEvent(player, player.getPurseCoins() / 2d);
                EventManager.post(event1);
                if (!event1.isCancelled()) {
                    if (player.getMana() < (player.getStats().getIntelligence().getValue(player)+100)/2) {
                        player.getPlayer().sendMessage(ChatColor.RED + "You died and lost " + NumberUtils.formatNumber(event1.getLoseCoins()) + " coins!");
                        player.setPurseCoins(player.getPurseCoins() - event1.getLoseCoins());
                    } else {
                        player.getPlayer().sendMessage(ChatColor.RED + "You Died!");
                        player.setMana((player.getStats().getIntelligence().getValue(player)+100)/2);
                    }
                }
                ((Player) event.getEntity()).playSound(event.getEntity().getLocation(), Sound.HURT_FLESH, 1f, 0.9f);
            }
        }

    }

    public String getDeathDamage(EntityDamageEvent event) {
        assert event.getEntity() instanceof CraftPlayer;
        IChatBaseComponent b = ((CraftPlayer) event.getEntity()).getHandle().combatTracker.b();
        StringBuilder out = new StringBuilder();
        for (IChatBaseComponent iChatBaseComponent : b) {
            if (Bukkit.getPlayer(iChatBaseComponent.getText()) != null) {
                out.append(ChatColor.RESET).append(Bukkit.getPlayer(iChatBaseComponent.getText()).getDisplayName());
                continue;
            }
            out.append(ChatColor.RESET).append(ChatColor.GRAY).append(iChatBaseComponent.getText());
        }
        return out.toString();
    }


}
