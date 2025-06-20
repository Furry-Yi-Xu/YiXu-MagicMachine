package com.yixu.Task;

import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.CropAcceleratorProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import org.bukkit.Location;

public class CropAcceleratorTask extends MachineTask {
    private final int radius;
    private final int height;
    private final double chance;
    private final MachineManager machineManager;
    private final CropAcceleratorProcessor cropAcceleratorProcessor;

    public CropAcceleratorTask(
            Location location,
            int duration,
            int radius,
            int height,
            double chance,
            MachineManager machineManager,
            CropAcceleratorProcessor cropAcceleratorProcessor
    ) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.chance = chance;
        this.machineManager = machineManager;
        this.cropAcceleratorProcessor = cropAcceleratorProcessor;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        cropAcceleratorProcessor.runMinecraftCropAccelerator(location, radius, height, chance);

    }
}
