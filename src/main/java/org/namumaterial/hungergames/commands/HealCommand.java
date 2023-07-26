package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            final double FULL_HEALTH = 20.0;
            final int FULL_FOOD = 20;

            player.setHealth(FULL_HEALTH);
            player.setFoodLevel(FULL_FOOD);
            PlayerRawMessageSender.sendValidationMessage("Healed !", player);
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }
}
