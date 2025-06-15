package com.yixu.Task;

import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

import java.util.Random;

public class MobKillerTask extends MachineTask{

    private final int radius;
    private final int height;
    private final double damage;
    private final MachineManager machineManager;

    public MobKillerTask(Location location, int duration, int radius, int height, double damage, MachineManager machineManager) {
        super(location, duration);
        this.radius = radius;
        this.height = height;
        this.damage = damage;
        this.machineManager = machineManager;
    }

    @Override
    public void machineTask() {
        duration--;

        if (isFinished()) {
            machineManager.setWorking(location, false);
        }

        for (Entity entity : location.getNearbyEntities(radius, height, radius)) {
            if (entity instanceof Monster monster) {
                monster.damage(damage);
            }
        }
    }
}
