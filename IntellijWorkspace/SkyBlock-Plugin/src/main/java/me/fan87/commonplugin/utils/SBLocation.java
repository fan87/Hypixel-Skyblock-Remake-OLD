package me.fan87.commonplugin.utils;

import org.bukkit.Location;
import org.bukkit.World;

public class SBLocation extends Location {

    public SBLocation(Location location) {
        super(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }
    public SBLocation(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public SBLocation(World world, double x, double y, double z, float yaw, float pitch) {
        super(world, x, y, z, yaw, pitch);
    }

    public boolean isInside(SBLocation first, SBLocation second) {
        return NumberUtils.isBetween(getX(), first.getX(), second.getX()) &&
         NumberUtils.isBetween(getY(), first.getY(), second.getY()) &&
         NumberUtils.isBetween(getZ(), first.getZ(), second.getZ());
    }

    public boolean isInside(double x1, double y1, double z1, double x2, double y2, double z2) {
        return NumberUtils.isBetween(getX(), x1, x2) &&
                NumberUtils.isBetween(getY(), y1, y2) &&
                NumberUtils.isBetween(getZ(), z1, z2);
    }

}
