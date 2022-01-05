package me.fan87.commonplugin.debug;

import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.debug.impl.DebugSkyBlock;

import java.util.ArrayList;
import java.util.List;

public class DebugGuiManager {


    private final List<DebugGui> debugGuis = new ArrayList<>();

    public List<DebugGui> getDebugGuis() {
        return new ArrayList<>(debugGuis);
    }

    public DebugGuiManager(SkyBlock skyBlock) {
        registerGui(new DebugSkyBlock(skyBlock));
    }

    public void registerGui(DebugGui gui) {
//        debugGuis.add(gui);
//        new Thread(() -> {
//            gui.init();
//            gui.run();
//            gui.destroy();
//        }).start();
    }

}
