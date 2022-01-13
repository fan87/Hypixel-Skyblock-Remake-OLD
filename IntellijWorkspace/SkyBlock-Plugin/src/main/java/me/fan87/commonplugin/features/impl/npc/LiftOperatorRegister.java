package me.fan87.commonplugin.features.impl.npc;

import me.fan87.commonplugin.areas.SBArea;
import me.fan87.commonplugin.features.SBFeature;
import me.fan87.commonplugin.positionplaceholder.ArmorstandPlaceholderManager;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class LiftOperatorRegister extends SBFeature {
    public final Map<SBArea, Location> points = new HashMap<>();
    public LiftOperatorRegister() {
        super("Lift Operator Register", "Register the Lift", true);
    }

    @Override
    protected void onEnable() {
        skyBlock.getArmorstandPlaceholderManager().registerArmorStandHandler("LIFTPOS", new ArmorstandPlaceholderManager.ArmorStandHandler() {
            @Override
            public boolean onFound(ArmorstandPlaceholderManager.SBArmorStandPlaceHolder armorStandPlaceHolder) {
                for (SBArea area : skyBlock.getAreasManager().getAreas()) {

                    if (area.getNamespace().equals(armorStandPlaceHolder.getValue()[0])) {

                        points.put(area, armorStandPlaceHolder.getLocation());

                    }

                }
                return true;
            }
        });
    }

    @Override
    protected void onDisable() {

    }
}
