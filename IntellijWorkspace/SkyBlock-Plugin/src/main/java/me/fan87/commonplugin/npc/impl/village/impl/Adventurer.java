package me.fan87.commonplugin.npc.impl.village.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.gui.impl.npc.GuiShop;
import me.fan87.commonplugin.item.SBItemVector;
import me.fan87.commonplugin.item.init.ItemsVANILLA;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.NPCManager;
import me.fan87.commonplugin.npc.NPCPlayer;
import me.fan87.commonplugin.players.SBPlayer;
import me.fan87.commonplugin.players.tradings.tradable.impl.CoinTradable;
import me.fan87.commonplugin.utils.RotationUtils;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

@AbstractNPC.NPCRegistry(
        world = WorldsManager.WorldType.SKYBLOCK_HUB,
        skin = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwMWQxZWEyNjMwYmEwNjI3N2RhNDNjNzY3YWFiNDE0MWFjNzYxM2FmYjRhZmNlZDE5ZDUzMzJhZmU5ZmFkMyJ9fX0=",
        namespace = "ADVENTURER",
        name = "Adventurer",
        addonName = "default"
)

public class Adventurer extends NPCPlayer {
    public Adventurer(SkyBlock skyBlock) {
        super(skyBlock);
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
    protected void onInteract(SBPlayer player) {
        super.onInteract(player);
        new GuiShop(player, "Adventurer"
                , new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.ROTTEN_FLESH, 1), new CoinTradable(8))
                , new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.BONE, 1), new CoinTradable(8))
                , new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.STRING, 1), new CoinTradable(10))
                , new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.SLIME_BALL, 1), new CoinTradable(14))
                , new GuiShop.ShopItem(new SBItemVector(ItemsVANILLA.SULPHUR, 1), new CoinTradable(10)))
                .open(player.getPlayer());
    }

    @Override
    public List<String> getLore(Player player) {
        return Arrays.asList(ChatColor.WHITE.toString() + "Adventurer", ChatColor.YELLOW.toString() + ChatColor.BOLD + "CLICK");
    }
}

