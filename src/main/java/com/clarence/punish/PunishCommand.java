package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PunishCommand implements CommandExecutor {
    public static ArrayList<UUID> uuid = new ArrayList<>();
    //Command usage: /punish <player> <reason>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println(Errors.getNoConsoleSender());
            return true;
        }
        Player player = (Player) sender;

        if (!(player.hasPermission(Permission.Usage.getName()))) {
            player.sendMessage(Util.Color(Errors.getNoPermission()));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(Util.Color(Errors.getInvalidArugments()));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(Util.Color(Errors.getInvalidTarget()));
            return true;
        }

        //if (target == player) {
        //    player.sendMessage(Util.Color("&4You can't punish yourself."));
        //    return true;
        //}

        handleInventory(player, target, args);

        return true;
    }
    void handleInventory(Player player, Player target, String args[]) {
        if (args.length == 1) {
            player.sendMessage(Util.Color(Errors.getPunishmentReason()));
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

        InventoryHelper inventoryHelper = new InventoryHelper(player, target);
    }
}
