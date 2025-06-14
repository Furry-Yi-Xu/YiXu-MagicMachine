package com.yixu.Task;

import com.yixu.Cache.ChestCacheManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;

public class FindNearestChest{

    private final Location location;
    private final int radius;
    private final ChestCacheManager chestCacheManager;

    private Block nearestChest = null;
    private double nearestDistance = Double.MAX_VALUE;

    public FindNearestChest(Location location, int radius, ChestCacheManager chestCacheManager) {
        this.location = location;
        this.radius = radius;
        this.chestCacheManager = chestCacheManager;
    }

    public Chest FindNearestChest() {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                Location centerLocation = location.clone().add(dx, 1, dz);
                Block block = centerLocation.getBlock();

                if (block.getState() instanceof Chest) {
                    double distanceToChest = location.distanceSquared(location);
                    if (distanceToChest < nearestDistance) {
                        nearestChest = block;
                        nearestDistance = distanceToChest;
                    }
                }
            }
        }
        if (nearestChest != null) {
            Chest chest = (Chest) nearestChest.getState();
            chestCacheManager.putChestCache(location, chest);
            return chest;
        } else {
            return null;
        }
    }
}
