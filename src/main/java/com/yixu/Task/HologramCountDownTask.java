package com.yixu.Task;

import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;


import java.util.Map;

public class HologramCountDownTask extends MachineTask {
    private final Hologram hologram;

    public HologramCountDownTask(Hologram hologram, Location location, int duration) {
        super(location ,duration);
        this.duration = duration;
        this.hologram = hologram;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            DHAPI.setHologramLine(hologram, 0, "&r");
            return;
        }

        Map<String, String> placeholders = Map.of("remaining", String.valueOf(duration));
        DHAPI.setHologramLine(hologram, 0, MessageUtil.getMessage("farm.duration-hologram", placeholders));
    }
}