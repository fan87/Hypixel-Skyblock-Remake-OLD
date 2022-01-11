package me.fan87.commonplugin.utils;

import lombok.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Vec3d {

    private double x;
    private double y;
    private double z;

    @SneakyThrows
    public byte[] toBytesArray() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream outputStream = new DataOutputStream(out);
        outputStream.writeDouble(x);
        outputStream.writeDouble(y);
        outputStream.writeDouble(z);
        return out.toByteArray();
    }

    @SneakyThrows
    public static Vec3d fromByteArray(byte[] data) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(data));
        return new Vec3d(inputStream.readDouble(), inputStream.readDouble(), inputStream.readDouble());
    }

    public static Vec3d fromString(String input) {
        if (input == null) return new Vec3d(0, 0, 0);
        if (input.split(", ").length != 3) return new Vec3d(0, 0, 0);
        try {
            return new Vec3d(Double.parseDouble(input.split(", ")[0]), Double.parseDouble(input.split(", ")[1]), Double.parseDouble(input.split(", ")[2]));
        } catch (Exception e) {
        }
        return new Vec3d(0, 0, 0);
    }

    public String toString() {
        return x + ", " + y + ", " + z;
    }

}
