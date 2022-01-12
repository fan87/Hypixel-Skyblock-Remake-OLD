package me.fan87.commonplugin.utils;

import java.util.HashMap;

public class SBMap<K, V> extends HashMap<K, V> {

    public SBMap() {

    }

    public V getDefaultValue() {
        return null;
    }

    @Override
    public V get(Object key) {
        V v = super.get(key);
        return (v == null ? getDefaultValue() : v);
    }

}
