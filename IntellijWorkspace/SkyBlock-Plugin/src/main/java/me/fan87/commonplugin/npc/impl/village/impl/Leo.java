package me.fan87.commonplugin.npc.impl.village.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.impl.village.NPCIntroVillager;
import me.fan87.commonplugin.world.WorldsManager;

import java.util.Arrays;
import java.util.List;

import static org.bukkit.ChatColor.*;
import static org.bukkit.ChatColor.RESET;

@AbstractNPC.NPCRegistry(
        world = WorldsManager.WorldType.SKYBLOCK_HUB,
        skin = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwMWQxZWEyNjMwYmEwNjI3N2RhNDNjNzY3YWFiNDE0MWFjNzYxM2FmYjRhZmNlZDE5ZDUzMzJhZmU5ZmFkMyJ9fX0=",
        namespace = "INTRO_LEO",
        name = "Leo",
        addonName = "default"
)
public class Leo extends NPCIntroVillager {
    public Leo(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList();
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                "You can unlock " + GREEN  + "Leaflet Armor" + WHITE + " by progressing through your " + GREEN + "Oak Log Collection" + WHITE + ".",
                String.format("There is a %sForest %swest of the %sVillage %swhere you can gather Oak Logs.", AQUA, RESET, AQUA, RESET),
                String.format("To check your Collection progress and rewards, open the %sCollection Menu %sin your %sSkyBlock Menu%s.", GREEN, RESET, GREEN, RESET)
        );
    }

    // Todo: Finish rogue sword
}
