package com.yixu.Cache;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

import java.util.HashMap;
import java.util.Map;

public class ChestCacheManager {

    private final Map<Location, Location> chestLocationCache = new HashMap<>();

    public Chest getChestCache(Location location) {

        Location blockLocation = chestLocationCache.get(location);

        if (blockLocation == null) {
            return null;
        }

        Block block = blockLocation.getBlock();

        if (block.getType() != Material.CHEST) {
            chestLocationCache.remove(location);
            return null;
        }

        Chest chest = (Chest) block.getState();

        if (chest.getInventory().firstEmpty() == -1) {
            chestLocationCache.remove(location);
            return null;
        }

        return chest;
    }

    public void putChestCache(Location location, Chest chest) {
        if (location != null && chest != null) {
            chestLocationCache.put(location, chest.getLocation());
        }
    }

    public void removeChestCache(Location location) {
        chestLocationCache.remove(location);
    }

}
