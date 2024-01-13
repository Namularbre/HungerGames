package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.PlayerManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            PlayerManager.healPlayer(player);
            PlayerRawMessageSender.sendValidationMessage("Healed !", player);
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }
}
