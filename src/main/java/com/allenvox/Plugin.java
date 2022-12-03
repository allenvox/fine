package com.allenvox;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin {
  public static Logger logger = Logger.getLogger("fine");
  private CommandHandler commandHandler;

  public void onEnable() {
    logger.info("Fine plugin enabled");
    commandHandler = new CommandHandler();
    getCommand("hi").setExecutor(commandHandler);
  }

  public void onDisable() { logger.info("Fine plugin disabled"); }
}
