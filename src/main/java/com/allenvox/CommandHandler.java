package com.allenvox;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
                File file = new File(plugin.getDataFolder(), "coords.json");
                try {
                    JSONParser jsonParser = new JSONParser();
                    Object parsed = jsonParser.parse(new FileReader(file.getPath()));
                    JSONObject jsonObject = (JSONObject) parsed;
                    JSONArray pl = (JSONArray) jsonObject.get(player.getName());
                    for(Object obj : pl) {
                        JSONObject place = (JSONObject) obj;
                        player.sendMessage(ChatColor.GOLD + place.get("name").toString() + ChatColor.GREEN + ":");
                        player.sendMessage(ChatColor.GREEN + "X: " + place.get("x").toString() + ChatColor.GREEN + ", Z: " + place.get("z").toString());
                    }
                }
                catch(ParseException | IOException e) {
                    e.printStackTrace();
                }
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
                File file = new File(plugin.getDataFolder(), "coords.json");
                try {
                    JSONObject place = new JSONObject();
                    place.put("name", name);
                    place.put("x", x);
                    place.put("z", z);

                    JSONParser jsonParser = new JSONParser();
                    Object parsed = jsonParser.parse(new FileReader(file.getPath()));
                    JSONObject jsonObject = (JSONObject) parsed;
                    JSONArray pl = (JSONArray) jsonObject.get(player.getName());
                    pl.add(place);
                }
                catch(ParseException | IOException e) {
                    e.printStackTrace();
                }
                player.sendMessage(ChatColor.GREEN + "Succesfully saved " + ChatColor.YELLOW + "(" + x + "; " + z + ")" + ChatColor.GREEN + " as " + ChatColor.GOLD + name);
                return true;
            }
        } else {
            Plugin.logger.info("Command /"+ command.getName() + " not found");
            return false;
        }
    }
}
