package com.yixu.Task;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Util.Chest.ChestFinder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class DropCollectorTask extends MachineTask{

    private final int radius;
    private final int height;
    private final MachineManager machineManager;
    private final ChestCacheManager chestCacheManager;

    public DropCollectorTask(Location location, int duration, int radius, int height, MachineManager machineManager, ChestCacheManager chestCacheManager) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.machineManager = machineManager;
        this.chestCacheManager= chestCacheManager;
    }

    @Override
    public void tick() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        for (Entity entity : location.getNearbyEntities(radius, height, radius)) {

            if (entity instanceof Item item) {

                ItemStack itemStack = item.getItemStack();

                if(itemStack.getType() != Material.AIR) {

                    Chest chestCache = chestCacheManager.getChestCache(location);
                    Chest nearestChest = null;

                    if (chestCache == null) {
                        ChestFinder chestFinder = new ChestFinder(location, radius, height, itemStack, chestCacheManager);
                        nearestChest = chestFinder.FindNearestChest();

                        if (nearestChest != null) {
                            chestCacheManager.putChestCache(location, nearestChest);
                        }
                        else {
                            return;
                        }

                    }
                    else {
                        nearestChest = chestCache;
                    }

                    Inventory chestInventory = nearestChest.getInventory();

                    HashMap<Integer, ItemStack> itemStackHashMap = chestInventory.addItem(itemStack);

                    if (itemStackHashMap.isEmpty()) {
                        item.remove();
                    }
                    else {
                        item.setItemStack(itemStackHashMap.values().iterator().next());
                    }

                }
            }
        }
    }
}
