package me.fan87.commonplugin.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.fan87.commonplugin.entity.ISBEntity;

@Getter
@AllArgsConstructor
public class PostEntityUpdateEvent {

    private final ISBEntity entity;
    private ISBEntity.EntityUpdateResult result;

}
