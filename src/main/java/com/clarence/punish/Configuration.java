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

    public static void addPlayerUUID(Player target, String reason, BanType punishment_type, int duration, BanDuration durationType, Player whoKickedTarget) {

         List<String> PathToPlayerUUID = getConfig().getStringList("Punishments" + ".");
        List<String> PathToPlayername = getConfig().getStringList("Punishments" + "." + target.getDisplayName() + ".name");
        List<String> PathToReason = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".reason");
        List<String> PathToPunishmentType = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".punishment_type");
        List<Integer> PathToDuration = getConfig().getIntegerList("Punishments" + "." + target.getUniqueId() + ".duration");
        List<String> PathTopunishmentDuration = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".durationType");
        List<String> PathTounpunishmentDate = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".releaseDate");
        List<String> PathToPunishBy = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".punishedBy");

        if (getConfig().contains("Punishments" + "." + target.getUniqueId())) {
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

        PathToPlayerUUID.add(target.getUniqueId().toString());
        PathToPlayername.add(target.getDisplayName());
        PathToReason.add(reason);
        PathToPunishmentType.add(punishment_type.toString());
        PathToDuration.add(duration);
        PathTounpunishmentDate.add(cal.getTime().toString());
        PathToPunishBy.add(whoKickedTarget.getDisplayName());
        PathTopunishmentDuration.add(durationType.toString());

        getConfig().set("Punishments" + "." + target.getUniqueId(), PathToPlayerUUID);
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".name", PathToPlayername);
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".reason", PathToReason);

        switch (punishment_type) {
            case Kick:
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PathToPunishmentType);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".durationType", PathToDuration);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".releaseDate", PathTounpunishmentDate);
                break;
            case Temporary:
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PathToPunishmentType);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".duration", PathToDuration);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".durationType", PathTopunishmentDuration);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".releaseDate", PathTounpunishmentDate);
                break;
            case Permanent:
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PathToPunishmentType);
                break;
        }
        getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishedBy", PathToPunishBy);

        punish.saveConfig();

        System.out.println(Util.getPluginPrefix() +"Added " + target.getDisplayName() + " (" + target.getUniqueId() + ") " + "to the config.yml file.");
    }
}
