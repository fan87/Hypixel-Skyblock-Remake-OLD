package me.fan87.commonplugin.config;

import com.google.gson.annotations.SerializedName;

public class SBConfig {

    @SerializedName("autoSaveTick")
    public int autoSaveTick = 200;

    @SerializedName("noDatabaseSavingMessage")
    public boolean noDatabaseSavingMessage = false;

    @SerializedName("hexServerId")
    public String serverId = "m001";

    @SerializedName("serverIp")
    public String serverIp = "www.hypixel.net";

    @SerializedName("doNotReplacePlaceHolder")
    public boolean doNotReplacePlaceHolder = false;

}
