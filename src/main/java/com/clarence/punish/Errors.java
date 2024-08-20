package com.clarence.punish;

public class Errors {
    private final static String errorColor = "&4";

    private static String NO_CONSOLE_SENDER = "You can't access this command through CONSOLE";
    private static String NO_PERMISSION = getErrorColor() + "You do not have permission to use this command.";
    private static String INVALID_ARUGMENTS = "Command usage: /punish <player> <reason>\nCommand usage: /punish version";
    private static String INVALID_TARGET = getErrorColor() + "The targeted player is offline or does not exist.";
    private static String PUNISHMENT_REASON = getErrorColor() + "Enter a reason for the punishment.";

    public static String getPunishmentReason() { return PUNISHMENT_REASON; }
    public static String getInvalidTarget() {return INVALID_TARGET; }
    public static String getInvalidArugments() { return INVALID_ARUGMENTS; }
    public static String getNoConsoleSender() { return NO_CONSOLE_SENDER; }
    public static String getNoPermission() { return NO_PERMISSION; }
    private static String getErrorColor() { return errorColor; }
}
