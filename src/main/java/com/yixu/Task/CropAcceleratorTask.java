package com.yixu.Task;

import com.yixu.Config.MachineConfig;
import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CropAcceleratorTask extends BukkitRunnable {
    private final Location location;
    private final int radius;
    private int duration;
    private double chance;
    private final Random random = new Random();
    private final MachineManager machineManager;

    public CropAcceleratorTask(Location location, MachineConfig config, MachineManager machineManager) {
        this.location = location;
        this.radius = config.getEffectRadius();
        this.duration = config.getEffectDuration();
        this.chance = config.getGrowthChance();
        this.machineManager = machineManager;
    }

    @Override
    public void run() {
        if (duration <= 0) {
            machineManager.setWorking(location, false);
            this.cancel();
            return;
        }

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                Location loc = location.clone().add(dx, 0, dz);
                Block block = loc.getBlock();
                BlockData blockData = block.getBlockData();

                if (blockData instanceof Ageable ageable) {
                    if (ageable.getAge() < ageable.getMaximumAge() && random.nextDouble() < chance) {
                        ageable.setAge(ageable.getAge() + 1);
                        block.setBlockData(ageable);
                    }
                }
            }
        }
        duration--;
    }
}

