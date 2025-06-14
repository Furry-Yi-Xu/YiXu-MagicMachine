package com.yixu.Util.Chest;

import com.yixu.Cache.ChestCacheManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;

public class ChestFinder {

    private final Location location;
    private final int radius;
    private final int height;
    private final ChestCacheManager chestCacheManager;

    private Block nearestChest = null;
    private double nearestDistance = Double.MAX_VALUE;

    public ChestFinder(Location location, int radius,int height, ChestCacheManager chestCacheManager) {
        this.location = location;
        this.radius = radius;
        this.height = height;
        this.chestCacheManager = chestCacheManager;
    }

    public Chest FindNearestChest() {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                Location centerLocation = location.clone().add(dx, height, dz);
                Block block = centerLocation.getBlock();

                if (block.getState() instanceof Chest chest) {
                    if (chest.getInventory().firstEmpty() == -1) {
                        continue;
                    }

                    double distanceToChest = block.getLocation().distanceSquared(location);

                    if (distanceToChest < nearestDistance) {
                        nearestChest = block;
                        nearestDistance = distanceToChest;
                    }
                }
            }
        }
        if (nearestChest != null) {
            return (Chest) nearestChest.getState();
        }

        return null;
    }
}
