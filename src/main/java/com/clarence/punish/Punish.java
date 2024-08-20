package com.clarence.punish;

import com.technicjelle.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Punish extends JavaPlugin {
    private static UpdateChecker updateChecker;

    public static UpdateChecker getUpdateChecker() {
        return updateChecker;
    }

    @Override
    public void onEnable() {
        updateChecker = new UpdateChecker("PositionV2024", "Punish", Bukkit.getPluginManager().getPlugin(this.getName()).getDescription().getVersion());
        updateChecker.check();
        updateChecker.logUpdateMessage(this.getLogger());

        new Configuration(this);
        Bukkit.getPluginManager().registerEvents(new listener(), this);
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("punish").setTabCompleter(new PunishTabCompleter());
    }
    @Override
    public void onDisable() {
        System.out.println(Util.getPluginPrefix() + " has shut down.");
    }
}
