package me.fan87.commonplugin.npc.impl.village.impl;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.npc.AbstractNPC;
import me.fan87.commonplugin.npc.impl.village.NPCIntroVillager;
import me.fan87.commonplugin.world.WorldsManager;

import java.util.Arrays;
import java.util.List;

@AbstractNPC.NPCRegistry(
        world = WorldsManager.WorldType.SKYBLOCK_HUB,
        skin = "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTQwMWQxZWEyNjMwYmEwNjI3N2RhNDNjNzY3YWFiNDE0MWFjNzYxM2FmYjRhZmNlZDE5ZDUzMzJhZmU5ZmFkMyJ9fX0=",
        namespace = "INTRO_VEX",
        name = "Vex",
        addonName = "default"
)
public class Vex extends NPCIntroVillager {
    public Vex(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "You can shift click any player to trade with them!",
                "Once both players are ready to trade, click on §aAccept trade§r!",
                "Make sure you don't give away all your belongings!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                "You can disable Player Trading in your §bSkyBlock Settings§f!",
                "Your settings can be found in the SkyBlock Menu."
        );
    }

    // Todo: Finish rogue sword
}
