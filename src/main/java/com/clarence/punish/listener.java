package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class listener implements Listener {
    @EventHandler
    public void onInventoryClickedEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        UUID uuid = (UUID) unique_identifier.getUUIDHashMap().get(player.getUniqueId());

        if (uuid == null) {
            return;
        }

        Player target = player.getServer().getPlayer(uuid);

        if (!event.getView().getTitle().equalsIgnoreCase(InventoryHelper.getInventoryTitle())) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }

        if (target == null) {
            player.sendMessage(Util.Color("&7That player is not online."));
            player.closeInventory();
            return;
        }

        if (event.getCurrentItem().getType() == InventoryHelper.getKickItemMaterial()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getKickTitle())) {
                player.closeInventory();
                Bukkit.broadcastMessage(Util.Color("&7" + target.getDisplayName() + " have been kicked from this server."));
                target.kickPlayer(Util.Color("&7You have been kicked from this server. &l&b[REASON]&7: " + Util.getStringBuilderMessage() + "."));
            }
        }
        if (event.getCurrentItem().getType() == InventoryHelper.getTemporaryBanMaterial()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getTemporaryBanTitle())) {
                Inventory inventory = InventoryHelper.createDefaultInventory("Select a duration", InventoryHelper.getDefaultInventoryMaterial());
                for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 35, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 11, 20, 29, 38, 15, 24, 33, 42, 10, 20, 30, 16, 25, 33}) {
                    ItemStack itemStack = InventoryHelper.createNewItemStack(InventoryHelper.getDefaultInventoryMaterial(), "", "");
                    InventoryHelper.setInventoryItem(inventory, i, itemStack);
                }
                InventoryHelper.playerOpenInventory(player, inventory);
            }
        }
        if (event.getCurrentItem().getType() == InventoryHelper.getBanMaterial()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getBanTitle())) {
                player.closeInventory();
                Bukkit.broadcastMessage(Util.Color("&7" + target.getDisplayName() + " have been banned from this server."));
                target.kickPlayer(Util.Color("&7You have been banned from this server. &l&b[REASON]&7: " + Util.getStringBuilderMessage() + "."));
                Util.setBan(target, Util.getStringBuilderMessage(), null);
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());
    }
    // if (!event.getView().getTitle().equalsIgnoreCase(InventoryHelper.getInventoryTitle())) {
    //     return;
    // }

    // UUID uuid = (UUID) unique_identifier.getUUIDHashMap().get(event.getPlayer().getUniqueId());

    // if (uuid == null) {
    //     System.out.println(Util.Color(Util.getPluginPrefix() + " The UUID is INVALID."));
    //     return;
    // }

    // Player target = Bukkit.getServer().getPlayer((uuid));
    // unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());

    // if (target == null) {
    //     return;
    // }

    // event.getPlayer().sendMessage(Util.Color("&7You have stopped punishing " +  target.getDisplayName() + "."));
}