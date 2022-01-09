package me.fan87.commonplugin.areas;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.utils.NumberUtils;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Biome;

@Getter
@AllArgsConstructor
public class SBArea {

    private final String name;
    private final ChatColor color;

    @JsonProperty("checkType")
    private AreaCheckType checkType;
    @JsonProperty("biomeName")
    private Biome biomeName;
    @JsonProperty("worldType")
    private WorldsManager.WorldType worldType;
    @JsonProperty("fromX")
    private int fromX;
    @JsonProperty("fromY")
    private int fromY;
    @JsonProperty("fromZ")
    private int fromZ;
    @JsonProperty("toX")
    private int toX;
    @JsonProperty("toY")
    private int toY;
    @JsonProperty("toZ")
    private int toZ;

    public SBArea(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public boolean match(SkyBlock skyBlock, Location location) {
        if (worldType == null || skyBlock.getWorldsManager().getWorld(location.getWorld().getName()).getWorldType() == worldType) {
            if (checkType == AreaCheckType.BIOME) {
                return location.getWorld().getBiome(location.getBlockX(), location.getBlockZ()).name().equals(biomeName);
            }
            if (checkType == AreaCheckType.POSITION) {
                return NumberUtils.isBetween(location.getX(), fromX, toX) &&
                       NumberUtils.isBetween(location.getY(), fromY, toY) &&
                       NumberUtils.isBetween(location.getZ(), fromZ, toZ);
            }
        }
        return false;
    }

    public enum AreaCheckType {
        BIOME,
        POSITION;
    }



}
