package com.allenvox;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("hi") && sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("hello! :)");
            return true;
        } else {
            Plugin.logger.info("Command not found");
            return false;
        }
    }
}
