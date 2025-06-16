package com.yixu.Task;

import com.yixu.Processor.HologramCountDownProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

public class HologramCountDownTask extends MachineTask {

    private final Hologram hologram;

    public HologramCountDownTask(
            Hologram hologram,
            Location location,
            int duration
    ) {
        super(location, duration);
        this.duration = duration;
        this.hologram = hologram;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            DHAPI.setHologramLine(hologram, 0, "");
            return;
        }

        HologramCountDownProcessor hologramCountDownProcessor = new HologramCountDownProcessor();
        hologramCountDownProcessor.runHologramCountDown(duration, hologram);

    }
}