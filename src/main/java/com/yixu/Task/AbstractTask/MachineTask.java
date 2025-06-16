package com.yixu.Task.AbstractTask;

import org.bukkit.Location;

public abstract class MachineTask {
    protected Location location;
    protected int duration;

    public abstract void machineTask();

    public MachineTask(Location location, int duration) {
        this.location = location;
        this.duration = duration;
    }

    public boolean isFinished() {
        return duration <= 0;
    }

}
