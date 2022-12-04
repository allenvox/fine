package com.allenvox;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
  public static Logger logger = Logger.getLogger("fine");

  public void onEnable() {
    logger.info("Fine plugin enabled");
    getServer().getPluginManager().registerEvents(new EventListener(), this);
    getCommand("hi").setExecutor(new CommandHandler());
  }

  public void onDisable() {
    logger.info("Fine plugin disabled");
  }
}