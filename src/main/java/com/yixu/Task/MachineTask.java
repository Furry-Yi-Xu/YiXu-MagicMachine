package com.yixu.Task;

import org.bukkit.Location;

public abstract class MachineTask {
    protected Location location;
    protected int duration;

    public MachineTask(Location location, int duration) {
        this.location = location;
        this.duration = duration;
    }

    public boolean isFinished() {
        return duration <= 0;
    }

    public abstract void machineTask();
}
