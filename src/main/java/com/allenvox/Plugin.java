package com.allenvox;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Plugin extends JavaPlugin {
    public static Logger logger = Logger.getLogger("fine");
    public static Gson gson;
    public static HashMap<UUID, HashMap<String, HashMap<Integer, Integer>>> coordinates;
    
    public void onEnable() {
        logger.info("Fine plugin enabled");
        getServer().getPluginManager().registerEvents(new EventListener(), this);
        getCommand("coords").setExecutor(new CommandHandler(this));
        gson = new GsonBuilder().setPrettyPrinting().create();
        coordinates = new HashMap<>();
        loadCoordinates();
    }

    public void onDisable() {
        logger.info("Fine plugin disabled");
    }

    private void loadCoordinates() {
        try {
            File file = new File(getDataFolder(), "coords.json");
            if (file.exists()) {
                FileReader reader = new FileReader(file);
                coordinates = gson.fromJson(reader, new TypeToken<HashMap<String, HashMap<Integer, Integer>>>() {}.getType());
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}