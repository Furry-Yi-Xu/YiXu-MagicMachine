package com.yixu.Listener.ItemsAdder;

import com.yixu.Config.MachineConfig;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Machine.BirthLantern;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;

public class CustomBlockInteractEvent implements Listener {

    private final Plugin plugin;
    private final MachineManager machineManager;

    public CustomBlockInteractEvent(Plugin plugin, MachineManager machineManager) {
        this.plugin = plugin;
        this.machineManager = machineManager;
    }

    @EventHandler
    public void onCustomBlockInteract(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        String namespacedID = event.getNamespacedID();

        if (!action.isRightClick()) {
            return;
        }

        MachineConfig machineConfig = new MachineConfig(namespacedID);

        if (!"birth_lantern".equals(machineConfig.getType())) {
            return;
        }

        Location location = event.getBlockClicked().getLocation();

        if (machineManager.isWorking(location)) {
            MessageUtil.sendMessage(player, "lantern.machine_started");
            event.setCancelled(true);
            return;
        }

        BirthLantern birthLantern = new BirthLantern(player, location, plugin, machineConfig, machineManager);
        birthLantern.runBirthLantern();
    }
}
