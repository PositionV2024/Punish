package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class InventoryHelper {
    private static String inventoryTitle = "Punishment GUI";
    private static String itemLoreColor = "" + ChatColor.BLUE;
    private static String itemTitleColor = "" + ChatColor.GREEN + ChatColor.BOLD;
    private static String kickTitle = "[Kick]";
    private static String temporaryBanTitle = "[Temporary ban]";
    private static String banTitle = "[Ban]";

    private static Material defaultInventoryMaterial = Material.YELLOW_STAINED_GLASS_PANE;
    private static Material reasonToBePunishment = Material.WRITABLE_BOOK;
    private static Material playerNameItemMaterial = Material.DIAMOND_AXE;
    private static Material kickItemMaterial = Material.ENCHANTED_BOOK;
    private static Material temporaryBanMaterial = Material.ENCHANTED_BOOK;
    private static Material banMaterial = Material.ENCHANTED_BOOK;
    private static Material typesOfPunishmentMaterial = Material.BOOK;

    public static Material getDefaultInventoryMaterial() { return defaultInventoryMaterial; }
    public static Material getTypesOfPunishmentMaterial() {return typesOfPunishmentMaterial; }
    public static Material getBanMaterial() {return banMaterial; }
    public static Material getTemporaryBanMaterial() { return temporaryBanMaterial; }
    public static Material getKickItemMaterial() { return kickItemMaterial; }
    public static Material getPlayerNameItemMaterial() { return  playerNameItemMaterial; }
    public static Material getPunishMaterial() { return reasonToBePunishment; }

    public static String getItemTitleColor() {return itemTitleColor; }
    public static String getItemLoreColor() { return itemLoreColor; }
    public static String getInventoryTitle() { return inventoryTitle; }
    public static String getKickTitle() { return kickTitle; }
    public static String getTemporaryBanTitle() { return temporaryBanTitle; }
    public static String getBanTitle() { return banTitle; }

    public static Inventory createDefaultInventory(String invTitle, Material inventoryFrameMaterial){
        Inventory inventory = Bukkit.createInventory(null, 54, invTitle);
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 35, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 11, 20, 29, 38, 15, 24, 33, 42}) {
            ItemStack itemStack = createNewItemStack(inventoryFrameMaterial, "", "");
            setInventoryItem(inventory, i, itemStack);
        }
        return inventory;
    }

    public static ItemStack createNewItemStack(Material material, String itemDisplayName, String... itemLore) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemDisplayName);
        itemMeta.setLore(Arrays.asList(itemLore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static void setInventoryItem(Inventory inventory, int invSlot, ItemStack itemStack) {
        inventory.setItem(invSlot, itemStack);
    }

    public static void playerOpenInventory(Player player, Inventory inventory) {
        player.openInventory(inventory);
    }
}
