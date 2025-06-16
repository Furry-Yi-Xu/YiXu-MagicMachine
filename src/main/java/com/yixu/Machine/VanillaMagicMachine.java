package com.yixu.Machine;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Config.MachineConfig;
import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.CropAcceleratorProcessor;
import com.yixu.Processor.DropCollectorProcessor;
import com.yixu.Processor.HologramCountDownProcessor;
import com.yixu.Processor.MobKillerProcessor;
import com.yixu.Task.*;
import com.yixu.Task.AbstractTask.MachineTask;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ConsumeIAItem;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class VanillaMagicMachine implements Listener {

    private final Plugin plugin;
    private final Player player;
    private final Location location;
    private final MachineConfig machineConfig;
    private final MachineManager machineManager;
    private final MachineTaskScheduler machineTaskScheduler;
    private final ChestCacheManager chestCacheManager;

    public VanillaMagicMachine(Player player, Location location, Plugin plugin, MachineConfig machineConfig, MachineManager machineManager, MachineTaskScheduler machineTaskScheduler, ChestCacheManager chestCacheManager) {
        this.player = player;
        this.plugin = plugin;
        this.location = location;
        this.machineConfig = machineConfig;
        this.machineManager = machineManager;
        this.machineTaskScheduler = machineTaskScheduler;
        this.chestCacheManager = chestCacheManager;
    }

    public void addMachineTask(MachineTask machineTask) {
        ConsumeIAItem consumeIAItem = new ConsumeIAItem();
        if (consumeIAItem.checkItemEnough(player, machineConfig.getConsumeItem(), machineConfig.getConsumeAmount())) {
            DecentHologram decentHologram = new DecentHologram();
            HologramCountDownProcessor hologramCountDownProcessor = new HologramCountDownProcessor();

            String hologramName = decentHologram.getHologram(location);
            Hologram hologram = DHAPI.getHologram(hologramName);

            machineManager.setWorking(location, true);

            HologramCountDownTask hologramCountDownTask = new HologramCountDownTask(
                    hologram,
                    location,
                    machineConfig.getEffectDuration(),
                    hologramCountDownProcessor
            );

            machineTaskScheduler.addTask(location, machineTask);
            machineTaskScheduler.addTask(location, hologramCountDownTask);
        }
    }

    public void runBirthMachine() {

        CropAcceleratorProcessor cropAcceleratorProcessor = new CropAcceleratorProcessor();

        MachineTask cropAcceleratorTask = new CropAcceleratorTask(
                location,
                machineConfig.getEffectDuration(),
                machineConfig.getEffectRadius(),
                machineConfig.getEffectHeight(),
                machineConfig.getGrowthChance(),
                machineManager,
                cropAcceleratorProcessor
        );
        addMachineTask(cropAcceleratorTask);
    }

    public void runCollectMachine() {

        DropCollectorProcessor dropCollectorProcessor = new DropCollectorProcessor();

        DropCollectorTask dropCollectorTask = new DropCollectorTask(
                location,
                machineConfig.getEffectDuration(),
                machineConfig.getEffectRadius(),
                machineConfig.getEffectHeight(),
                machineManager,
                chestCacheManager,
                dropCollectorProcessor
        );
        addMachineTask(dropCollectorTask);
    }

    public void runKillingMachine() {

        MobKillerProcessor mobKillerProcessor = new MobKillerProcessor();

        MobKillerTask mobKillerTask = new MobKillerTask(
                location,
                machineConfig.getEffectDuration(),
                machineConfig.getEffectRadius(),
                machineConfig.getEffectHeight(),
                machineConfig.getMonsterDamage(),
                machineManager,
                mobKillerProcessor
        );
        addMachineTask(mobKillerTask);
    }

    public void runTerraMachine() {

        CropAcceleratorProcessor cropAcceleratorProcessor = new CropAcceleratorProcessor();
        DropCollectorProcessor dropCollectorProcessor = new DropCollectorProcessor();

        TerraMachineTask terraMachineTask = new TerraMachineTask(
                location,
                machineConfig.getEffectDuration(),
                machineConfig.getEffectRadius(),
                machineConfig.getEffectHeight(),
                machineConfig.getGrowthChance(),
                machineManager,
                chestCacheManager,
                cropAcceleratorProcessor,
                dropCollectorProcessor
        );
        addMachineTask(terraMachineTask);
    }
}
