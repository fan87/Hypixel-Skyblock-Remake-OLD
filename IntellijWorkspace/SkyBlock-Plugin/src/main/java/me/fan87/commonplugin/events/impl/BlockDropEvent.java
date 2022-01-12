package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class BlockDropEvent extends Cancellable {

    private SBPlayer player;
    @Setter
    private List<ItemStack> drops = new ArrayList<>();
    private BlockBreakEvent blockBreakEvent;
    private Random random;
    private boolean countAsCollection;

}
