package com.yixu.Manager.MachineManager;

import org.bukkit.Location;

public class Machine {
    private final Location location;
    private boolean working;

    public Machine(Location location) {
        this.location = location;
        this.working = false;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
