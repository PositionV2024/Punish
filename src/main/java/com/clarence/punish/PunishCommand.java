package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PunishCommand implements CommandExecutor {
    //public static ArrayList<UUID> uuid = new ArrayList<>();
    //Command usage: /punish <player> <reason>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println(Configuration.getMessagesConfiguration().getString("NO_CONSOLE_SENDER"));
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission(Permission.Usage.getName()))) {
            sendMessage(player, Util.Color(Configuration.getMessagesConfiguration().getString("NO_PERMISSION")));
            return true;
        }

        if (args.length == 0) {
            sendMessage(player, ChatColor.RED + Errors.getInvalidArugments());
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "version":
                updateChecker(player, Util.Color("&7Your current version is " + Punish.getUpdateChecker().getCurrentVersion() + "\n" +
                        "There is a latest version." +
                        " Please download the latest version " +
                        "for more improvements: &2" + Punish.getUpdateChecker().getUpdateUrl()));
                break;
            case "lookup":
                if (args.length == 1) {
                    sendMessage(player, Util.Color(Errors.getInvalidArugments()));
                    return true;
                }
                InventoryHelper.lookUp(player, args);
                break;
            default:
                setTargetPunished(player, args);
                break;
        }
        return true;
    }
    void handleInventory(Player player, Player target, String args[]) {
        if (args.length == 1) {
            player.sendMessage(Util.Color(Configuration.getMessagesConfiguration().getString("PUNISHMENT_REASON")));
            return;
        }
       // if (uuid.contains(target.getUniqueId())) {
            //player.sendMessage(Util.Color("&7Someone is already punishing this player."));
           // return;
        //}
        //uuid.add(target.getUniqueId());
        if (unique_identifier.getUUIDHashMap().containsValue(target.getUniqueId())) {
            player.sendMessage(Util.Color("&7Someone is already punishing this player."));
            return;
        }

        unique_identifier.setUUIDhashmap(player.getUniqueId(), target.getUniqueId());

        UUID playerUUID = (UUID) unique_identifier.getUUIDHashMap().get(player.getUniqueId());

        Player getTargetByUUID = player.getServer().getPlayer(playerUUID);

        player.sendMessage(Util.Color("&7You are punishing " + getTargetByUUID.getDisplayName() + "."));

        StringBuilder stringBuilder = Util.stringBuilder(args, 1);

        new InventoryHelper(player, target);
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(message);
    }
    public void setTargetPunished(Player player, String[] args){
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(Util.Color(Configuration.getMessagesConfiguration().getString("INVALID_TARGET")));
            return;
        }
        handleInventory(player, target, args);
    }
    public void updateChecker(Player player, String message) {
        if (Punish.getUpdateChecker().isUpdateAvailable()) {
            sendMessage(player, message);
            return;
        }
        sendMessage(player, Util.Color("&7Your current version is the latest."));
    }
}
