package com.clarence.punish;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class Configuration {
     private static File userUUIDFile, messagesFile, materialFile = null;
     private static YamlConfiguration userUUIDYamlConfiguration, messagesConfiguration, materialConfiguration = null;

     public static YamlConfiguration getUserUUIDYamlConfiguration() { return  userUUIDYamlConfiguration; }
     public static YamlConfiguration getMessagesConfiguration() { return  messagesConfiguration; }
     public static YamlConfiguration getMaterialConfiguration() {return materialConfiguration; }

    public Configuration(Punish punish) {
        userUUIDFile = createFile(punish,"Punishments.yml");
        userUUIDYamlConfiguration = YamlConfiguration.loadConfiguration(userUUIDFile);

        messagesFile = createFile(punish,"Messages.yml");
        messagesConfiguration = YamlConfiguration.loadConfiguration(messagesFile);

        materialFile = createFile(punish,"Material.yml");
        materialConfiguration = YamlConfiguration.loadConfiguration(materialFile);


        addDefault(messagesConfiguration, "NO_CONSOLE_SENDER", Errors.getNoConsoleSender());
        addDefault(messagesConfiguration, "NO_PERMISSION", Errors.getNoPermission());
        addDefault(messagesConfiguration, "INVALID_TARGET", Errors.getInvalidTarget());
        addDefault(messagesConfiguration, "PUNISHMENT_REASON", Errors.getPunishmentReason());
        addDefault(messagesConfiguration,"NO_RECORD", Errors.getNoRecord());

        addDefault(materialConfiguration, "defaultInventoryMaterial", InventoryHelper.getDefaultInventoryMaterial().toString());
        addDefault(materialConfiguration, "reasonToBePunishmentMaterial", InventoryHelper.getReasonToBePunishmentMaterial().toString());

        saveConfigurationFile(userUUIDYamlConfiguration, userUUIDFile);
        saveConfigurationFile(messagesConfiguration, messagesFile);
        saveConfigurationFile(materialConfiguration, materialFile);
    }

    public void addDefault(YamlConfiguration yamlConfiguration, String path, String name) {
        yamlConfiguration.addDefault(path, name);
        yamlConfiguration.options().copyDefaults(true);
    }

    public File createFile(Punish punish, String name) {
        File file = new File(punish.getDataFolder(), name);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }

        return file;
    }

    public void saveConfigurationFile(YamlConfiguration yamlConfiguration, File fileName) {
        try {
            yamlConfiguration.save(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
