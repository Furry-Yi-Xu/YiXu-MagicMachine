package com.yixu.Machine;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Config.MachineConfig;
import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Task.CropAcceleratorTask;
import com.yixu.Task.DropCollectorTask;
import com.yixu.Task.HologramCountDownTask;
import com.yixu.Task.MachineTask;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ConsumeIAItem;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
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
    private final MachineTaskScheduler machineTaskScheduler;
    private final ChestCacheManager chestCacheManager;

    public VanillaFarmMachine(Player player, Location location, Plugin plugin, MachineConfig machineConfig, MachineManager machineManager, MachineTaskScheduler machineTaskScheduler, ChestCacheManager chestCacheManager) {
        this.player = player;
        this.plugin = plugin;
        this.location = location;
        this.machineConfig = machineConfig;
        this.machineManager = machineManager;
        this.machineTaskScheduler = machineTaskScheduler;
        this.chestCacheManager = chestCacheManager;
    }

    public void runBirthMachine() {
        ConsumeIAItem consumeIAItem = new ConsumeIAItem();
        if (consumeIAItem.checkItemEnough(player, machineConfig.getConsumeItem(), machineConfig.getConsumeAmount())) {
            DecentHologram decentHologram = new DecentHologram();
            String hologramName = decentHologram.getHologram(location);
            Hologram hologram = DHAPI.getHologram(hologramName);
            machineManager.setWorking(location, true);

            MachineTask cropAcceleratorTask = new CropAcceleratorTask(
                    location,
                    machineConfig.getEffectDuration(),
                    machineConfig.getEffectRadius(),
                    machineConfig.getEffectHeight(),
                    machineConfig.getGrowthChance(),
                    machineManager
            );

            HologramCountDownTask hologramCountDownTask = new HologramCountDownTask(
                    hologram,
                    location,
                    machineConfig.getEffectDuration()
            );

            machineTaskScheduler.addTask(location ,cropAcceleratorTask);
            machineTaskScheduler.addTask(location ,hologramCountDownTask);

        }
    }

    public void runCollectMachine() {
        ConsumeIAItem consumeIAItem = new ConsumeIAItem();
        if (consumeIAItem.checkItemEnough(player, machineConfig.getConsumeItem(), machineConfig.getConsumeAmount())) {
            DecentHologram decentHologram = new DecentHologram();
            String hologramName = decentHologram.getHologram(location);
            Hologram hologram = DHAPI.getHologram(hologramName);
            machineManager.setWorking(location, true);

            DropCollectorTask dropCollectorTask = new DropCollectorTask(
                    location,
                    machineConfig.getEffectDuration(),
                    machineConfig.getEffectRadius(),
                    machineConfig.getEffectHeight(),
                    machineManager,
                    chestCacheManager
            );

            HologramCountDownTask hologramCountDownTask = new HologramCountDownTask(
                    hologram,
                    location,
                    machineConfig.getEffectDuration()
            );

            machineTaskScheduler.addTask(location ,dropCollectorTask);
            machineTaskScheduler.addTask(location ,hologramCountDownTask);
        }
    }
}
