package me.fan87.commonplugin.config;

import com.google.gson.annotations.SerializedName;

public class SBConfig {

    @SerializedName("autoSaveTick")
    public int autoSaveTick = 200;

    @SerializedName("noDatabaseSavingMessage")
    public boolean noDatabaseSavingMessage = false;

}
