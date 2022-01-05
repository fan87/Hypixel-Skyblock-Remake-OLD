package me.fan87.commonplugin.addon;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class SBAddon {

    private final String name;
    private final String namespace;
    private final JavaPlugin plugin;

}
