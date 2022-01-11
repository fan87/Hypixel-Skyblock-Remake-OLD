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
        namespace = "INTRO_DUKE",
        name = "Duke",
        addonName = "default"
)
public class Duke extends NPCIntroVillager {
    public Duke(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "Are you new here? As you can see there is a lot to explore!",
                "My advice is to start by visiting the §bFarm§r, or the §bCoal Mine §rboth North of here.",
                "If you do need some wood, the best place to get some is West of the §bVillage§r!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                "I found a few Fairy Souls during my travels, they are usually pretty hard to find!",
                "I would not venture South of the §bVillage§r, it seems like this place was abandoned."
        );
    }

    // Todo: Finish rogue sword
}
