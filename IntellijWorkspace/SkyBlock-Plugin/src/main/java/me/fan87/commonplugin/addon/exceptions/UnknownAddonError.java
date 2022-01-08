package me.fan87.commonplugin.addon.exceptions;

public class UnknownAddonError extends Error {

    public UnknownAddonError(String addonName) {
        super("Addon: " + addonName + " is not defined!");
    }
}