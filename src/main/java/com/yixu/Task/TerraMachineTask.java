package com.yixu.Task;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Processor.CropAcceleratorProcessor;
import com.yixu.Processor.DropCollectorProcessor;
import com.yixu.Task.AbstractTask.MachineTask;
import net.momirealms.customcrops.api.CustomCropsAPI;
import net.momirealms.customcrops.api.core.world.CustomCropsWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class TerraMachineTask extends MachineTask {

    private final int radius;
    private final int height;
    private final double chance;
    private final String type;
    private final Player player;
    private final CustomCropsWorld<?> customCropsWorld;
    private final CustomCropsAPI customCropsAPI;
    private final MachineManager machineManager;
    private final ChestCacheManager chestCacheManager;
    private final CropAcceleratorProcessor cropAcceleratorProcessor;
    private final DropCollectorProcessor dropCollectorProcessor;

    public TerraMachineTask(
            Location location,
            int duration,
            int radius,
            int height,
            double chance,
            String type,
            Player player,
            CustomCropsWorld<?> customCropsWorld,
            CustomCropsAPI customCropsAPI,
            MachineManager machineManager,
            ChestCacheManager chestCacheManager,
            CropAcceleratorProcessor cropAcceleratorProcessor,
            DropCollectorProcessor dropCollectorProcessor
    ) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.chance = chance;
        this.type = type;
        this.player = player;
        this.customCropsWorld = customCropsWorld;
        this.customCropsAPI = customCropsAPI;
        this.machineManager = machineManager;
        this.chestCacheManager = chestCacheManager;
        this.cropAcceleratorProcessor = cropAcceleratorProcessor;
        this.dropCollectorProcessor = dropCollectorProcessor;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        switch (type) {
            case "terra_machine":
                cropAcceleratorProcessor.runMinecraftCropAccelerator(location, radius, height, chance);
                dropCollectorProcessor.runDropCollector(location, radius, height, chestCacheManager);
                break;
            case "customcrops_terra_machine":
                cropAcceleratorProcessor.runCustomCropCropAccelerator(location, radius, height, chance, player, customCropsWorld, customCropsAPI);
                dropCollectorProcessor.runDropCollector(location, radius, height, chestCacheManager);
                break;
        }
    }
}
