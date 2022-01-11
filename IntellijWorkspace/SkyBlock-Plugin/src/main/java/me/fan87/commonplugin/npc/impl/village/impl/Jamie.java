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
        namespace = "INTRO_JAMIE",
        name = "Jamie",
        addonName = "default"
)
public class Jamie extends NPCIntroVillager {
    public Jamie(SkyBlock skyBlock) {
        super(skyBlock);
    }

    @Override
    protected List<String> getDialogues() {
        return Arrays.asList(
                "You might have noticed that you have a Mana bar!",
                "Some items have mysterious properties, called Abilities.",
                "Abilities use your Mana as a resource. Here, take this Rogue Sword. I don't need it!"
        );
    }

    @Override
    protected List<String> getSecondTalkDialogue() {
        return Arrays.asList("Right click with the Rogue Sword to use its Ability and become temporarily faster!", "You can use Mana Potions to go over your Mana limit!");
    }

    // Todo: Finish rogue sword
}
