package me.fan87.commonplugin;

import io.github.retrooper.packetevents.PacketEvents;
import lombok.Getter;
import me.fan87.commonplugin.addon.SBAddon;
import me.fan87.commonplugin.commands.CommandsManager;
import me.fan87.commonplugin.config.ConfigsManager;
import me.fan87.commonplugin.database.DatabaseManager;
import me.fan87.commonplugin.debug.DebugGuiManager;
import me.fan87.commonplugin.events.EventManager;
import me.fan87.commonplugin.events.impl.ServerShutdownEvent;
import me.fan87.commonplugin.features.FeaturesManager;
import me.fan87.commonplugin.item.init.SBItems;
import me.fan87.commonplugin.players.PlayersManager;
import me.fan87.commonplugin.recipes.RecipesManager;
import me.fan87.commonplugin.world.WorldsManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SkyBlock extends JavaPlugin {

    @Getter
    private DatabaseManager databaseManager;
    @Getter
    private ConfigsManager configsManager;
    @Getter
    private EventManager eventManager;
    @Getter
    private PlayersManager playersManager;
    @Getter
    private FeaturesManager featuresManager;
    @Getter
    private DebugGuiManager debugGuiManager;
    @Getter
    private CommandsManager commandsManager;
    @Getter
    private RecipesManager recipesManager;
    @Getter
    private WorldsManager worldsManager;
    @Getter
    private final List<SBAddon> addons = new ArrayList<>();


    private static SkyBlock instance;


    public static final Reflections reflections = new Reflections();
    private static final boolean DEBUG_MODE = true;


    @Override
    public void onEnable() {
        instance = this;
        addons.clear();
        if (DEBUG_MODE) {
            debugGuiManager = new DebugGuiManager(this);
        }

        PacketEvents.create(this);
        PacketEvents.get().init();

        byte[] decode = Base64.getDecoder().decode("dHRmdGZmZnR0dHR0dHR0dHRmZmZmZmZmdHRmdHQxdHR0dHR0dDExMXR0dHQxMTExMTExdHR0dDF0dDExMXR0dHQxMTExMTF0dHR0MXR0dHR0dHR0dHR0dHR0dDExMTExMTExMQp0dGZ0dHR0dHR0dHR0dHR0dHR0ZkxMTGZ0ZmZmZmZmZmZmZnR0dHQxMTF0dDExMTExdHRmZmZmZnR0dHQxMTExMTExMTExMTExMTExdHR0dHR0ZnR0dHR0dHR0dDExMTExMXQxCnR0dHR0dHR0dHR0dHR0dHRmZmZmTGZmdGZmZmZmZmZmTExmZnR0dHR0dGZ0dDExMXR0ZmZmZmZmdHR0dHR0dHQxMTExMTF0dHR0dHRmZmZmdHRmZmZ0dHR0dHR0MTExMTExdHQKdHR0dHR0dHR0dHR0dHR0dGZmZkxmdHRmZmZmZmZMTGZmZmZ0dHR0dHR0dHR0dDF0ZmZmZmZmZmZmZnR0dHR0dDExMTExMXRmZmZmZmZmZmZ0dHRmZmZmdHR0dDExMTExdHR0MQp0dHR0dHR0dHR0dHRmZkxmdHRmZmZ0dGZmZmZmZmZmZnR0ZmZ0dHR0dHQxdDF0dDExdGZmZmZmZnR0dHR0dDExMXQxMTExMTF0dGZmZmZMTGZmdHRmZmZmdHQxMTExMTF0dHR0CnR0dHR0dHR0dHRmZkxMTExmdHR0dGZmTExMZnR0dHRmdGZMZnR0dHR0dHRmZmZmdHR0dGZmZmZ0dHR0ZmZ0dDExdDExMXR0MTExdHRmZmZMZmZ0dGZmdHR0dHR0MTExMXR0dHQKdHR0dHR0dHRmZkxMTExMTExmZnR0ZkxMZmZ0dGZmZmZ0ZkxmdHR0dHR0ZmZ0dHQxMTExMXR0dHRmZmZmZmZmdHQxMTExdGZ0MTExMTF0dGZmZmZ0dHQxMXRmZnR0MTF0dHR0dAp0dHR0dHR0ZmZmTExMTExMTExmZnRmZnR0dHRmZkxmZmZ0ZmZ0dHR0dGZ0MWk7Ozo6Ojo7aWl0ZmZmZmZmZmZmdHQxMTF0ZnQxdHR0dDF0dGZmTGZ0dHR0ZmZmZmZ0dHR0dHR0CnR0dHR0dHRmZmZMTExMTExMTGZ0dHR0ZmZmdGZmZmZmdGZ0dHR0dHRmZjE6LCwsLCwsLCw6Oml0ZmZmZmZmZmZ0MTExMTExMTF0dHR0dHR0dHR0dDF0ZmZmZmZmZmZmZmZ0dHQKdHR0ZmZ0dGZmTExMTExMTExMZnR0ZmZ0ZmZmZmZmZmZMTExmdHR0dHR0Ozo6LDo6Ojs7Ozs6OjF0ZmZmZnRmZnQxMTExMXR0dDF0dHQxdGZ0dHQxMXR0ZmZmZmZmZmZMZnR0dAp0ZmZmZmZmZmZMTExMTExMZmZmdGZMZmZmdHRmTExmZkxMTGZ0dHR0dGk7OztpaWkxMTExMTs6aTExdHRmZmZ0MTExMTExdGZmdDExdDExdHQxdHR0dHRmZmZMTExMTExmdHRmCnRmZmZmZnR0dHRmTExMZmZ0dHR0ZmZmZmZ0dExMTExmTExMZnR0dGZ0aTo7OztpaWlpaWlpaTtpdDExMXR0MTExdDExMTF0ZmZ0dGZmdHQxMXR0ZnR0dGZmZkxMTExMTGZmZmYKdHR0dGZ0dHR0ZmZmZmZ0dHR0dHR0dHR0dHR0ZkxMTGZmTExmdHR0dHQxOzs7Ozs7aWlpaWlpOzF0ZnQxMXR0MTExMTExMXRmZnRmZmZmdHR0dHR0dHR0dHRmTExmZmZ0dGZmZgp0ZmZmZmZmZmZmZnR0dGZmZmZmZmZmZnR0ZmZ0dExMZmZmTGZ0dHR0dDExaTs7OztpaWlpaWlpMXR0MTF0ZmZ0dDExMTExdHR0MWZmZnR0dDF0dHR0dHR0dHRmZmZmdHR0dHR0CnR0ZmZmZmZmZmZmdHR0ZmZmZmZmZmZmdHRmZmZ0ZmZ0dGZ0dHR0dGZmMWlpOzs7O2lpaTFpaWkxMTF0dDF0dHR0dDExMTF0MTExZmZ0dGZmdHRmZmZmZkxmZnR0dHRmZmZmZmYKdHRmZmZmZmZmZnR0dHRmZmZmZmZmZnR0dHR0dHR0dHRmZmZ0dHR0dGZ0MWlpOzs7aWkxaWlpMTExdGZmdDExdHR0MTExMTExdDF0dDF0ZmZ0MXR0ZmZmZkxMZnR0dExMTExMTAp0dHRmZmZmZmZmZnR0ZmZmZmZmZmZ0dHR0dHR0dDExZmZmZnR0dHQxdDExMWlpaWk7aWlpaWkxMXR0ZmZmdDExMTExMTExdHR0dHR0dDF0dDExdHRmZmZmTGZmdHR0ZkxMTExmCnR0dHR0dHRmZmZmdHRmZmZ0dHR0dHR0dGZmdHR0MTF0ZmZ0dHR0dHR0MTF0dGlpOzs7aWlpaTExaTFmZmZ0dDExMTExMTF0dHR0MTExdHR0dDExdHR0dGZmZmZmdGZmTExMZmYKdGZ0dHR0dHR0dHR0dHR0dHR0dHR0dGZmZmZ0dHR0MTF0ZmZ0dHR0dHQxMTF0MWk7OztpaWlpMWksLDo7aTExMTF0MTExMXR0dDExMXQxdGZmdHQxMTExMXR0ZmZ0ZmZmZmZ0dAp0dHR0ZmZmZnR0dHR0dHRmZmZmdHR0ZmZmZnR0ZmZ0dHR0ZnQxMTF0dDExMTExaTs7O2lpaWkxOywsLi4uLiw6Ojo7aTExdHQxMTF0dDExdGZmZnR0dHR0dHR0MXR0dHR0dHR0CnQxMXR0ZmZmZmZ0dHRmZmZmZmZ0dHR0ZmZmdHRmZnR0dHRmdDExMTExMTExMTE7Ozs7Ozs7aXQ7LC4uLi4uLi4uLi4uLDppdDExMXR0dDF0ZmZmdHR0dHRmZnQxMXR0ZmZmZmYKdHQxdHRmZmZmdHQxdHRmZmZmdDExMXRmZnR0ZnR0dHR0dHQxMTExdDFpOzo7MTFpMWk7OzF0MTosLC4uLi4uLi4uLi4uLixpdHQxMXR0MXRmZnR0MXRmZmZmZmZ0MXRmZkxMZgp0ZnR0dHRmdHR0dHQxMXR0dHQxdHR0MTF0dHR0dHRmdHQxMTExMTFpOiwuLjoxMTExaWlpMTE7LC4uLi4uLi4uLi4uLi4uLjt0dDExMXQxMXR0dHR0dHR0ZmZ0dHQxMWZmTExmCnRmZnR0dHR0dHRmZnR0dHR0dHRmZmZ0dDExMXR0ZmZmdHQxMWk7LC4sLi4uLGlpaWk7Ozs7OiwsLi4uLi4uLi4uLi4uLi4uOjF0dHQxMTExdHRmZmZ0dHR0ZnQxdHQxdHRmZnQKdHR0dHR0dHR0dHR0dHR0MTF0dHR0dHR0dHR0ZmZmZmZmdDE6LC4uLi4uLi4uLi46Ojo6OjosLCwuLi4uLi4uLi4uLi4uLi4sMXR0dHR0MTF0dGZmZmZmdHR0dGZmZnR0dHR0dAp0ZmZmdHR0dGZmZmZmdHQxMXR0dHR0dHR0dHRmZmZmZmZ0MTouLi4uLi4uLi4uLjo7Ojo7Oi4uLi4uLi4uLi4uLi4uLi4uLiwxdHR0dHR0MXR0ZmZmZmZ0dHR0dHR0dGZ0dHRmCnRmZmZ0dHR0ZmZmZmZmdDF0dGZ0dHRmZnR0dHR0ZmZmdDExOi4uLi4uLi4uLi4uLDo6Ojo6Li4uLi4uLi4uLi4uLi4uLi4uLDF0dHRmdDExdGZmZmZmZmZ0dGZmZmZmZmZ0dGYKdGZmZnR0dHRmZmZmZmYxMXR0dHR0dHR0dHR0dHR0dHR0dDE7Li4uLi4uLi4uLi4sOjo6OiwuLi4uLi4uLi4uLi4uLi4uLi4uO3QxdDExMTF0ZmZmZmZMZnR0ZmZmZmZmZnR0Zgp0ZmZmdHR0dGZmdHR0dDExdHR0dHR0dHQxdGZmdHR0dHR0MTouLi4uLi4uLi4uLiw6Ojo6LC4uLi4uLiAuLi4uLi4uLi4uLi4sMTExMXR0MTFmZmZmZkxmdHR0ZmZ0dGZmdHRmCnR0dHR0dHR0dHR0dHR0MTF0dHR0dHR0dDF0ZmZmdHR0ZnQxOi4uLi4uLi4uLi4uLDo6OiwsLi4uLi4uLi4uLi4uLi4uLi4uLi5pdDF0dHQxMXRmZmZmZmZ0dHRmZnR0ZmZ0dHQKdHR0dHQxdHR0dHR0dHQxMXR0dHR0dHR0dHRmZnR0dHR0dHQ6Li4uLi4uLi4uLi4sOjosLC4uLi4uLi4uLi4uLi4uLi4uLi4uLjoxMTF0dDExdHR0dHR0dHQxdHR0dHR0dHR0dAp0dHR0dDF0dHR0dHR0dDExdGZ0dHR0dGZ0dHR0dHR0dHR0MTssOjs6Li4uLi4uLiw6LCwsLi4uLi4uLi4uLi4uLi4uLi4uLi4sLDExMTF0MTF0dHR0dHR0MTF0dHR0dHRmdHR0CjF0dHR0MXR0ZmZmdHR0MTF0dGZmZmZmZnR0dHRmZmZmdDExaWkxaTouLi4uLi4uLDosLCwuLi4uLi4uLi4uLi4uLi4uLi4uLi4sMXR0MTExMXR0dHR0dHQxMTF0dHR0dGZ0dHQKMXR0dDExdHR0dHR0dHQxMXR0dHR0dHR0dHR0dHR0dHR0dGk7Ozs7Oi4uLi4uLi4sOiwsLC4uLi4uLi4gLi4uLi4uLi4uLi4uLixpdHQxMTExdHR0dDExdDExMXR0dHR0dHQxdAoxMTExMTF0dHR0dHR0dDExMTExMTExdHR0dHR0dHR0dHQxaTs7Ozs6Li4uLi4uLiw6LCwsLi4uLi4uLi4uLi4uLi4uLi4uLi4uLml0dDExMTExMTExMTExMTExMTExMTExMTExCjExMTExMTExMTExMTExMTExMTExMTF0dHR0dHR0dHR0dDE7Ozs7OzouLi4uLi4gLDosLCwsLi4uLi4uLi4uLi4uLi4uLi4uLi4uaXR0MTF0MTExMTExMTExMTExMTExMTExMTEKMTExMTExMTExMTExMTExMTExMTExMXR0dHR0dHR0dHQxOzosOjo7LC4uLi4uLiAsOi4uLiwuLi4uLi4uLi4uLi4uLi4uLi4uLi5pdHR0dHQxMTExMTExMTExMTExMTExMTExMQoxMTExMTExMTExMTExMTExMTExMTExMTF0dHR0dHR0dDFpLC4uLi4uICAuLi4uIC46Li4uLi4uLi4uLi4uLi4uLi4uLi4uLi4uLGkxdHR0dDExMTExMTExMTExMTExMTExMTExCjExMTExMTExMTExMTExMTExMTExMTExMTF0MTF0MTExMTE7LC4uLi4sLC4uLi4gLDo6OiwsLC4uLi4uLi4uLi4uLi4uLi4uLi4sMTF0dHR0dDExdDExMTExMTF0MTExMTExMTEKMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTFpaWlpaWkuIC4uLiAuOjo6Ojo6LC4uLi4uLi4uLi4uLi4uLi4uLiwxMXQxdHR0MTExdDExMTExdHR0dHR0MTExMQoxMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExOi4sLi4uLi46Ojo6OjosLi4uLi4uLi4uLi4uLi4uLi4uOjExMXR0dHR0dDF0dHR0MTF0dHR0dHQxMTExCjExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTExMTE6OjosLi4uLDo6Ojo7OjosLi4sLC4uLi4uLi4uLi4uLi46MTF0dDF0dHR0dHR0dHR0dHR0dHR0dDExMTEKfn5+fn5+IEh5cGl4ZWwgU2t5QmxvY2sgUmVjcmVhdGUgIC0gIE1hZGUgYnkgZmFuODcgfn5+fn5+");
        System.out.println(new String(decode));

        this.recipesManager = new RecipesManager(this);
        EventManager.EVENT_BUS.register(new SBItems(this));
        this.configsManager = new ConfigsManager(this);
        this.databaseManager = new DatabaseManager(this);
        this.eventManager = new EventManager(this);
        this.worldsManager = new WorldsManager(this);
        this.playersManager = new PlayersManager(this);
        this.featuresManager = new FeaturesManager(this);
        this.commandsManager = new CommandsManager(this);
    }

    @Override
    public void onDisable() {
        EventManager.EVENT_BUS.post(new ServerShutdownEvent());
        PacketEvents.get().terminate();
    }

    public void sendMessage(String message) {
        getServer().getConsoleSender().sendMessage(message);
    }


    public static SkyBlock registerAddon(String name, String namespace, JavaPlugin javaPlugin) {
        if (namespace.equals("default")) {
            throw new IllegalArgumentException("You are not allowed to use \"default\" as addon namespace");
        }
        for (SBAddon addon : instance.addons) {
            if (addon.getNamespace().equals(namespace)) {
                throw new AddonAlreadyRegisteredError("Addon " + namespace + " is already registered! Please contact the addon developer to change addon name.");
            }
        }
        instance.addons.add(new SBAddon(name, namespace, javaPlugin));
        return instance;
    }

    public SBAddon getAddon(String namespace) {
        for (SBAddon addon : addons) {
            if (addon.getNamespace().equals(namespace)) return addon;
        }
        return null;
    }

    public static class AddonAlreadyRegisteredError extends Error {
        public AddonAlreadyRegisteredError(String message) {
            super(message);
        }
    }

}
