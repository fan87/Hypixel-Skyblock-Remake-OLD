package me.fan87.commonplugin.utils;

public class RotationUtils {

    public static float[] tryFace(double rx, double ry, double rz) {
        float yaw = (float) (Math.toDegrees(Math.atan2(rz, rx)) + 90);
        float pitch = (float) Math.toDegrees(Math.atan(
                ry / Math.sqrt(rx*rx + rz*rz)
        ));

        return new float[] {yaw, pitch};
    }

}
