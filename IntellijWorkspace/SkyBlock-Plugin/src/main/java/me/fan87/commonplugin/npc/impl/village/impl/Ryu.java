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
        namespace = "INTRO_RYU",
        name = "Ryu",
        addonName = "default"
)
public class Ryu extends NPCIntroVillager {
    public Ryu(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "There are §a7 Skills §rin SkyBlock!",
                "Farming, Mining, Foraging, Fishing, Combat, Enchanting and Alchemy.",
                "I mean there was 12 but fan87 said he's lazy to make all of them",
                "You can learn all about them in the §aSkill Menu§r, located in your §aSkyBlock Menu§r."
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList(
                "You get rewarded every time you level up a Skill!",
                "Most actions in SkyBlock will reward you Skill EXP"
        );
    }

    // Todo: Finish rogue sword
}
