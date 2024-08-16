package com.clarence.punish;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Util {
    private final static String pluginPrefix = "[Punish] ";
    private final static String pluginColor = "&8";

    private static int defaultTempBanDuration = 1;

    private static String stringBuilderMessage = null;

    public static int getDefaultTempBanTimeDuration() { return defaultTempBanDuration; }
    private static String getPluginColor() { return pluginColor; }
    public static String getStringBuilderMessage() { return stringBuilderMessage; }
    public static String Color(String message) { return ChatColor.translateAlternateColorCodes('&', getPluginColor() + getPluginPrefix() +  "&f" + message); }
    public static String getPluginPrefix() { return pluginPrefix; }

    public static StringBuilder stringBuilder(String totalLength[], int buildFrom) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = buildFrom; i < totalLength.length; i++) {
            stringBuilder.append(totalLength[i]);
            stringBuilder.append(" ");
            stringBuilderMessage = stringBuilder.toString().strip();
        }
        return stringBuilder;
    }
    private static Calendar createMinCalender(int time) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, time);
        return cal;
    }

    public static void setBan(Player target, String banReason , int duration, String source) {
        Calendar cal = createMinCalender(duration);
        Bukkit.broadcastMessage(Util.Color(target.getDisplayName() + " &7was temporary banned."));
        target.kickPlayer(Util.Color("&7You were temporary banned from this server for " + banReason + "." + " This ban will be lifted in " + duration + " Minutes."));
        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banReason, cal.getTime(), source);
    }
    public static void setBan(Player target, String banReason, String source) {
        Bukkit.broadcastMessage(Util.Color(target.getDisplayName() + " &7was banned."));
        target.kickPlayer(Util.Color("&7You were banned from this server for " + banReason + "."));
        Bukkit.getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banReason, null, source);
    }
}
