package com.yixu.Task;

import com.yixu.Task.AbstractTask.MachineTask;
import org.bukkit.Location;

public class TerraTask extends MachineTask {

    public TerraTask(
            Location location,
            int duration
    ) {
        super(location, duration);
    }

    @Override
    public void machineTask() {

    }
}
