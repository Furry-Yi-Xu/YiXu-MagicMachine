package com.yixu.Task;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.DropCollectorProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import org.bukkit.Location;

public class DropCollectorTask extends MachineTask {

    private final int radius;
    private final int height;
    private final MachineManager machineManager;
    private final ChestCacheManager chestCacheManager;
    private final DropCollectorProcessor dropCollectorProcessor;

    public DropCollectorTask(
            Location location,
            int duration,
            int radius,
            int height,
            MachineManager machineManager,
            ChestCacheManager chestCacheManager,
            DropCollectorProcessor dropCollectorProcessor
    ) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.machineManager = machineManager;
        this.chestCacheManager = chestCacheManager;
        this.dropCollectorProcessor = dropCollectorProcessor;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        dropCollectorProcessor.runDropCollector(location, radius, height, chestCacheManager);

    }
}
