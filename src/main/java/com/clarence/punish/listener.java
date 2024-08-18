package com.clarence.punish;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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
            Util.setKick(target, Util.getStringBuilderMessage());
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getTemporaryBanTitle())) {
            InventoryHelper.changeInventoryItem(event.getInventory(), InventoryHelper.getTempBanDecorationMaterial());
            if (event.getCurrentItem() == null) {
                return;
            }
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getBanTitle())) {
            Util.setBan(BanType.Permanent, null, target, Util.getStringBuilderMessage(), 0);
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes5Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 5);
            return;
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes10Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 5);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getMinutes20Title())) {
            Util.setBan(BanType.Temporary, BanDuration.MINUTES, target, Util.getStringBuilderMessage(), 20);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours5Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 5);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours10Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 10);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getHours20Title())) {
            Util.setBan(BanType.Temporary, BanDuration.HOURS, target, Util.getStringBuilderMessage(), 20);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay1Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 1);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay2Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 5);
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName().contains(InventoryHelper.getDay3Title())) {
            Util.setBan(BanType.Temporary, BanDuration.DAYS, target, Util.getStringBuilderMessage(), 10);
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        unique_identifier.getUUIDHashMap().remove(event.getPlayer().getUniqueId());
    }
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (!Configuration.getConfig().contains("Punishments" + "." + event.getPlayer().getUniqueId().toString())) {
            System.out.println(event.getPlayer().getDisplayName() + " is not in the punished list");
            return;
        }
        Configuration.getConfig().set("Punishments" + "." + event.getPlayer().getUniqueId().toString(), null);
        Configuration.punish.saveConfig();
        System.out.println(event.getPlayer().getDisplayName() + " is removed from the config.yml");
    }
}