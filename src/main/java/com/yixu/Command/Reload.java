package com.yixu.Command;

import com.yixu.Manager.ConfigManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.yixu.Util.Permission.PermissionNodes.MainPREFIX;
import static com.yixu.Util.Permission.PermissionCheck.checkPermission;

public class Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String sub = args[0].toLowerCase();

        if (sub.equals("reload") && checkPermission(sender, MainPREFIX + sub)) {
            ConfigManager.reloadAll();
            MessageUtil.sendMessage(sender, "commands.reload.reload-succeed");
            return true;
        }
        return false;
    }
}
