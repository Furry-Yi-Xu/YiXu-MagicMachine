package com.yixu.Task;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.CropAcceleratorProcessor;
import com.yixu.Processor.DropCollectorProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import org.bukkit.Location;

public class TerraMachineTask extends MachineTask {

    private final int radius;
    private final int height;
    private final double chance;
    private final MachineManager machineManager;
    private final ChestCacheManager chestCacheManager;
    private final CropAcceleratorProcessor cropAcceleratorProcessor;
    private final DropCollectorProcessor dropCollectorProcessor;

    public TerraMachineTask(
            Location location,
            int duration,
            int radius,
            int height,
            double chance,
            MachineManager machineManager,
            ChestCacheManager chestCacheManager,
            CropAcceleratorProcessor cropAcceleratorProcessor,
            DropCollectorProcessor dropCollectorProcessor
    ) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.chance = chance;
        this.machineManager = machineManager;
        this.chestCacheManager = chestCacheManager;
        this.cropAcceleratorProcessor = cropAcceleratorProcessor;
        this.dropCollectorProcessor = dropCollectorProcessor;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        cropAcceleratorProcessor.runCropAccelerator(location, radius, height, chance);
        dropCollectorProcessor.runDropCollector(location, radius, height, chestCacheManager);

    }
}
