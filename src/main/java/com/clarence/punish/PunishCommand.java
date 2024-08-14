package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PunishCommand implements CommandExecutor {
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

        if (target == player) {
            player.sendMessage(Util.Color("&4You can't punish yourself."));
            return true;
        }

        handleInventory(player, target, args);

        return true;
    }
    void handleInventory(Player player, Player target, String args[]) {
        if (args.length == 1) {
            player.sendMessage(Util.Color(Errors.getPunishmentReason()));
            return;
        }

        if (unique_identifier.getUUIDHashMap().containsValue(target.getUniqueId())) {
            player.sendMessage(Util.Color("&7Someone is already punishing this player."));
            return;
        }

        unique_identifier.setUUIDhashmap(player.getUniqueId(), target.getUniqueId());

        UUID playerUUID = (UUID) unique_identifier.getUUIDHashMap().get(player.getUniqueId());

        Player getTargetByUUID = player.getServer().getPlayer(playerUUID);

        player.sendMessage(Util.Color("&7You are punishing " + getTargetByUUID.getDisplayName() + "."));

        StringBuilder stringBuilder = Util.stringBuilder(args, 1);

        createInventory(player, getTargetByUUID);
    }

    void createInventory(Player player, Player getTargetByUUID) {
        Inventory inventory = InventoryHelper.createDefaultInventory(InventoryHelper.getInventoryTitle(), InventoryHelper.getDefaultInventoryMaterial());

        //Essentials
        ItemStack reasonItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getPunishMaterial(), InventoryHelper.getItemTitleColor() + "Reason for punishment", InventoryHelper.getItemLoreColor() + Util.getStringBuilderMessage() + ".");
        ItemStack playerNameItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getPlayerNameItemMaterial(), InventoryHelper.getItemTitleColor() + "Target's name", InventoryHelper.getItemLoreColor() + getTargetByUUID.getDisplayName());
        ItemStack playerUUIDItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getTypesOfPunishmentMaterial(), InventoryHelper.getItemTitleColor() + "Player's UUID", InventoryHelper.getItemLoreColor() + getTargetByUUID.getUniqueId());

        ItemStack typeOfPunishmentsItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getTypesOfPunishmentMaterial(), InventoryHelper.getItemTitleColor() + "Types of punishments", "");

        //Types of punishments
        ItemStack kickItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getKickItemMaterial(), InventoryHelper.getItemTitleColor() + InventoryHelper.getKickTitle(), InventoryHelper.getItemLoreColor() + "Good for players", InventoryHelper.getItemLoreColor() + "breaking small rules.");
        ItemStack temporaryBanItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getTemporaryBanMaterial(), InventoryHelper.getItemTitleColor() + InventoryHelper.getTemporaryBanTitle(), InventoryHelper.getItemLoreColor() + "Good for players", InventoryHelper.getItemLoreColor() + "that continue bad behaviour.");
        ItemStack banItemStack = InventoryHelper.createNewItemStack(InventoryHelper.getBanMaterial(), InventoryHelper.getItemTitleColor() + InventoryHelper.getBanTitle(), InventoryHelper.getItemLoreColor() + "Good for players", InventoryHelper.getItemLoreColor() + "that are always putting on", InventoryHelper.getItemLoreColor() + "a continuous cycle of bad behaviour.");


        InventoryHelper.setInventoryItem(inventory, 13, typeOfPunishmentsItemStack);
        InventoryHelper.setInventoryItem(inventory, 19, reasonItemStack);
        InventoryHelper.setInventoryItem(inventory, 25, playerNameItemStack);
        InventoryHelper.setInventoryItem(inventory, 21, kickItemStack);
        InventoryHelper.setInventoryItem(inventory, 22, temporaryBanItemStack);
        InventoryHelper.setInventoryItem(inventory, 23, banItemStack);
        InventoryHelper.setInventoryItem(inventory, 34, playerUUIDItemStack);

        InventoryHelper.playerOpenInventory(player, inventory);
    }
}
