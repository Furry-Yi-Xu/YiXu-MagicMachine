package com.yixu.Listener.ItemsAdder;

import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class CustomBlockBreakEvent implements Listener {

    private final Plugin plugin;
    private final MachineManager machineManager;

    public CustomBlockBreakEvent(Plugin plugin, MachineManager machineManager) {
        this.plugin = plugin;
        this.machineManager = machineManager;
    }

    @EventHandler
    public void onCustomBlockInteractEvent(dev.lone.itemsadder.api.Events.CustomBlockBreakEvent event){

        if (machineManager.isWorking(event.getBlock().getLocation())) {
            machineManager.removeMachine(event.getBlock().getLocation());
        }
    }
}
