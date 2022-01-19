package me.fan87.commonplugin.annotations;

/**
 * API annotated with this basically means there will be an updated version of it
 * Before the update, there will be some features that's limited, and you won't be able to use.
 * For example: "SBAbility" is annotated with {@link UnAddonable} and {@link ComingSoon},
 * which basically means that right now you are not allowed to register your own Ability
 * but the feature is planned and coming soon
 */
public @interface ComingSoon {
}
