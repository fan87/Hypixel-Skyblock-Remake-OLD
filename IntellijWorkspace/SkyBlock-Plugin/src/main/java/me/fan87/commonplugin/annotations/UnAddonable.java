package me.fan87.commonplugin.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * API annotated with this basically means that they are not expendable
 * And you won't be able to create your own version with Addon API
 * If it's coming soon, it will be annotated with {@link ComingSoon}
 */
@Retention(RetentionPolicy.SOURCE)
public @interface UnAddonable {



}
