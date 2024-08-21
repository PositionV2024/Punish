package com.clarence.punish;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class Configuration {
     public static File messagesFile = null;
     private static YamlConfiguration messagesFileYamlConfiguration = null;

     public static YamlConfiguration getMessagesFileYamlConfiguration() { return  messagesFileYamlConfiguration; }

    public Configuration(Punish punish) {
        messagesFile = createFile(punish,"Punishments.yml");
        messagesFileYamlConfiguration = yamlConfiguration(messagesFile);
        try {
            messagesFileYamlConfiguration.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public YamlConfiguration yamlConfiguration(File file) {
         return YamlConfiguration.loadConfiguration(file);
    }

    public static void addPlayerUUID(Player target, String reason, BanType punishment_type, int duration, BanDuration durationType, Player Punishedby) {
        List<String> PlayerNamePath = messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getDisplayName() + ".name");
        List<String> ReasonPath = messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".reason");
        List<String> PunishmentTypePath = messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".punishment_type");
        List<String> DurationPath = messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".duration");
        List<String> releaseDatePath = messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".releaseDate");
        List<String> punishByPath =messagesFileYamlConfiguration.getStringList("Punishments" + "." + target.getUniqueId() + ".punishedBy");


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

        messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".name", PlayerNamePath);
        messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".reason", ReasonPath);

        switch (punishment_type) {
            case Kick:
            case Permanent:
                messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                break;
            case Temporary:
                messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".duration", DurationPath);
                messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".releaseDate", releaseDatePath);
                break;
        }
        messagesFileYamlConfiguration.set("Punishments" + "." + target.getUniqueId() + ".punishedBy", punishByPath);

        try {
            messagesFileYamlConfiguration.save(messagesFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
