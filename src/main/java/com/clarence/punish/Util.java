package com.clarence.punish;

import org.bukkit.ChatColor;

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

}
