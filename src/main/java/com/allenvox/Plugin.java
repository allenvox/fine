package com.allenvox;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    }

    public void onDisable() {
        logger.info("Fine plugin disabled");
    }
}