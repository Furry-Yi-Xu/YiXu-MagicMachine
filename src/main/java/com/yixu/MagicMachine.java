package com.yixu;

import com.yixu.Cache.ChestCacheManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.CommandManager;
import com.yixu.Manager.ConfigManager;
import com.yixu.Manager.EventManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicMachine extends JavaPlugin {

    private static MagicMachine magicMachine;

    public MagicMachine() {
        super();
    }

    @Override
    public void onEnable() {
        magicMachine = this;

        MachineTaskScheduler machineTaskScheduler = new MachineTaskScheduler();
        MachineManager machineManager = new MachineManager();
        ChestCacheManager chestCacheManager = new ChestCacheManager();

        machineTaskScheduler.runTaskTimer(this, 0L, 20L);

        MessageUtil.init(this);
        ConfigManager.init(this);
        EventManager.init(this, machineManager, machineTaskScheduler, chestCacheManager);

        getCommand("yixu-magicmachine").setExecutor(new CommandManager());
        getCommand("yixu-magicmachine").setTabCompleter(new MainTabCompleter());

        getLogger().info("YiXu-MagicMachine 插件已加载！");
    }

    @Override
    public void onDisable() {
        getLogger().info("YiXu-MagicMachine 插件已卸载！");
    }

    public static MagicMachine getMagicMachine() {
        return magicMachine;
    }
}
