package com.clarence.punish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.UUID;

public class listener implements Listener {
    @EventHandler
    public void onInventoryClickedEvent(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        //Player target = null;
        //for (UUID uuid : PunishCommand.uuid) {
           // target = Bukkit.getServer().getPlayer(uuid);
       // }
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

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getKickTitle())) {
            Util.setKick(target, Util.getStringBuilderMessage());
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getTemporaryBanTitle())) {
            InventoryHelper.changeInventoryItem(event.getInventory(), InventoryHelper.getTempBanDecorationMaterial());
            player.sendMessage(Util.Color("&7Changed inventory item."));
            if (event.getCurrentItem() == null) {
                return;
            }
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getBanTitle())) {
            Util.setPermanentBan(target, Util.getStringBuilderMessage());
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes5Title())){
            Util.setTemporaryBan(target, Util.getStringBuilderMessage(), 5);
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes10Title())) {
            Util.setTemporaryBan(target, Util.getStringBuilderMessage(), 10);
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes20Title())) {
            Util.setTemporaryBan(target, Util.getStringBuilderMessage(), 20);
        }

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());
        //for (UUID uuid : PunishCommand.uuid) {
            //PunishCommand.uuid.remove(uuid);
        //}
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