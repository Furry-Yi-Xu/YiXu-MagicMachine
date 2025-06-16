package com.yixu.Processor;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;

public class CropAcceleratorProcessor {

    private final Random random = new Random();

    public void runCropAccelerator(
            Location location,
            int radius,
            int height,
            double chance
    ) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -height; dy <= height; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Location centerLocation = location.clone().add(dx, dy, dz);
                    Block block = centerLocation.getBlock();
                    BlockData blockData = block.getBlockData();

                    if (blockData instanceof Ageable ageable) {

                        if (ageable.getAge() == ageable.getMaximumAge()) {
                            Collection<ItemStack> blockDrops = block.getDrops();
                            for (ItemStack itemStack : blockDrops) {
                                centerLocation.getWorld().dropItemNaturally(centerLocation, itemStack);
                            }
                            ageable.setAge(0);
                            block.setBlockData(ageable);
                            return;
                        }

                        if (ageable.getAge() < ageable.getMaximumAge() && random.nextDouble() < chance) {
                            ageable.setAge(ageable.getAge() + 1);
                            block.setBlockData(ageable);
                        }
                    }
                }
            }
        }
    }
}
