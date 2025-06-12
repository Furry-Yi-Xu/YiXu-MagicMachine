package com.yixu.Manager;

import com.yixu.Config.BaseConfig;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    private static BaseConfig configConfig;
    private static BaseConfig messagesConfig;
    private static BaseConfig machineConfig;

    public static void init(Plugin plugin) {
        configConfig = new BaseConfig(plugin, "config.yml");
        messagesConfig = new BaseConfig(plugin, "messages.yml");
        machineConfig = new BaseConfig(plugin, "machines.yml");
    }

    public static BaseConfig getMessagesConfig() {
        return messagesConfig;
    }

    public static BaseConfig getMachineConfig() {
        return machineConfig;
    }

    public static BaseConfig getConfig() {
        return configConfig;
    }

    public static void reloadAll() {
        configConfig.reload();
        messagesConfig.reload();
        machineConfig.reload();
    }
}
