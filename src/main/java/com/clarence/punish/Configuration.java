package com.clarence.punish;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Configuration {
     private static File userUUIDFile, messagesFile, materialFile = null;
     private static FileConfiguration userUUIDYamlConfiguration, messagesConfiguration, materialConfiguration = null;
     public static File getUserUUIDFile() {return userUUIDFile;}
     public static File getMessagesFile() {return messagesFile;}
     public static File getMaterialFile() {return materialFile;}

     public static FileConfiguration getUserUUIDYamlConfiguration() { return userUUIDYamlConfiguration; }
     public static FileConfiguration getMessagesConfiguration() { return  messagesConfiguration; }
     public static FileConfiguration getMaterialConfiguration() {return materialConfiguration; }

    public Configuration(Punish punish) {
        userUUIDFile = createFile(punish,"Punishments.yml");
        userUUIDYamlConfiguration = YamlConfiguration.loadConfiguration(userUUIDFile);

        messagesFile = createFile(punish,"Messages.yml");
        messagesConfiguration = YamlConfiguration.loadConfiguration(messagesFile);

        materialFile = createFile(punish,"Material.yml");
        materialConfiguration = YamlConfiguration.loadConfiguration(materialFile);

        List<String> configurationMessages = Arrays.asList(
                "NO_CONSOLE_SENDER",
                "NO_PERMISSION",
                "INVALID_TARGET",
                "PUNISHMENT_REASON",
                "NO_RECORD"
        );
        List<String> getErrorMessages = Arrays.asList(
                Errors.getNoConsoleSender(), Errors.getNoPermission(),
                Errors.getInvalidTarget(),
                Errors.getPunishmentReason(),
                Errors.getNoRecord()
        );

        List<String> materials = Arrays.asList(
                "defaultInventoryMaterial",
                "reasonToBePunishmentMaterial",
                "playerNameItemMaterial",
                "getPlayerUUIDMaterial",
                "typesOfPunishmentMaterial",
                "getKickItemMaterial",
                "getTemporaryBanMaterial",
                "getBanMaterial"
        );

        List<String> configurationMaterials = Arrays.asList(
                InventoryHelper.getDefaultInventoryMaterial().toString(),
                InventoryHelper.getReasonToBePunishmentMaterial().toString(),
                InventoryHelper.getPlayerNameItemMaterial().toString(),
                InventoryHelper.getPlayerUUIDMaterial().toString(),
                InventoryHelper.getTypesOfPunishmentsMaterial().toString(),
                InventoryHelper.getKickItemMaterial().toString(),
                InventoryHelper.getTemporaryBanMaterial().toString(),
                InventoryHelper.getBanMaterial().toString()
        );


        for (int i = 0; i < configurationMessages.size(); i++) {
            addDefault(messagesConfiguration, configurationMessages.get(i), getErrorMessages.get(i));
        }

        for (int i = 0; i < materials.size(); i++) {
            addDefault(materialConfiguration, materials.get(i), configurationMaterials.get(i));
        }

        saveConfigurationFile(userUUIDYamlConfiguration, userUUIDFile);
        saveConfigurationFile(messagesConfiguration, messagesFile);
        saveConfigurationFile(materialConfiguration, materialFile);
    }

    public void addDefault(FileConfiguration yamlConfiguration, String path, String name) {
        yamlConfiguration.addDefault(path, name);
        yamlConfiguration.options().copyDefaults(true);
    }
    public File createFile(Punish punish, String name) {
        File file = new File(punish.getDataFolder(), name);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(Util.getPluginPrefix() + "Could not create " + name + " file.");
            }
        }
        return file;
    }

    public static void saveConfigurationFile(FileConfiguration yamlConfiguration, File fileName) {
        try {
            yamlConfiguration.save(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Util.getPluginPrefix() + "could not save " + fileName + ".");
        }
    }
    public static void reloadConfigurationFiles() {
        messagesConfiguration = YamlConfiguration.loadConfiguration(messagesFile);
        materialConfiguration = YamlConfiguration.loadConfiguration(materialFile);
    }
    public static void reloadMessageConfigurationFile(Player player) {
        messagesConfiguration = YamlConfiguration.loadConfiguration(messagesFile);
        player.sendMessage(Util.Color("&7Successfully reloaded " + messagesFile.getName()));
    }
    public static void reloadMaterialConfigurationFile(Player player) {
        materialConfiguration = YamlConfiguration.loadConfiguration(materialFile);
        player.sendMessage(Util.Color("&7Successfully reloaded " + materialFile.getName()));
    }
    public static void addPlayerUUID(Player target, String reason, BanType punishment_type, int duration, BanDuration durationType, Player Punishedby) {
        List<String> PlayerNamePath = userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getDisplayName() + ".name");
        List<String> ReasonPath = userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".reason");
        List<String> PunishmentTypePath = userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".punishment_type");
        List<String> DurationPath = userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".duration");
        List<String> releaseDatePath = userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".releaseDate");
        List<String> punishByPath =userUUIDYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".punishedBy");


        Calendar cal = Calendar.getInstance();

        switch (durationType) {
            case MINUTES:
                cal.add(Calendar.MINUTE, duration);
                break;
            case HOURS:
                cal.add(Calendar.HOUR, duration);
                break;
            case DAYS:
                cal.add(Calendar.DAY_OF_WEEK, duration);
                break;
        }

        PlayerNamePath.add(target.getDisplayName());
        ReasonPath.add(reason);
        PunishmentTypePath.add(punishment_type.toString());
        DurationPath.add(duration + " (" + durationType + ")");
        releaseDatePath.add(cal.getTime().toString());
        punishByPath.add(Punishedby.getDisplayName());

        userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".name", PlayerNamePath);
        userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".reason", ReasonPath);

        switch (punishment_type) {
            case Kick:
            case Permanent:
                userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                break;
            case Temporary:
                userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".duration", DurationPath);
                userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".releaseDate", releaseDatePath);
                break;
        }
        userUUIDYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishedBy", punishByPath);

        try {
            userUUIDYamlConfiguration.save(userUUIDFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
