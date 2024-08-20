package com.clarence.punish;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Calendar;
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

    public static void addPlayerUUID(Player target, String reason, BanType punishment_type, int duration, BanDuration durationType, Player Punishedby) {

         List<String> MainPath = getConfig().getStringList("Punishments" + ".");
        List<String> PlayerNamePath = getConfig().getStringList("Punishments" + "." + target.getDisplayName() + ".name");
        List<String> ReasonPath = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".reason");
        List<String> PunishmentTypePath = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".punishment_type");
        List<String> DurationPath = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".duration");
        List<String> releaseDatePath = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".releaseDate");
        List<String> punishByPath = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".punishedBy");

        if (getConfig().contains("Punishments" + "." + target.getUniqueId() + ".reason")) {
            System.out.println(Util.getPluginPrefix() + "You are already in the punishments list.");
            return;
        }

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

        MainPath.add(target.getUniqueId().toString());
        PlayerNamePath.add(target.getDisplayName());
        ReasonPath.add(reason);
        PunishmentTypePath.add(punishment_type.toString());
        DurationPath.add(duration + " (" + durationType + ")");
        releaseDatePath.add(cal.getTime().toString());
        punishByPath.add(Punishedby.getDisplayName() + " (" + Punishedby.getUniqueId() + ")");

        getConfig().set("Punishments" + "." + target.getUniqueId(), MainPath);
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".name", PlayerNamePath);
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".reason", ReasonPath);

        switch (punishment_type) {
            case Kick:
            case Permanent:
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                break;
            case Temporary:
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PunishmentTypePath);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".duration", DurationPath);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".releaseDate", releaseDatePath);
                break;
        }
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishedBy", punishByPath);

        punish.saveConfig();

        System.out.println(Util.getPluginPrefix() +"Added " + target.getDisplayName() + " (" + target.getUniqueId() + ") " + "to the config.yml file.");
    }
}
