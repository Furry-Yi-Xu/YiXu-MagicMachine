package com.yixu;

import com.yixu.Manager.CommandManager;
import com.yixu.Manager.ConfigManager;
import com.yixu.Manager.ListenerManager;
import com.yixu.Manager.MachineManager.MachineManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class MagicMachine extends JavaPlugin {

    private static MagicMachine instance;

    public MagicMachine() {
        super();
    }

    @Override
    public void onEnable() {
        instance = this;
        MachineManager machineManager = new MachineManager();

        getLogger().info("YiXu-MagicMachine 插件已加载！");
        getCommand("yixu-magicmachine").setExecutor(new CommandManager());

        MessageUtil.init(this);
        ConfigManager.init(this);
        ListenerManager.init(this, machineManager);
    }

    @Override
    public void onDisable() {
        getLogger().info("YiXu-MagicMachine 插件已卸载！");
    }

    public static MagicMachine getInstance() {
        return instance;
    }

}
