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
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
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

