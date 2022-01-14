package me.fan87.commonplugin.npc;

import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.addon.exceptions.UnknownAddonError;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.Subscribe;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.SBNamespace;
import net.minecraft.server.v1_8_R3.PacketPlayInUseEntity;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCManager {
    
    private final SkyBlock skyBlock;

    private final List<AbstractNPC<?>> existingNpcList = new ArrayList<>();
    private final Map<SBNamespace, Class<? extends AbstractNPC<?>>> npcList = new HashMap<>();

    public Map<SBNamespace, Class<? extends AbstractNPC<?>>> getNpcList() {
        return new HashMap<>(npcList);
    }

    public NPCManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        EventManager.register(this);

        Reflections reflections = new Reflections();
        for (Class<? extends AbstractNPC> aClass : reflections.getSubTypesOf(AbstractNPC.class)) {
            if (AbstractNPC.getNPCRegistry(aClass) != null) {
                forceRegisterNpc((Class<? extends AbstractNPC<?>>) aClass);
            }
        }
    }

    public void registerNpc(Class<? extends AbstractNPC<?>> npcClass) {
        NPCPlayer.NPCRegistry npcRegistry = AbstractNPC.getNPCRegistry(npcClass);
        if (npcRegistry == null) throw new NullPointerException("NPC does not have legal registry!");
        SBAddon addon = skyBlock.getAddon(npcRegistry.addonName());
        if (addon == null) throw new UnknownAddonError("Addon not found: " + npcRegistry.addonName());
        forceRegisterNpc(npcClass);
    }

    protected void forceRegisterNpc(Class<? extends AbstractNPC<?>> npcClass) {
        NPCPlayer.NPCRegistry npcRegistry = AbstractNPC.getNPCRegistry(npcClass);
        if (npcRegistry == null) throw new NullPointerException("NPC does not have legal registry!");
        SBNamespace key = new SBNamespace(npcRegistry.addonName(), npcRegistry.namespace());
        if (npcList.containsKey(key)) throw new IllegalArgumentException("NPC: " + key + " already registered!");
        npcList.put(key, npcClass);
    }

    protected void addNpc(AbstractNPC<?> npc) {
        existingNpcList.add(npc);
    }


    @Subscribe
    @SneakyThrows
    public void onPacket(PacketPlayReceiveEvent event) {
        Object nmsPacket = event.getNMSPacket().getRawNMSPacket();
        try {
            if (nmsPacket instanceof PacketPlayInUseEntity) {
                PacketPlayInUseEntity packet = ((PacketPlayInUseEntity) nmsPacket);
                SBPlayer player = skyBlock.getPlayersManager().getPlayer(event.getPlayer());
                Field a = PacketPlayInUseEntity.class.getDeclaredField("a");
                a.setAccessible(true);
                int entityId = (int) a.get(packet);
                for (AbstractNPC<?> npc : existingNpcList) {
                    if (entityId == npc.getNpcEntity().getId()) {
                        Location location = player.getPlayer().getLocation();
                        double deltaX = location.getX() - npc.getNpcEntity().locX;
                        double deltaY = location.getY() - npc.getNpcEntity().locY;
                        double deltaZ = location.getZ() - npc.getNpcEntity().locZ;
                        if (Math.sqrt(deltaX*deltaY + deltaY*deltaY + deltaZ*deltaZ) < 10) {
                            if (player.isBuilding()) {
                                player.getWorld().removeNpc(npc);
                            } else {
                                npc.onInteract(player);
                            }
                        }
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe()
    public void onTick(ServerTickEvent event) {
        for (AbstractNPC<?> npc : existingNpcList) {
            if (npc.displayToAll()) {
                for (Player player : npc.asCraftCopy().getWorld().getPlayers()) {
                    if (npc.getViewers().contains(player)) {
                        continue;
                    }
                    Location location = player.getLocation();
                    double deltaX = location.getX() - npc.asCraftCopy().getLocation().getX();
                    double deltaY = location.getY() - npc.asCraftCopy().getLocation().getY();
                    double deltaZ = location.getZ() - npc.asCraftCopy().getLocation().getZ();
                    if (location.getWorld() == npc.asCraftCopy().getWorld() && Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ) < npc.getAppearDistance()) {
                        npc.display(player);
                    }
                }
                for (Player viewer : new ArrayList<>(npc.getViewers())) {
                    Location location = viewer.getLocation();
                    double deltaX = location.getX() - npc.asCraftCopy().getLocation().getX();
                    double deltaY = location.getY() - npc.asCraftCopy().getLocation().getY();
                    double deltaZ = location.getZ() - npc.asCraftCopy().getLocation().getZ();
                    if (Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ) > npc.getAppearDistance() || location.getWorld() != npc.asCraftCopy().getWorld()) {
                        npc.hide(viewer);
                    }
                }
            }
            try {
                npc.onTick();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe()
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (Team team : new ArrayList<>(event.getPlayer().getScoreboard().getTeams())) {
            if (team.getName().startsWith("NPC-")) team.unregister();
        }
        for (AbstractNPC<?> npc : existingNpcList) {
            if (npc.displayToAll()) {
                npc.display(event.getPlayer());
            }
        }
    }

    @Subscribe()
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        for (AbstractNPC<?> npc : existingNpcList) {
            if (npc.displayToAll()) {
                npc.hide(event.getPlayer());
            }
        }
    }


    protected void removeNpc(AbstractNPC<?> npc) {
        existingNpcList.remove(npc);
    }


}
