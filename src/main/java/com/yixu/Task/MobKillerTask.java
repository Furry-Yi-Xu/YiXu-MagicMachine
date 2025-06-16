package com.yixu.Task;

import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.MobKillerProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import org.bukkit.Location;

public class MobKillerTask extends MachineTask {

    private final int radius;
    private final int height;
    private final double damage;
    private final MachineManager machineManager;

    public MobKillerTask(Location location, int duration, int radius, int height, double damage, MachineManager machineManager) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.damage = damage;
        this.machineManager = machineManager;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        MobKillerProcessor mobKillerProcessor = new MobKillerProcessor();
        mobKillerProcessor.runMobKiller(location, radius, height, damage, machineManager);

    }
}
