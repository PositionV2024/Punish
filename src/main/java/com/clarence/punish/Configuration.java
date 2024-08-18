package com.clarence.punish;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

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

    public static void addPlayerUUID(Player target, String reason, BanType banType) {

         String prefixPath = "Punishments";
        String uuidPath = prefixPath + "." + target.getUniqueId();
        String reasonPath = prefixPath + ".reason";
        String bantypePath = prefixPath + ".punishment_type";

        List<String> PathToUUID = getConfig().getStringList(uuidPath);
        List<String> PathToReason = getConfig().getStringList(reasonPath);
        List<String> PathToBanType = getConfig().getStringList(bantypePath);

        String eachUUID = String.valueOf(target.getUniqueId());

        if (PathToUUID.contains(eachUUID)) {
            System.out.println(Util.getPluginPrefix() + "Couldn't add " + target.getUniqueId() + " to the config.yml. Copies DETECTED.");
            return;
        }

        PathToUUID.add(eachUUID);
        PathToReason.add(reason);
        PathToBanType.add(String.valueOf(banType).toUpperCase());

        punish.getConfig().set(uuidPath, PathToUUID);
        punish.getConfig().set(reasonPath, PathToReason);
        punish.getConfig().set(bantypePath, PathToBanType);

        punish.saveConfig();

        System.out.println(Util.getPluginPrefix() +"Added " + target.getDisplayName() + " (" + target.getUniqueId() + ") " + "to the config.yml file.");
    }
}
