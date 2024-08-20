package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class PunishTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("version");
            options.add("lookup");
            return stringUtil(args, options, 0);
        }

        if (args[0].equals("lookup")) {
            List<String> players = new ArrayList<>();

            for (OfflinePlayer offlinePlayer : Bukkit.getServer().getOfflinePlayers()) {
                players.add(offlinePlayer.getUniqueId().toString());
            }
            return stringUtil(args, players, 1);
        }
      // if (args.length == 2) {
      //    List<String> commonReason = new ArrayList<>();
      //    commonReason.add("Hacking");
      //    commonReason.add("Xraying");
      //    commonReason.add("Griefing");
      //    commonReason.add("Spamming");
      //    commonReason.add("Flying");
      //    commonReason.add("Bullying others");
      //    commonReason.add("Harassment");
      //    return StringUtil.copyPartialMatches(args[1], commonReason, new ArrayList<>());
      //}
        return null;
    }

    private ArrayList<String> stringUtil (String[] args, List<String> options, int argIndex) {
        return StringUtil.copyPartialMatches(args[argIndex], options, new ArrayList<>());
    }
}
