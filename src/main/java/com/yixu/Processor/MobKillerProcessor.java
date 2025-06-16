package com.yixu.Processor;

import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;

public class MobKillerProcessor {
    public void runMobKiller(
            Location location,
            int radius,
            int height,
            double damage,
            MachineManager machineManager
    ) {
        for (Entity entity : location.getNearbyEntities(radius, height, radius)) {
            if (entity instanceof Monster monster) {
                monster.damage(damage);
            }
        }
    }
}
