package me.fan87.commonplugin.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
@Setter
public class SBMaterial {

    Material material;
    SBCustomItem item;
    ItemType type;


    public enum ItemType {
        VANILLA,
        UNKNOWN,
        CUSTOM;
    }

}
