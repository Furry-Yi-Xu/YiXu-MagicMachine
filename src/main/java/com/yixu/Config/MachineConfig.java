package com.yixu.Config;

import com.yixu.Manager.ConfigManager;

public class MachineConfig {

    private final String path;

    public MachineConfig(String namespacedID) {
        this.path = namespacedID + ".";
    }

    public String getType() {

        if (ConfigManager.getMachineConfig().getConfig().getString(path + "type") == null) {
            return "null";
        }
        return ConfigManager.getMachineConfig().getConfig().getString(path + "type");
    }

    public String getConsumeItem() {
        return ConfigManager.getMachineConfig().getConfig().getString(path + "consume_item");
    }

    public int getConsumeAmount() {
        return ConfigManager.getMachineConfig().getConfig().getInt(path + "consume_amount");
    }

    public int getEffectRadius() {
        return ConfigManager.getMachineConfig().getConfig().getInt(path + "effect_radius");
    }

    public int getEffectDuration() {
        return ConfigManager.getMachineConfig().getConfig().getInt(path + "effect_duration");
    }

    public int getEffectHeight() {
        return ConfigManager.getMachineConfig().getConfig().getInt(path + "effect_height");
    }

    public double getGrowthChance() {
        return ConfigManager.getMachineConfig().getConfig().getDouble(path + "growth_chance");
    }

    public double getMonsterDamage() {
        return ConfigManager.getMachineConfig().getConfig().getDouble(path + "monster_damage");
    }

}
