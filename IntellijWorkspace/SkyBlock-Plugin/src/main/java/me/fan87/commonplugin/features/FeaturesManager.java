package me.fan87.commonplugin.features;

import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;

import java.util.ArrayList;
import java.util.List;

public class FeaturesManager {

    private final List<SBFeature> features = new ArrayList<>();

    @Getter
    private final SkyBlock skyBlock;

    public FeaturesManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;

        for (Class<? extends SBFeature> clazz : SkyBlock.reflections.getSubTypesOf(SBFeature.class)) {
            try {
                SBFeature feature = clazz.newInstance();
                features.add(feature);
                feature.skyBlock = this.skyBlock;
                feature.setToggled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
