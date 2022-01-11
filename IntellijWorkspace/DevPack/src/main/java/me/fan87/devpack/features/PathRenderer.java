package me.fan87.devpack.features;

import lombok.SneakyThrows;
import me.fan87.commonplugin.events.impl.ServerTickEvent;
import me.fan87.commonplugin.features.SBFeature;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathEntity;
import net.minecraft.server.v1_8_R3.PathPoint;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.greenrobot.eventbus.Subscribe;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;
import xyz.xenondevs.particle.data.color.RegularColor;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PathRenderer extends SBFeature {
    public static List<UUID> pathViewers = new ArrayList<>();

    public PathRenderer() {
        super("Path Renderer", "Renders the mob's path", false);
    }

    @Override
    protected void onEnable() {

    }

    @Override
    protected void onDisable() {

    }

    int ticks = 0;
    @Subscribe
    @SneakyThrows
    public void onTick(ServerTickEvent event) {
        ticks++;
        if (ticks % 1 == 0) {
            for (World world : Bukkit.getWorlds()) {
                for (Entity e : world.getEntities()) {
                    if (e instanceof CraftLivingEntity) {
                        CraftLivingEntity craftLivingEntity = (CraftLivingEntity) e;
                        if (craftLivingEntity.getHandle() instanceof EntityInsentient) {
                            EntityInsentient entity = ((EntityInsentient) craftLivingEntity.getHandle());
                            if (!entity.getNavigation().m()) {
                                for (UUID pathViewer : pathViewers) {
                                    Player player = Bukkit.getPlayer(pathViewer);
                                    if (player != null) {
                                        PathEntity j = entity.getNavigation().j();
                                        Field a = PathEntity.class.getDeclaredField("a");
                                        a.setAccessible(true);
                                        PathPoint[] pathPoints = (PathPoint[]) a.get(j);
                                        for (PathPoint pathPoint : pathPoints) {
                                            CraftPlayer p = (CraftPlayer) player;
                                            Location location = new Location(world, pathPoint.a + 0.5, pathPoint.b + 0.5, pathPoint.c + 0.5);
                                            Color color = new Color(entity.getId()*100);
                                            new ParticleBuilder(ParticleEffect.REDSTONE, location)
                                                    .setParticleData(new RegularColor(color.getRed(),color.getGreen(),color.getBlue()))
                                                    .setSpeed(20f)
                                                    .display(player);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}
