package com.clarence.punish;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Configuration {
     private static File userUUIDFile, messagesFile, materialFile = null;
     private static YamlConfiguration userUUIDYamlConfiguration, messagesConfiguration, materialConfiguration = null;
     private static List<String> materials, configurationMaterials = null;
     private static int[] inventorySlot = new int[]{
             13, 19, 25, 21, 22, 23, 34
     };

     public static List<String> getMaterials() {return  materials; }
    public static List<String> getConfigurationMaterials() {return  configurationMaterials; }
    public static int[] getInventorySlot() { return inventorySlot; }
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

        materials = Arrays.asList(
                "defaultInventoryMaterial",
                "reasonToBePunishmentMaterial",
                "playerNameItemMaterial",
                "getPlayerUUIDMaterial",
                "typesOfPunishmentMaterial",
                "getKickItemMaterial",
                "getTemporaryBanMaterial",
                "getBanMaterial"
        );

        configurationMaterials = Arrays.asList(
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
                System.out.println(Util.getPluginPrefix() + "Could not create " + name + " file.");
            }
        }

        return file;
    }

    public void saveConfigurationFile(YamlConfiguration yamlConfiguration, File fileName) {
        try {
            yamlConfiguration.save(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(Util.getPluginPrefix() + "could not save " + fileName + ".");
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
