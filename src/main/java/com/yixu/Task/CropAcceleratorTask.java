package com.yixu.Task;

import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;

import java.util.Random;

public class CropAcceleratorTask extends MachineTask {
    private final int radius;
    private final double chance;
    private final MachineManager machineManager;
    private final Random random = new Random();

    public CropAcceleratorTask(Location location, int duration, int radius, double chance, MachineManager machineManager) {
        super(location ,duration);
        this.radius = radius;
        this.chance = chance;
        this.machineManager = machineManager;
    }

    @Override
    public void tick() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                Location centerLocation = location.clone().add(dx, 0, dz);
                Block block = centerLocation.getBlock();
                BlockData blockData = block.getBlockData();

                if (blockData instanceof Ageable ageable) {
                    if (ageable.getAge() < ageable.getMaximumAge() && random.nextDouble() < chance) {
                        ageable.setAge(ageable.getAge() + 1);
                        block.setBlockData(ageable);
                    }
                }
            }
        }
    }
}

