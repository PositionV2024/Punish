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
        if (event.getView().getTitle().equals("Punishment lookup")) {
            event.setCancelled(true);
            return;
        }

        UUID uuid = (UUID) unique_identifier.getUUIDHashMap().get(player.getUniqueId());

        if (uuid == null) {
            return;
        }

        Player target = player.getServer().getPlayer(uuid);

        if (!event.getView().getTitle().contains(InventoryHelper.getInventoryTitle())) {
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
            Util.setKick(target, Util.getStringBuilderMessage(), player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getItemTitleColor() + InventoryHelper.getTemporaryBanTitle())) {
            InventoryHelper.changeInventoryItem(event.getInventory(), InventoryHelper.getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("getTempBanDecorationMaterial")));
            if (event.getCurrentItem() == null) {
                return;
            }
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getBanTitle())) {
            Util.setBan(BanType.Permanent, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 0, player);
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes5Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 5, player);
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes10Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 5, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes20Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 20, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours5Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 5, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours10Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 10, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours20Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 20, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay1Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 1, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay2Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 5, player);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay3Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 10, player);
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());
    }
}