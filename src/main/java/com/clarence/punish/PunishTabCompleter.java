package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
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

           for (Player player : Bukkit.getOnlinePlayers()) {
               options.add(player.getDisplayName());
           }
           return StringUtil.copyPartialMatches(args[0], options, new ArrayList<>());
       } else if (args.length == 2) {
           List<String> commonReason = new ArrayList<>();
           for (OfflinePlayer uuid : Bukkit.getServer().getOfflinePlayers()) {
               commonReason.add(uuid.getUniqueId().toString());
           }
           commonReason.add("Hacking");
           commonReason.add("Xraying");
           commonReason.add("Griefing");
           commonReason.add("Spamming");
           commonReason.add("Flying");
           commonReason.add("Bullying others");
           commonReason.add("Harassment");


           return StringUtil.copyPartialMatches(args[1], commonReason, new ArrayList<>());
       }
        return null;
    }
}
