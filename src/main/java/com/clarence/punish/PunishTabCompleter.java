package com.clarence.punish;

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
            List<String> options = AddSuggestedList("version", "lookup", "<player_name>");

            return stringUtil(args, options, 0);
      }
        return null;
    }

    private ArrayList<String> stringUtil (String[] args, List<String> options, int argIndex) {
        return StringUtil.copyPartialMatches(args[argIndex], options, new ArrayList<>());
    }
    private List<String> AddSuggestedList(String... args) {
        List<String> commonReason = new ArrayList<>();
        commonReason.add(Arrays.asList(args).toString());
        return commonReason;
    }
}
