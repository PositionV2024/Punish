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
    private final static int[] defaultDecorationInventorySlot = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            17, 18, 26, 35, 27, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 11, 20, 29, 38, 15,
            24, 33, 42
    };

    private final static int[] DecorationInventorySlot = new int[]{
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            17, 18, 26, 35, 27, 35, 36, 37, 38, 39,
            40, 41, 42, 43, 44, 11, 20, 29, 38, 15,
            24, 33, 42};

    private final static String inventoryTitle = "Punishment GUI";

    private final static String kickTitle = "[Kick]";
    private final static String temporaryBanTitle = "[Temporary ban]";
    private final static String banTitle = "[Permanent ban]";

    private final static String MINUTES_5 = "[5 MINUTES]";
    private final static String MINUTES_10 = "[10 MINUTES]";
    private final static String MINUTES_20 = "[20 MINUTES]";

    private final static String HOURS_5 = "[5 HOURS]";
    private final static String HOURS_10 = "[10 HOURS]";
    private final static String HOURS_20 = "[20 HOURS]";

    private final static String DAY_1 = "[1 DAY]";
    private final static String DAY_2 = "[5 DAYS]";
    private final static String DAY_3 = "[10 DAYS]";

    private final static String itemLoreColor = "" + ChatColor.BLUE;
    private final static String itemTitleColor = "" + ChatColor.GREEN + ChatColor.BOLD;

    private final static Material defaultInventoryMaterial = Material.YELLOW_STAINED_GLASS_PANE;
    private final static Material reasonToBePunishment = Material.WRITABLE_BOOK;
    private final static Material playerNameItemMaterial = Material.DIAMOND_AXE;
    private final static Material kickItemMaterial = Material.ENCHANTED_BOOK;
    private final static Material temporaryBanMaterial = Material.ENCHANTED_BOOK;
    private final static Material banMaterial = Material.ENCHANTED_BOOK;
    private final static Material typesOfPunishmentMaterial = Material.BOOK;
    private final static Material tempBanDecorationMaterial = Material.RED_STAINED_GLASS_PANE;

    private final static Material Minutes5Material = Material.ENCHANTED_BOOK;
    private final static Material Minutes10Material = Material.ENCHANTED_BOOK;
    private final static Material Minutes20Material = Material.ENCHANTED_BOOK;

    private final static Material Hours5Material = Material.ENCHANTED_BOOK;
    private final static Material Hours10Material = Material.ENCHANTED_BOOK;
    private final static Material Hours20Material = Material.ENCHANTED_BOOK;

    private final static Material Day1Material = Material.ENCHANTED_BOOK;
    private final static Material Day2Material = Material.ENCHANTED_BOOK;
    private final static Material Day3Material = Material.ENCHANTED_BOOK;

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
    public static String getMinutes5Title() {return MINUTES_5; }
    public static String getMinutes10Title(){
        return MINUTES_10;
    }
    public static String getMinutes20Title() {
        return MINUTES_20;
    }

    public static String getHours5Title() { return HOURS_5; }
    public static String getHours10Title() { return HOURS_10; }
    public static String getHours20Title() { return HOURS_20; }

    public static String getDay1Title() { return DAY_1; }
    public static String getDay2Title() { return DAY_2; }
    public static String getDay3Title() { return DAY_3; }

    public static Material getMinutes5Material() {return Minutes5Material; }
    public static Material getMinutes10Material() {
        return Minutes10Material;
    }
    public static Material getMinutes20Material() {
        return Minutes20Material;
    }

    public static Material getHours10Material() { return Hours10Material; }
    public static Material getHours5Material() {return Hours5Material; }
    public static Material getHours20Material() { return Hours20Material; }

    public static Material getDay1Material() {return Day1Material;}
    public static Material getDay2Material() {return Day2Material;}
    public static Material getDay3Material() {return Day3Material;}

    public static Material getTempBanDecorationMaterial() { return  tempBanDecorationMaterial; }
    public static int[] getDefaultDecorationInventorySlot() {return defaultDecorationInventorySlot; }
    public static int[] getDecorationInventorySlot() {return DecorationInventorySlot; }

    public InventoryHelper(Player player, Player targetByUUID) {
        Inventory inventory = createDefaultInventory(getInventoryTitle(), getDefaultInventoryMaterial());

        //Essentials
        createBaseItemStack(inventory, targetByUUID);
        playerOpenInventory(player, inventory);
    }
    public static Inventory createDefaultInventory(String invTitle, Material inventoryFrameMaterial) {
        Inventory inventory = Bukkit.createInventory(null, 45, invTitle);
        for (int i : getDefaultDecorationInventorySlot()) {
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
    public static void clearItemIndex(Inventory inventory, int index) { inventory.clear(index); }
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
        clearItemIndex(inventory, 13);
        clearItemIndex(inventory, 22);
        clearItemIndex(inventory, 23);

        for (int i : getDecorationInventorySlot()) {
            ItemStack itemStack = createNewItemStack(inventoryFrameMaterial,  "", "");
            setInventoryItem(inventory, i, itemStack);
        }

        ItemStack MINUTE_5ItemStack = createNewItemStack(getMinutes5Material(), getItemTitleColor() + getMinutes5Title());
        ItemStack MINUTE_10ItemStack = createNewItemStack(getMinutes10Material(), getItemTitleColor() + getMinutes10Title());
        ItemStack MINUTE_20ItemStack = createNewItemStack(getMinutes20Material(), getItemTitleColor() + getMinutes20Title());

        ItemStack HOURS_5ItemStack = createNewItemStack(getHours5Material(), getItemTitleColor() + getHours5Title());
        ItemStack HOURS_10ItemStack = createNewItemStack(getHours10Material(), getItemTitleColor() + getHours10Title());
        ItemStack HOURS_20ItemStack = createNewItemStack(getHours20Material(), getItemTitleColor() + getHours20Title());

        ItemStack DAY_1temStack = createNewItemStack(getDay1Material(), getItemTitleColor() + getDay1Title());
        ItemStack DAY_2temStack = createNewItemStack(getDay2Material(), getItemTitleColor() + getDay2Title());
        ItemStack DAY_3temStack = createNewItemStack(getDay3Material(), getItemTitleColor() + getDay3Title());

        InventoryHelper.setInventoryItem(inventory, 12, MINUTE_5ItemStack);
        InventoryHelper.setInventoryItem(inventory, 21, MINUTE_10ItemStack);
        InventoryHelper.setInventoryItem(inventory, 30, MINUTE_20ItemStack);

        InventoryHelper.setInventoryItem(inventory, 13,HOURS_5ItemStack);
        InventoryHelper.setInventoryItem(inventory, 22,HOURS_10ItemStack);
        InventoryHelper.setInventoryItem(inventory, 31,HOURS_20ItemStack);

        InventoryHelper.setInventoryItem(inventory, 14,DAY_1temStack);
        InventoryHelper.setInventoryItem(inventory, 23,DAY_2temStack);
        InventoryHelper.setInventoryItem(inventory, 32,DAY_3temStack);
    }
}