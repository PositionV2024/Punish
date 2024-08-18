package com.clarence.punish;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class Configuration {

     public static Punish punish;

     public static FileConfiguration getConfig() {
         return Configuration.punish.getConfig();
     }

    public Configuration(Punish punish) {
        Configuration.punish = punish;

        if (punish == null){
            System.out.println(Util.getPluginPrefix() + "has failed to hook on to " + punish.getName());
            return;
        }

        System.out.println(Util.getPluginPrefix() + "has successfully hook on to " + punish.getName());
        punish.getConfig().options().copyDefaults();
        punish.saveDefaultConfig();
    }

    public static void addPlayerUUID(List<String> configList, Player target, String reason, BanType banType) {
        UUID targetUUID = target.getUniqueId();

        String eachUUID = String.valueOf(targetUUID);

        configList = getConfig().getStringList("userUUID/name");

        if (configList.contains(eachUUID)) {
            System.out.println(Util.getPluginPrefix() + "Couldn't add " + targetUUID + " to the config.yml. Copies DETECTED.");
            return;
        }

        configList.add(eachUUID);
        punish.getConfig().set("userUUID/name", configList);
        punish.saveConfig();
        System.out.println(Util.getPluginPrefix() +"Added " + target.getDisplayName() + " (" + targetUUID + ") " + "to the config.yml file.");
    }
}
