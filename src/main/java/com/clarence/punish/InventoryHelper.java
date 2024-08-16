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

    private static String MINUTES_10 = "[10 MINUTES]";
    private static String MINUTES_20 = "[20 MINUTES]";

    private static Material defaultInventoryMaterial = Material.YELLOW_STAINED_GLASS_PANE;
    private static Material reasonToBePunishment = Material.WRITABLE_BOOK;
    private static Material playerNameItemMaterial = Material.DIAMOND_AXE;
    private static Material kickItemMaterial = Material.ENCHANTED_BOOK;
    private static Material temporaryBanMaterial = Material.ENCHANTED_BOOK;
    private static Material banMaterial = Material.ENCHANTED_BOOK;
    private static Material typesOfPunishmentMaterial = Material.BOOK;
    private static Material tempBanMaterial = Material.GOLD_BLOCK;

    private static Material Minutes10Material = Material.BOOK;
    private static Material Minutes20Material = Material.BOOK;

    public static Material getDefaultInventoryMaterial() {
        return defaultInventoryMaterial;
    }

    public static Material getTypesOfPunishmentMaterial() {
        return typesOfPunishmentMaterial;
    }

    public static Material getBanMaterial() {
        return banMaterial;
    }

    public static Material getTemporaryBanMaterial() {
        return temporaryBanMaterial;
    }

    public static Material getKickItemMaterial() {
        return kickItemMaterial;
    }

    public static Material getPlayerNameItemMaterial() {
        return playerNameItemMaterial;
    }

    public static Material getPunishMaterial() {
        return reasonToBePunishment;
    }

    public static String getItemTitleColor() {
        return itemTitleColor;
    }

    public static String getItemLoreColor() {
        return itemLoreColor;
    }

    public static String getInventoryTitle() {
        return inventoryTitle;
    }

    public static String getKickTitle() {
        return kickTitle;
    }

    public static String getTemporaryBanTitle() {
        return temporaryBanTitle;
    }

    public static String getBanTitle() {
        return banTitle;
    }
    public static String getMinutes10(){
        return MINUTES_10;
    }
    public static String getMinutes20() {
        return MINUTES_20;
    }
    public static Material getTempBanMaterial() {
        return tempBanMaterial;
    }

    public static Material getMinutes10Material() {
        return Minutes10Material;
    }
    public static Material getMinutes20Material() {
        return Minutes20Material;
    }
    public InventoryHelper(Player player, Player targetByUUID) {
        Inventory inventory = createDefaultInventory(getInventoryTitle(), getDefaultInventoryMaterial());

        //Essentials
        createBaseItemStack(inventory, targetByUUID);
        playerOpenInventory(player, inventory);
    }

    public static Inventory createDefaultInventory(String invTitle, Material inventoryFrameMaterial) {
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

    public static void createBaseItemStack(Inventory inventory, Player targetByUUID) {
        ItemStack reasonItemStack = createNewItemStack(getPunishMaterial(), getItemTitleColor() + "Reason for punishment", getItemLoreColor() + Util.getStringBuilderMessage() + ".");
        ItemStack playerNameItemStack = createNewItemStack(getPlayerNameItemMaterial(), getItemTitleColor() + "Target's name", getItemLoreColor() + targetByUUID.getDisplayName());
        ItemStack playerUUIDItemStack = createNewItemStack(getTypesOfPunishmentMaterial(), getItemTitleColor() + "Player's UUID", getItemLoreColor() + targetByUUID.getUniqueId());

        ItemStack typeOfPunishmentsItemStack = createNewItemStack(getTypesOfPunishmentMaterial(), getItemTitleColor() + "Types of punishments", "");

        //Types of punishments
        ItemStack kickItemStack = createNewItemStack(getKickItemMaterial(), getItemTitleColor() + getKickTitle(), getItemLoreColor() + "Good for players", getItemLoreColor() + "breaking small rules.");
        ItemStack temporaryBanItemStack = createNewItemStack(getTemporaryBanMaterial(), getItemTitleColor() + getTemporaryBanTitle(), getItemLoreColor() + "Good for players", getItemLoreColor() + "that continue bad behaviour.");
        ItemStack banItemStack = createNewItemStack(getBanMaterial(), getItemTitleColor() + getBanTitle(), getItemLoreColor() + "Good for players", getItemLoreColor() + "that are always putting on", getItemLoreColor() + "a continuous cycle of bad behaviour.");

        setInventoryItem(inventory, 13, typeOfPunishmentsItemStack);
        setInventoryItem(inventory, 19, reasonItemStack);
        setInventoryItem(inventory, 25, playerNameItemStack);
        setInventoryItem(inventory, 21, kickItemStack);
        setInventoryItem(inventory, 22, temporaryBanItemStack);
        setInventoryItem(inventory, 23, banItemStack);
        setInventoryItem(inventory, 34, playerUUIDItemStack);
    }
    public static void changeInventoryItem(Inventory inventory, Material inventoryFrameMaterial) {
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 35, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 11, 20, 29, 38, 15, 24, 33, 42}) {
            ItemStack itemStack = createNewItemStack(inventoryFrameMaterial, getItemTitleColor() + "Last confirmation");
            setInventoryItem(inventory, i, itemStack);
        }
        ItemStack durationItemStack = InventoryHelper.createNewItemStack(getTempBanMaterial(), getItemTitleColor() + "Duration", getItemLoreColor() + "Select a duration");
        ItemStack MINUTE_10ItemStack = InventoryHelper.createNewItemStack(getMinutes10Material(), getItemTitleColor() + getMinutes10());
        ItemStack MINUTE_20ItemStack = InventoryHelper.createNewItemStack(getMinutes20Material(), getItemTitleColor() + getMinutes20());

        InventoryHelper.setInventoryItem(inventory, 13, durationItemStack);
        InventoryHelper.setInventoryItem(inventory, 21, MINUTE_10ItemStack);
        InventoryHelper.setInventoryItem(inventory, 30, MINUTE_20ItemStack);
    }
}