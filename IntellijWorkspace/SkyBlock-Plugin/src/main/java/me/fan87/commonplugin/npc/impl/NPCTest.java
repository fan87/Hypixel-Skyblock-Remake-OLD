package me.fan87.commonplugin.npc.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.NPCPlayer;
import me.fan87.commonplugin.npc.NPCVillager;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.utils.RotationUtils;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@NPCPlayer.NPCRegistry(
        addonName = "default",
        name = "Test NPC",
        namespace = "testnpc",
        skin = "ewogICJ0aW1lc3RhbXAiIDogMTY0MTkyMjcyNTc5NSwKICAicHJvZmlsZUlkIiA6ICIzMzYwM2NkZDEyNTM0ZDI4OThjMzFlNTkwNjNkZDY3NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJmYW44NyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNWVmNzc3NjAxOGIwMDE5YTkyOTMwMjZkZGU4N2YyNDEzN2M5NDM2OWI3MWFmY2UwNmEzNTU0ZWQxYmUxZDNlIgogICAgfQogIH0KfQ",
        world = WorldsManager.WorldType.SKYBLOCK_HUB
)
public class NPCTest extends NPCVillager {

    public NPCTest(SkyBlock skyBlock) {
        super(skyBlock);
//        setSkin("ewogICJ0aW1lc3RhbXAiIDogMTY0MTkyMjcyNTc5NSwKICAicHJvZmlsZUlkIiA6ICIzMzYwM2NkZDEyNTM0ZDI4OThjMzFlNTkwNjNkZDY3NSIsCiAgInByb2ZpbGVOYW1lIiA6ICJmYW44NyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNWVmNzc3NjAxOGIwMDE5YTkyOTMwMjZkZGU4N2YyNDEzN2M5NDM2OWI3MWFmY2UwNmEzNTU0ZWQxYmUxZDNlIgogICAgfQogIH0KfQ==", "cMrmcJVjMggjIahRHtZEbpvWJiLvmmVJVZ1zZ+KOD5y1leNtGKpTM5s13UywKaVavncH0zfQLJy64mv1RlZZ9YJ/29wNoyrFpZXYgX2xwM3fsz2d1Zg/9sz4YAFxPKiDDs+kxT1QivecauepNvWTQiCwtDD7qoHBZOuI03//rIFskLTPtOF9c85XD9FDLLRN4kb/ms9TV2bnAjpdKEEfkdZM9pscntNUF73j/wGn4on0gb+tL2X/JFzCCTuvgjczNpVhO6LBZQJ2wmfF9oiiKDhZ3gMxmJNYdubn23Kj1wNyinQqk1dbzjDDElKrR1EYxVPM2miygjv8zjOGWy9ywR0kDyY2hUsJr/tPcvfFOcRNlNbqoxp6abeus0E1rIKuVCoCnp9E1QH5g9cJpZw8HGkI04oIWMt7pQPxLPGcOcaCth4Am5PKmgvnXASx4vXB9zmam8hQjPmighJiNrCDoNg7M5U+/tutqhIZdIahbQvNIUAx5/1IzNdwYmyaQoLOLeGPXMJJJO+QP3rfUNVgqcP+5QlnpZeFxmzsxymVk9hgDzr1Rcl3CEMqJXttYWxADaTmIBZK2CJO/qjmKbY1YL6I2r15NnDAOz+IsrXHsRnnUgTRwRO/IFvS1w1f/2keEZSHSUd3ObOExExLWF9ZeGBEXmpxY3zLdnzMmqf9v0Q=");
    }

    @Override
    protected void onCreate() {

    }

    @Override
    protected void onInteract(SBPlayer player) {
        destroy();
    }

    @Override
    protected void onTick() {
        Entity nearest = null;
        double lastDist = 0;
        for (Entity nearbyEntity : asCraftCopy().getWorld().getNearbyEntities(asCraftCopy().getLocation(), 10, 5, 10)) {
            if (nearest == null) {
                nearest = nearbyEntity;
                double deltaX = nearest.getLocation().getX() - asCraftCopy().getLocation().getX();
                double deltaY = nearest.getLocation().getY() - asCraftCopy().getLocation().getY();
                double deltaZ = nearest.getLocation().getZ() - asCraftCopy().getLocation().getZ();
                lastDist = Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
                continue;
            }
            double deltaX = nearest.getLocation().getX() - asCraftCopy().getLocation().getX();
            double deltaY = nearest.getLocation().getY() - asCraftCopy().getLocation().getY();
            double deltaZ = nearest.getLocation().getZ() - asCraftCopy().getLocation().getZ();
            double sqrt = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
            if (sqrt < lastDist) {
                nearest = nearbyEntity;
                lastDist = sqrt;
            }
        }
        if (nearest != null) {
            Location location = nearest.getLocation();
            float[] face = RotationUtils.tryFace(getNpcEntity().locX - location.getX(), getNpcEntity().locY - location.getY(), getNpcEntity().locZ - location.getZ());
            rotate(face[0], face[1]);
        }
    }

    @Override
    public List<String> getLore(Player player) {
        return Arrays.asList(ChatColor.GREEN + "Test NPC", ChatColor.YELLOW + "Click to remove");
    }
}
