package com.yixu.Event.ItemsAdder;

import com.flowpowered.nbt.IntTag;
import com.flowpowered.nbt.Tag;
import com.yixu.Cache.ChestCacheManager;
import com.yixu.Config.MachineConfig;
import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.ConfigManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Machine.VanillaMagicMachine;
import com.yixu.Util.Message.MessageUtil;
import net.momirealms.customcrops.api.BukkitCustomCropsPlugin;
import net.momirealms.customcrops.api.core.world.CustomCropsBlockState;
import net.momirealms.customcrops.api.core.world.CustomCropsWorld;
import net.momirealms.customcrops.api.core.world.Pos3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Optional;

public class CustomBlockInteractEvent implements Listener {

    private final Plugin plugin;
    private final MachineManager machineManager;
    private final MachineTaskScheduler machineTaskScheduler;
    private final ChestCacheManager chestCacheManager;

    public CustomBlockInteractEvent(Plugin plugin, MachineManager machineManager, MachineTaskScheduler machineTaskScheduler, ChestCacheManager chestCacheManager) {
        this.plugin = plugin;
        this.machineManager = machineManager;
        this.machineTaskScheduler = machineTaskScheduler;
        this.chestCacheManager = chestCacheManager;
    }

    @EventHandler
    public void onCustomBlockInteract(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        String namespacedID = event.getNamespacedID();

        if (!action.isRightClick()) {
            return;
        }

        MachineConfig machineConfig = new MachineConfig(namespacedID);
        List<?> farmMachines = ConfigManager.getConfig().getConfig().getStringList("FarmMachines");

        if (!farmMachines.contains(machineConfig.getType())) {
            return;
        }

        Location location = event.getBlockClicked().getLocation();

        if (machineManager.isWorking(location)) {
            MessageUtil.sendMessage(player, "farm.machine_started");
            event.setCancelled(true);
            return;
        }

        VanillaMagicMachine vanillaMagicMachine = new VanillaMagicMachine(player, location, plugin, machineConfig, machineManager, machineTaskScheduler, chestCacheManager);

        switch (machineConfig.getType()) {
            case "birth_machine":
                vanillaMagicMachine.runBirthMachine();
                break;
            case "collect_machine":
                vanillaMagicMachine.runCollectMachine();
                break;
            case "killing_machine":
                vanillaMagicMachine.runKillingMachine();
                break;
            case "terra_machine":
            case "customcrops_terra_machine":
                vanillaMagicMachine.runTerraMachine();
                break;
        }
    }
}
