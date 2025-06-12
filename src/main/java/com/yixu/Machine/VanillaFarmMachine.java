package com.yixu.Machine;

import com.yixu.Config.MachineConfig;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Task.CropAcceleratorTask;
import com.yixu.Task.HologramCountDownTask;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ConsumeIAItem;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class VanillaFarmMachine implements Listener {

    private final Plugin plugin;
    private final Player player;
    private final Location location;
    private final MachineConfig machineConfig;
    private final MachineManager machineManager;

    public VanillaFarmMachine(Player player, Location location, Plugin plugin, MachineConfig machineConfig, MachineManager machineManager) {
        this.player = player;
        this.plugin = plugin;
        this.location = location;
        this.machineConfig = machineConfig;
        this.machineManager = machineManager;
    }

    public void runMachineWithHologram(Runnable customTask) {
        ConsumeIAItem consumeIAItem = new ConsumeIAItem();
        if (consumeIAItem.checkItemEnough(player, machineConfig.getConsumeItem(), machineConfig.getConsumeAmount())) {
            DecentHologram decentHologram = new DecentHologram();
            String hologramName = decentHologram.getHologram(location);
            Hologram hologram = DHAPI.getHologram(hologramName);
            machineManager.setWorking(location, true);
            new HologramCountDownTask(hologram, location, machineConfig, machineManager).runTaskTimer(plugin, 0L, 20L);
            customTask.run();
        }
    }

    public void runBirthLantern() {
        runMachineWithHologram(new Runnable() {
            @Override
            public void run() {
                runCropAcceleratorTask();
            }
        });
    }

    private void runCropAcceleratorTask() {
        new CropAcceleratorTask(location, machineConfig, machineManager).runTaskTimer(plugin, 0L, 20L);
    }
}
