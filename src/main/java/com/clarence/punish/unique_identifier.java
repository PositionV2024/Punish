package com.clarence.punish;

import java.util.HashMap;
import java.util.UUID;

public class unique_identifier {
    private static HashMap<UUID, UUID> UUIDhashmap = new HashMap<>();
    public static void setUUIDhashmap(UUID playerUUID, UUID targetUUID) {
        unique_identifier.getUUIDHashMap().put(playerUUID, targetUUID);
    }
    public static HashMap getUUIDHashMap() { return UUIDhashmap; }
}
