package me.fan87.commonplugin.database;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SBServerData {

    @JsonProperty("dayZero")
    public long dayZero = System.currentTimeMillis();

}
