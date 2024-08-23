package com.clarence.punish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
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
    private final static Material reasonToBePunishmentMaterial = Material.WRITABLE_BOOK;
    private final static Material playerNameItemMaterial = Material.DIAMOND_AXE;
    private final static Material kickItemMaterial = Material.ENCHANTED_BOOK;
    private final static Material temporaryBanMaterial = Material.ENCHANTED_BOOK;
    private final static Material banMaterial = Material.ENCHANTED_BOOK;
    private final static Material playerUUIDMaterial = Material.BOOK;
    private final static Material typesOfPunishmentsMaterial = Material.BOOK;
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
    public static Material getReasonToBePunishmentMaterial() { return reasonToBePunishmentMaterial; }

    public static Material getPlayerUUIDMaterial() {
        return playerUUIDMaterial;
    }
    public static Material getTypesOfPunishmentsMaterial() { return typesOfPunishmentsMaterial; }
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
        Inventory inventory = createDefaultInventory(getInventoryTitle(), getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("defaultInventoryMaterial")));

        //Essentials
        createBaseItemStack(inventory, targetByUUID);
        playerOpenInventory(player, inventory);
    }

    public static Material getMaterialFromConfiguration(String name) {
        try {
            Material.matchMaterial(name);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
        }
        return Material.matchMaterial(name);
    }
    public static Inventory createDefaultInventory(String invTitle, Material inventoryFrameMaterial) {
        Inventory inventory = Bukkit.createInventory(null, 45, invTitle);

        for (int i : getDefaultDecorationInventorySlot()) {
            ItemStack itemStack = createNewItemStack(inventoryFrameMaterial, "", "");
            setInventoryItem(inventory, i, itemStack);
        }
        return inventory;
    }
    public static Inventory createInventory(String invTitle, Material inventoryFrameMaterial) {
        Inventory inventory = Bukkit.createInventory(null, 9, invTitle);

        for (int i : new int[]{0, 1,2, 6,7,8}) {
            ItemStack itemStack = createNewItemStack(Material.GREEN_STAINED_GLASS, "");
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
        ItemStack reasonToBePunishItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("reasonToBePunishmentMaterial")), getItemTitleColor() +"[PUNISHMENT REASON]", Util.getStringBuilderMessage());
        ItemStack playerNameItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("playerNameItemMaterial")), getItemTitleColor() + "[PLAYER NAME]", targetByUUID.getName());
        ItemStack playerUUIDItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("getPlayerUUIDMaterial")), getItemTitleColor() +"[PLAYER UUID]", targetByUUID.getUniqueId().toString());
        ItemStack typesPunishmentItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("typesOfPunishmentMaterial")), getItemTitleColor() + "[Types of punishment]");
        ItemStack getKickItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("getKickItemMaterial")), getItemTitleColor() + getKickTitle());
        ItemStack getTemporaryBanItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("getTemporaryBanMaterial")), getItemTitleColor() + getTemporaryBanTitle());
        ItemStack getBanItemStack = createNewItemStack(getMaterialFromConfiguration(Configuration.getMaterialConfiguration().getString("getBanMaterial")), getItemTitleColor() + getBanTitle());

        setInventoryItem(inventory, 19, reasonToBePunishItemStack);
        setInventoryItem(inventory, 25, playerNameItemStack);
        setInventoryItem(inventory, 34, playerUUIDItemStack);
        setInventoryItem(inventory, 13, typesPunishmentItemStack);
        setInventoryItem(inventory, 21, getKickItemStack);
        setInventoryItem(inventory, 22, getTemporaryBanItemStack);
        setInventoryItem(inventory, 23, getBanItemStack);
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
    public static void lookUp(Player player, String[] args) {

        OfflinePlayer getOfflinePlayerByUUID = Bukkit.getServer().getOfflinePlayer(args[1]);

        if (!getOfflinePlayerByUUID.hasPlayedBefore()) {
            player.sendMessage(Util.Color(Configuration.getMessagesConfiguration().getString("NO_RECORD")));
            return;
        }

        String playerName = Configuration.getUserUUIDYamlConfiguration().getString("Punishments."+getOfflinePlayerByUUID.getUniqueId()+".name");
        String punishmentReason = Configuration.getUserUUIDYamlConfiguration().getString("Punishments." + getOfflinePlayerByUUID.getUniqueId() + ".reason");
        String punishment_type = Configuration.getUserUUIDYamlConfiguration().getString("Punishments." + getOfflinePlayerByUUID.getUniqueId() + ".punishment_type");
        String duration = Configuration.getUserUUIDYamlConfiguration().getString("Punishments." + getOfflinePlayerByUUID.getUniqueId() + ".duration");
        String punishedBy = Configuration.getUserUUIDYamlConfiguration().getString("Punishments." + getOfflinePlayerByUUID.getUniqueId() + ".punishedBy");
        String releaseDate = Configuration.getUserUUIDYamlConfiguration().getString("Punishments." + getOfflinePlayerByUUID.getUniqueId() + ".releaseDate");

        Inventory inventory = InventoryHelper.createInventory("Punishment lookup", Material.WHITE_STAINED_GLASS_PANE);

        ItemStack playerNameItemStack, punishReasonItemStack, punishmentTypeItemStack, durationItemStack, punishedByItemStack, releaseDateItemStack;

        if (playerName == null) {
            playerNameItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Player name]");
        }
        else {
            playerNameItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Player name]", playerName);
        }
        InventoryHelper.setInventoryItem(inventory, 1, playerNameItemStack);

        if (punishmentReason == null) {
            punishReasonItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punishment reason]");
        }
        else {
            punishReasonItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punishment reason]", punishmentReason);
        }
        InventoryHelper.setInventoryItem(inventory, 2, punishReasonItemStack);

        if (punishment_type == null) {
            punishmentTypeItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punishment type]");
        }
        else { punishmentTypeItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punishment type]", punishment_type);

        }
        InventoryHelper.setInventoryItem(inventory, 3, punishmentTypeItemStack);

        if (duration == null) {
            durationItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Duration]");
        }
        else {
            durationItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Duration]", duration);
        }
        InventoryHelper.setInventoryItem(inventory, 4, durationItemStack);
        if (punishedBy == null) {
            punishedByItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punished by]");
        }
        else {
            punishedByItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Punished by]", punishedBy);
        }
        setInventoryItem(inventory, 5, punishedByItemStack);

        if (releaseDate == null) {
            releaseDateItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Release date]");
        }
        else {
            releaseDateItemStack = InventoryHelper.createNewItemStack(Material.GOLD_BLOCK, InventoryHelper.getItemTitleColor() + "[Release date]", releaseDate);
        }
        setInventoryItem(inventory, 6, releaseDateItemStack);

        playerOpenInventory(player, inventory);
    }
}