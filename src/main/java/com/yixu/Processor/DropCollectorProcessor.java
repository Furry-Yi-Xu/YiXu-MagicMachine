package com.yixu.Processor;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Util.Chest.ChestFinder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DropCollectorProcessor {

    public void runDropCollector(
            Location location,
            int radius,
            int height,
            ChestCacheManager chestCacheManager
    ) {
        for (Entity entity : location.getNearbyEntities(radius, height, radius)) {

            if (entity instanceof Item item) {

                ItemStack itemStack = item.getItemStack();

                if (itemStack.getType() != Material.AIR) {

                    Chest chestCache = chestCacheManager.getChestCache(location);
                    Chest nearestChest = null;

                    if (chestCache == null) {
                        ChestFinder chestFinder = new ChestFinder(location, radius, height, itemStack, chestCacheManager);
                        nearestChest = chestFinder.FindNearestChest();

                        if (nearestChest != null) {
                            chestCacheManager.putChestCache(location, nearestChest);
                        } else {
                            return;
                        }

                    } else {
                        nearestChest = chestCache;
                    }

                    Inventory chestInventory = nearestChest.getInventory();

                    HashMap<Integer, ItemStack> itemStackHashMap = chestInventory.addItem(itemStack);

                    if (itemStackHashMap.isEmpty()) {
                        item.remove();
                    } else {
                        item.setItemStack(itemStackHashMap.values().iterator().next());
                    }

                }
            }
        }
    }
}
