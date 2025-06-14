package com.yixu.Manager;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Event.ItemsAdder.CustomBlockBreakEvent;
import com.yixu.Event.ItemsAdder.CustomBlockInteractEvent;
import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void init(Plugin plugin, MachineManager machineManager, MachineTaskScheduler machineTaskScheduler, ChestCacheManager chestCacheManager) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockInteractEvent(plugin, machineManager, machineTaskScheduler, chestCacheManager), plugin);
        pluginManager.registerEvents(new CustomBlockBreakEvent(plugin, machineManager), plugin);
    }

}
