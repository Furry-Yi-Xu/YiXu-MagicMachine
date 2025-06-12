package com.yixu.Task;

import com.yixu.Config.MachineConfig;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class HologramCountDownTask extends BukkitRunnable {
    private final Hologram hologram;
    private int duration;
    private final MachineManager machineManager;
    private final Location location;

    public HologramCountDownTask(Hologram hologram, Location location, MachineConfig config, MachineManager machineManager) {
        this.hologram = hologram;
        this.duration = config.getEffectDuration();
        this.location = location;
        this.machineManager = machineManager;
    }

    @Override
    public void run() {
        if (duration <= 0 || !machineManager.isWorking(location)) {
            DHAPI.setHologramLine(hologram, 0, "&r");
            this.cancel();
            return;
        }

        Map<String, String> placeholders = Map.of("remaining", String.valueOf(duration));
        DHAPI.setHologramLine(hologram, 0, MessageUtil.getMessage("lantern.duration-hologram", placeholders));
        duration--;
    }
}