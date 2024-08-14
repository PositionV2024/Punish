package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Punish extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new listener(), this);
        getCommand("punish").setExecutor(new PunishCommand());
    }
}
