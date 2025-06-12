package com.yixu.MachineScheduler;

import com.yixu.Task.MachineTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class MachineTaskScheduler extends BukkitRunnable {

    private final Map<Location, List<MachineTask>> activeTasks = new HashMap<>();

    public void addTask(Location location, MachineTask task) {
        activeTasks.computeIfAbsent(location, k -> new ArrayList<>()).add(task);
    }

    public void removeAllTasks(Location location) {
        activeTasks.remove(location);
    }

    @Override
    public void run() {
        Iterator<Map.Entry<Location, List<MachineTask>>> iterator = activeTasks.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Location, List<MachineTask>> entry = iterator.next();
            List<MachineTask> taskList = entry.getValue();

            taskList.removeIf(task -> {
                task.tick();
                return task.isFinished();
            });

            if (taskList.isEmpty()) {
                iterator.remove();
            }
        }
    }
}
