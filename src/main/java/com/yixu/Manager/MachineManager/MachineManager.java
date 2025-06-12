package com.yixu.Manager.MachineManager;

import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class MachineManager {
    private final Map<Location, Machine> machines = new HashMap<>();

    public Machine getMachine(Location location) {
        return machines.computeIfAbsent(location, Machine::new);
    }

    public boolean isWorking(Location location) {
        Machine machine = machines.get(location);
        return machine != null && machine.isWorking();
    }

    public void setWorking(Location location, boolean working) {
        Machine machine = getMachine(location);
        machine.setWorking(working);
    }

    public void removeMachine(Location location) {
        machines.remove(location);
    }
}
