package com.allenvox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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
        if(command.getName().equalsIgnoreCase("coords")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(args.length == 1) {
                    String name = args[0];
                    int x = player.getLocation().getBlockX();
                    int z = player.getLocation().getBlockZ();
                    HashMap<Integer, Integer> XZ = new HashMap<>();
                    XZ.put(x,z);
                    HashMap<String, HashMap<Integer, Integer>> c = new HashMap<>();
                    c.put(name, XZ);
                    if(!Plugin.coordinates.containsKey(player.getUniqueId())) {
                        Plugin.coordinates.put(player.getUniqueId(), c);
                    } else {
                        player.sendMessage("You have already got coordinates saved with this name.");
                    }
                    saveCoords();
                    player.sendMessage("Success! Coordinates have been saved as '" + name + "'.");
                    return true;
                } else {
                    if(Plugin.coordinates.containsKey(player.getUniqueId())) {
                        for(String name : Plugin.coordinates.get(player.getUniqueId()).keySet()) {
                            HashMap<Integer, Integer> c = Plugin.coordinates.get(player.getUniqueId()).get(name);
                            player.sendMessage(name + ": " + c.toString());
                        }
                    } else {
                        player.sendMessage("You have not saved any coordinates yet.");
                    }
                    return true;
                }
            } else {
                sender.sendMessage("This command can only be executed by a player.");
                return true;
            }
        } else {
            return true;
        }
    }

    private void saveCoords() {
        try {
            File file = new File(plugin.getDataFolder(), "coords.json");
            if(!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            Plugin.gson.toJson(Plugin.coordinates, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}