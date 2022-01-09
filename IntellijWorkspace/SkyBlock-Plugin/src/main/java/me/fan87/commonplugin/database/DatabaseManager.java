package me.fan87.commonplugin.database;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.SneakyThrows;
import me.fan87.commonplugin.SkyBlock;
import me.fan87.commonplugin.players.SBPlayer;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;

import java.io.File;

public class DatabaseManager {

    @Getter
    private final SkyBlock skyBlock;
    private Configuration config;
    @Getter
    private MongoClient client;
    @Getter
    private DB db;
    @Getter
    private MongoDatabase database;
    @Getter
    private Jongo jongo;
    @Getter
    private SBServerData serverData;


    @SneakyThrows
    public DatabaseManager(SkyBlock skyBlock) {
        this.skyBlock = skyBlock;
        try {
            skyBlock.sendMessage(ChatColor.YELLOW + "Initializing database manager...");
            YamlConfiguration config = new YamlConfiguration();
            config.load(getConfigFile());
            this.config = config;
            if (!config.contains("url")) config.set("url", "mongodb://localhost:27017");
            if (!config.contains("database")) config.set("database", "SkyBlock");
            config.save(getConfigFile());
            this.client = new MongoClient(new MongoClientURI(config.getString("url")));
            this.db = this.client.getDB(config.getString("database"));
            this.database = this.client.getDatabase(config.getString("database"));
            Gson gson = new Gson();
            this.jongo = new Jongo(db, new JacksonMapper.Builder()
                    .setVisibilityChecker(new VisibilityChecker.Std(JsonAutoDetect.Visibility.NONE, JsonAutoDetect.Visibility.NONE, JsonAutoDetect.Visibility.NONE, JsonAutoDetect.Visibility.NONE, JsonAutoDetect.Visibility.NONE))
                    .build());
            MongoCollection server = getCollection("server");
            if (server.count() != 1) {
                server.remove();
                serverData = new SBServerData();
                server.insert(serverData);
            } else {
                serverData = server.findOne().as(SBServerData.class);
            }

            skyBlock.sendMessage(ChatColor.GREEN + "Initialized database manager!");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to initialize the database manager! Server is unsafe as long as player data are incorrect. Is database on? Please check database.yml in plugins/" + skyBlock.getName() + " to change the IP and stuff");
            skyBlock.getServer().shutdown();
        }
    }

    @SneakyThrows
    public File getConfigFile() {
        File dataFolder = skyBlock.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File configFile = new File(dataFolder, "database.yml");
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        return configFile;
    }

    public MongoCollection getCollection(String name) {
        if (!db.collectionExists(name)) {
            database.createCollection(name);
        }
        return jongo.getCollection(name);
    }

    public void saveAll() {
        for (SBPlayer loadedPlayer : skyBlock.getPlayersManager().getLoadedPlayers()) {
            loadedPlayer.save();
        }
    }

}
