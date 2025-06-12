package com.yixu.Manager;

import com.yixu.Listener.ItemsAdder.CustomBlockBreakEvent;
import com.yixu.Listener.ItemsAdder.CustomBlockInteractEvent;
import com.yixu.Manager.MachineManager.MachineManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    public static void init(Plugin plugin, MachineManager machineManager) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockInteractEvent(plugin, machineManager), plugin);
        pluginManager.registerEvents(new CustomBlockBreakEvent(plugin, machineManager), plugin);
    }

}
