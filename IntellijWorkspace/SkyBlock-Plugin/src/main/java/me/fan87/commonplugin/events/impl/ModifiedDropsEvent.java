package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.fan87.commonplugin.events.Cancellable;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ModifiedDropsEvent extends Cancellable {

    private List<ItemStack> drops = new ArrayList<>();
    private BlockBreakEvent blockBreakEvent;

}
