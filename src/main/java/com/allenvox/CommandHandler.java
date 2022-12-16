package com.allenvox;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    private Plugin plugin;

    public CommandHandler(Plugin instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("coords") && sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length != 3) {
                player.sendMessage(ChatColor.GREEN + "Saved coordinates:");
                return true;
            } else {
                int x, z;
                try {
                    x = Integer.parseInt(args[0]);
                    z = Integer.parseInt(args[1]);
                }
                catch(NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "X and Z values must be numeric!");
                    return false;
                }
                String name = args[2];
                File path = plugin.getDataFolder();

                player.sendMessage(ChatColor.GREEN + "Succesfully saved " + ChatColor.YELLOW + "(" + x + "; " + z + ")" + ChatColor.GREEN + " as " + ChatColor.GOLD + name);
                return true;
            }
        } else {
            Plugin.logger.info("Command /"+ command.getName() + " not found");
            return false;
        }
    }
}
