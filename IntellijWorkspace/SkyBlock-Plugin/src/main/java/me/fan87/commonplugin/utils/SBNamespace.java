package me.fan87.commonplugin.utils;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class SBNamespace {

    private String addonName;
    private String namespace;

    public String toString() {
        return addonName + "::" + namespace;
    }

}
