package me.fan87.commonplugin.features;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class FeaturesManager {

    @Getter
    private final List<SBFeature> features = new ArrayList<>();

    @Getter
    private final SkyBlock skyBlock;

    public FeaturesManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        skyBlock.sendMessage(ChatColor.YELLOW + "Started initializing features");
        for (Class<? extends SBFeature> clazz : SkyBlock.reflections.getSubTypesOf(SBFeature.class)) {
            try {
                SBFeature feature = clazz.newInstance();
                registerFeature(feature);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        skyBlock.sendMessage(ChatColor.GREEN + "Finished initializing features");
    }

    public void registerFeature(SBFeature feature) {
        features.add(feature);
        feature.skyBlock = this.skyBlock;
        feature.setToggled(true);
    }



}
