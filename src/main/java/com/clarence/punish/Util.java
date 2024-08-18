package com.clarence.punish;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class Util {
    private final static String pluginPrefix = "[Punish] ";
    private final static String pluginColor = "&8";

    private static String stringBuilderMessage = null;

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
    private static Calendar createCalender(int time, BanDuration banDuration) {
        Calendar cal = Calendar.getInstance();
        switch (banDuration) {
            case MINUTES:
                cal.add(Calendar.MINUTE, time);
                break;
            case HOURS:
                cal.add(Calendar.HOUR, time);
                break;
            case DAYS:
                cal.add(Calendar.DAY_OF_WEEK, time);
                break;
        }
        return cal;
    }
    public static void setKick(Player target, String kickReason) {
        Bukkit.broadcastMessage(Util.Color(target.getDisplayName() + " &7was kicked."));
        target.kickPlayer(Util.Color("&7You were kicked from this server for " + kickReason + "."));
        Configuration.addPlayerUUID(target, kickReason, BanType.Kick, 0, BanDuration.DAYS);
    }
    public static void setBan(BanType banType, BanDuration banDuration, Player target, String banReason , int duration) {
        switch (banType) {
            case Temporary:
                Calendar cal = createCalender(duration, banDuration);
                Bukkit.broadcastMessage(Util.Color(target.getDisplayName() + " &7was temporary banned."));

                switch (banDuration) {
                    case MINUTES:
                        target.kickPlayer(Util.Color("&7You were temporary banned from this server for " + banReason + "." + " This ban will be lifted in &b&8" + duration + " Minutes."));
                        break;
                    case HOURS:
                        target.kickPlayer(Util.Color("&7You were temporary banned from this server for " + banReason + "." + " This ban will be lifted in &b&8" + duration + " Hours."));
                        break;
                    case DAYS:
                        target.kickPlayer(Util.Color("&7You were temporary banned from this server for " + banReason + "." + " This ban will be lifted in &b&8" + duration + " Days."));
                        break;
                }
                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banReason, cal.getTime(), null);
                break;
            case Permanent:
                Bukkit.broadcastMessage(Util.Color(target.getDisplayName() + " &7was banned."));
                target.kickPlayer(Util.Color("&7You were permanently banned from this server for " + banReason + "."));
                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getDisplayName(), banReason, null, null);
                break;
        }
        Configuration.addPlayerUUID(target, banReason, banType, duration, banDuration);
    }
}
