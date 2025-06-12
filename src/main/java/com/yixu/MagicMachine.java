package com.yixu;

import com.yixu.MachineScheduler.MachineTaskScheduler;
import com.yixu.Manager.CommandManager;
import com.yixu.Manager.ConfigManager;
import com.yixu.Manager.EventManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicMachine extends JavaPlugin {

    private static MagicMachine instance;
    private MachineTaskScheduler scheduler;

    public MagicMachine() {
        super();
    }

    @Override
    public void onEnable() {
        instance = this;
        scheduler = new MachineTaskScheduler();
        scheduler.runTaskTimer(this, 0L, 20L);

        MachineManager machineManager = new MachineManager();

        MessageUtil.init(this);
        ConfigManager.init(this);
        EventManager.init(this, machineManager, scheduler);

        getLogger().info("YiXu-MagicMachine 插件已加载！");
        getCommand("yixu-magicmachine").setExecutor(new CommandManager());
    }

    @Override
    public void onDisable() {
        getLogger().info("YiXu-MagicMachine 插件已卸载！");
    }

    public static MagicMachine getInstance() {
        return instance;
    }

    public MachineTaskScheduler getScheduler() {
        return scheduler;
    }

}
