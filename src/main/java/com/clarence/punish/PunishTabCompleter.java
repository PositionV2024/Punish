package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PunishTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> options = AddSuggestedList("version", "lookup", "reload");

            return stringUtil(args, options, 0);
        }

        switch (args[0].toLowerCase()) {
            default:
            case "lookup":
                List<String> offlinePlayer = new ArrayList<>();

                for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
                    if (player.hasPlayedBefore()) {
                        offlinePlayer.add(player.getName());
                    }
                    return stringUtil(args, offlinePlayer, 1);
                }
                break;
            case "reload":
                List<String> options = AddSuggestedList("material.yml", "messages.yml", "none");

                return stringUtil(args, options, 1);
        }
        return null;
    }

    private ArrayList<String> stringUtil (String[] args, List<String> options, int argIndex) {
        return StringUtil.copyPartialMatches(args[argIndex], options, new ArrayList<>());
    }
    private List<String> AddSuggestedList(String... args) {
        return Arrays.asList(args);
    }
}
