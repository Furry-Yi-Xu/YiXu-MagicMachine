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

    public DropCollectorTask(
            Location location,
            int duration,
            int radius,
            int height,
            MachineManager machineManager,
            ChestCacheManager chestCacheManager
    ) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.machineManager = machineManager;
        this.chestCacheManager = chestCacheManager;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        DropCollectorProcessor dropCollectorProcessor = new DropCollectorProcessor();
        dropCollectorProcessor.runDropCollector(location, radius, height, chestCacheManager);

    }
}
