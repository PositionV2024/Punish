package com.clarence.punish;

import org.bukkit.Bukkit;
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
                player.closeInventory();
                Bukkit.broadcastMessage(Util.Color("&7" + target.getDisplayName() + " have been temporary banned from this server."));
                target.kickPlayer(Util.Color("&7You have been temporary banned from this server. &l&b[REASON]&7: " + Util.getStringBuilderMessage() + "."));
                Util.setBan(target, Util.getStringBuilderMessage(), Util.getDefaultTmporaryBanTimeDuration(), null);
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
        if (!event.getView().getTitle().equalsIgnoreCase(InventoryHelper.getInventoryTitle())) {
            return;
        }

        UUID uuid = (UUID) unique_identifier.getUUIDHashMap().get(event.getPlayer().getUniqueId());

        if (uuid == null) {
            System.out.println(Util.Color(Util.getPluginPrefix() + " The UUID is INVALID."));
            return;
        }

        Player target = Bukkit.getServer().getPlayer((uuid));
        unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());

        if (target == null) {
            return;
        }

        event.getPlayer().sendMessage(Util.Color("&7You have stopped punishing " +  target.getDisplayName() + "."));
    }
}
