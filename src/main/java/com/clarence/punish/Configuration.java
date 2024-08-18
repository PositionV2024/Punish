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

    public static void addPlayerUUID(Player target, String reason, BanType punishment_type, int duration, BanDuration punishmentDuration) {

         List<String> PathToPlayerUUID = getConfig().getStringList("Punishments" + ".");
        List<String> PathToPlayername = getConfig().getStringList("Punishments" + "." + target.getDisplayName() + ".name");
        List<String> PathToReason = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".reason");
        List<String> PathToPunishmentType = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".punishment_type");
        List<String> PathToDuration = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".duration");
        List<String> PathTopunishmentDuration = getConfig().getStringList("Punishments" + "." + target.getUniqueId() + ".durationType");

        switch (punishment_type) {
            case Kick:
                PathToPlayerUUID.add(target.getUniqueId().toString());
                PathToPlayername.add(target.getDisplayName());
                PathToReason.add(reason);
                PathToPunishmentType.add(punishment_type.toString());
                PathToDuration.add(String.valueOf(duration));

                getConfig().set("Punishments" + "." + target.getUniqueId(), PathToPlayerUUID);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".name", PathToPlayername);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".reason", PathToReason);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PathToPunishmentType);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".durationType", PathToDuration);
                break;
            case Temporary:
                PathToPlayerUUID.add(target.getUniqueId().toString());
                PathToPlayername.add(target.getDisplayName());
                PathToReason.add(reason);
                PathToPunishmentType.add(punishment_type.toString());
                PathToDuration.add(String.valueOf(duration));
                PathTopunishmentDuration.add(String.valueOf(punishmentDuration));

                getConfig().set("Punishments" + "." + target.getUniqueId(), PathToPlayerUUID);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".name", PathToPlayername);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".reason", PathToReason);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".punishment_type", PathToPunishmentType);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".duration", PathToDuration);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".durationType", PathTopunishmentDuration);
                break;
            case Permanent:
                PathToPlayerUUID.add(target.getUniqueId().toString());
                PathToPlayername.add(target.getDisplayName());
                PathToReason.add(reason);
                PathToPunishmentType.add(punishment_type.toString());
                PathToDuration.add(String.valueOf(duration));

                getConfig().set("Punishments" + "." + target.getUniqueId(), PathToPlayerUUID);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".name", PathToPlayername);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".reason", PathToReason);
                getConfig().set("Punishments" + "." + target.getUniqueId() + ".durationType", PathToPunishmentType);
                break;
        }

        punish.saveConfig();

        System.out.println(Util.getPluginPrefix() +"Added " + target.getDisplayName() + " (" + target.getUniqueId() + ") " + "to the config.yml file.");
    }
}
