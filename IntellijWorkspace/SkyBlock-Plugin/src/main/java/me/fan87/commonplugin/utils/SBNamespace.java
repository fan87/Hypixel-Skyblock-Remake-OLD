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
    public static SBNamespace fromString(String in) {
        SBNamespace namespace = new SBNamespace("", "");
        String[] split = in.split("::");
        if (split.length == 1) {
            namespace.addonName = "default";
            namespace.namespace = split[0];
            return namespace;
        }
        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                namespace.addonName = split[i];
                continue;
            }
            namespace.namespace += split[i] + "::";
        }
        namespace.namespace = namespace.getNamespace().substring(0, namespace.namespace.length() - 2);
        return namespace;
    }

}
