package com.yixu.Util.Chest;

import com.yixu.Cache.ChestCacheManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ChestFinder {

    private final Location location;
    private final int radius;
    private final int height;
    private final ChestCacheManager chestCacheManager;
    private ItemStack itemStack;

    private Block nearestChest = null;
    private double nearestDistance = Double.MAX_VALUE;

    public ChestFinder(Location location, int radius, int height, ItemStack itemStack, ChestCacheManager chestCacheManager) {
        this.location = location;
        this.radius = radius;
        this.height = height;
        this.itemStack = itemStack;
        this.chestCacheManager = chestCacheManager;
    }

    public Chest FindNearestChest() {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -height; dy <= height; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Location checkLoc = location.clone().add(dx, dy, dz);
                    Block block = checkLoc.getBlock();

                    if (block.getState() instanceof Chest chest) {

                        if (!CanPutItemInChest(chest, itemStack)) {
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
        }

        if (nearestChest != null) {
            return (Chest) nearestChest.getState();
        }

        return null;
    }

    public Boolean CanPutItemInChest(Chest chest, ItemStack itemStack) {

        if (chest.getInventory().firstEmpty() != -1) {
            return true;
        }

        for(ItemStack slotItem : chest.getInventory().getContents()) {

            if (slotItem == null) {
                continue;
            }

            if (slotItem.isSimilar(itemStack) && slotItem.getAmount() <= slotItem.getMaxStackSize()) {
                return true;
            }
        }

        return false;
    }
}
