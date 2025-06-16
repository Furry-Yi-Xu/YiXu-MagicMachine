package com.yixu.Processor;

import net.momirealms.customcrops.api.CustomCropsAPI;
import net.momirealms.customcrops.api.core.block.BreakReason;
import net.momirealms.customcrops.api.core.block.CropBlock;
import net.momirealms.customcrops.api.core.mechanic.crop.CropConfig;
import net.momirealms.customcrops.api.core.world.CustomCropsBlockState;
import net.momirealms.customcrops.api.core.world.CustomCropsWorld;
import net.momirealms.customcrops.api.core.world.Pos3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

public class CropAcceleratorProcessor {

    private final Random random = new Random();

    public void runMinecraftCropAccelerator(
            Location location,
            int radius,
            int height,
            double chance
    ) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -height; dy <= height; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Location cloneLocation = location.clone().add(dx, dy, dz);
                    Block block = cloneLocation.getBlock();
                    BlockData blockData = block.getBlockData();

                    if (blockData instanceof Ageable ageable) {

                        if (ageable.getAge() < ageable.getMaximumAge() && random.nextDouble() < chance) {
                            ageable.setAge(ageable.getAge() + 1);
                            block.setBlockData(ageable);
                        }

                        if (ageable.getAge() == ageable.getMaximumAge()) {
                            Collection<ItemStack> blockDrops = block.getDrops();
                            for (ItemStack itemStack : blockDrops) {
                                cloneLocation.getWorld().dropItemNaturally(cloneLocation, itemStack);
                            }
                            ageable.setAge(0);
                            block.setBlockData(ageable);
                        }
                    }
                }
            }
        }
    }

    public void runCustomCropCropAccelerator(
            Location location,
            int radius,
            int height,
            double chance,
            Player player,
            CustomCropsWorld<?> customCropsWorld,
            CustomCropsAPI customCropsAPI
    ) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -height; dy <= height; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    Location cloneLocation = location.clone().add(dx, dy, dz);
                    Pos3 pos3 = Pos3.from(cloneLocation);

                    Optional<CustomCropsBlockState> blockState = customCropsWorld.getBlockState(pos3);

                    if (blockState.isPresent()) {
                        CustomCropsBlockState customCropsBlockState = blockState.get();

                        if (customCropsBlockState.type() instanceof CropBlock cropBlock) {

                            CropConfig cropConfig = cropBlock.config(customCropsBlockState);

                            if (cropConfig == null) {
                                return;
                            }

                            String cropID = cropConfig.id();
                            int currentPoints = cropBlock.point(customCropsBlockState);
                            int maxPoints = cropConfig.maxPoints();

                            if (currentPoints < maxPoints && random.nextDouble() < chance) {
                                int nextPoints = currentPoints + 1;
                                customCropsAPI.addPointToCrop(cloneLocation, nextPoints);
                            }

                            if (currentPoints >= maxPoints) {
                                int minPoints = 0;
                                customCropsAPI.simulatePlayerBreakCrop(player, EquipmentSlot.HAND, cloneLocation, BreakReason.BREAK);
                                customCropsAPI.placeCrop(cloneLocation, cropID, minPoints);
                            }
                        }
                    }
                }
            }
        }
    }
}
