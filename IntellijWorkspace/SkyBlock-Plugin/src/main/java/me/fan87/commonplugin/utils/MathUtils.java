package me.fan87.commonplugin.utils;

import org.bukkit.Location;

public class MathUtils {

    public static double distanceBetween(Location location, Location location2) {
        double deltaX = location.getX() - location2.getX();
        double deltaY = location.getY() - location2.getY();
        double deltaZ = location.getZ() - location2.getZ();
        return Math.sqrt(deltaX*deltaX + deltaY*deltaY + deltaZ*deltaZ);
    }

}
