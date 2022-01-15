package me.fan87.commonplugin.npc.impl.bank;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.gui.Gui;
import me.fan87.commonplugin.gui.impl.bank.GuiBank;
import me.fan87.commonplugin.npc.NPCManager;
import me.fan87.commonplugin.npc.NPCPlayer;
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
        name = "Banker",
        namespace = "BANKER",
        skin = "eyJ0aW1lc3RhbXAiOjE1NTA2ODA4Mjg5MjMsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZjFlOGY5ZjVjZWE3YmFmNTZkOWUwNzkxMDU3YTdiMjNlNzkzNTZlNzY4M2VkM2Y0NzYwZWFhNmZjNWRjNGIxIn19fQ==",
        world = WorldsManager.WorldType.SKYBLOCK_HUB
)

public class NPCBanker extends NPCPlayer {
    @Override
    protected void onInteract(SBPlayer player) {
        new GuiBank(player).open(player.getPlayer());
    }

    protected void onTick() {
        Entity nearest = null;
        double lastDist = 0;
        for (Entity nearbyEntity : asCraftCopy().getWorld().getNearbyEntities(asCraftCopy().getLocation(), 5, 5, 5)) {
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
        return Arrays.asList(
                ChatColor.WHITE + "Banker",
                ChatColor.YELLOW.toString() + ChatColor.BOLD + "CLICK"
        );
    }

    public NPCBanker(SkyBlock skyBlock) {
        super(skyBlock);

        setSkin("eyJ0aW1lc3RhbXAiOjE1NTA2ODA4Mjg5MjMsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zZjFlOGY5ZjVjZWE3YmFmNTZkOWUwNzkxMDU3YTdiMjNlNzkzNTZlNzY4M2VkM2Y0NzYwZWFhNmZjNWRjNGIxIn19fQ==", "SkhUNSUjtfjFXHhfKO/Wsr0KYV96DzBjBlnzHbyvzHrY/xtHypc6qM8KB2TDPhNGlT3gNdjAyruf3rRaIeXZ9mpN1WdidPL4nYmGIDZRyxdMoEFuK20vHCg95gdg5sjVQyJmYjLzjAtOqBeZHfHiax8jTmuZjUEq94WiSzO5TkPNDwT9yu2hF51U4kvJKNIsdTsn6Y9Kkefx+mVSpd7UcsggmJ6uTEoP9aR9DeUwvaRA++1Ee5UyCURVFdIkGZrN52Ch63fbk9Gfr1XLThm6TYnUaIGatfrklW42KCkKhTuBNUeApAHiTd4lAApQJdqwRSMU4Z/L4THz0Kp64aHWOzqeY4ieW7PWxAS1f9grNRmM4wwlAKQEoyYW6YPpOhYCvHyxh9KlIix4g36sPj1xinmFuPKJMWwFSfMUZNQ/6D6QCejZcoY88ZL2bT3Q70jAl0vIqeS72dtlTjO33alTnkUIpxL7VWnRQSMWH1Q/LpcnLUkXTeJw07gX7C6oOH7nqmL6PTTrV+I5bZdgBYi9PDVj75iUBpWviODVIfQBr/Mzsbvv9KoDOttFjnXVX1l526whTbwnPyewq4rokqAuD5WXx22Rx6wAzQ/Z4SSNyV6oNm9RZWrcYIyvYXoj7sSgb3UsA9Qn+bmAoBMax0e43+Hy8QAn+vyzlqVgYTYruZM=");
    }
}
