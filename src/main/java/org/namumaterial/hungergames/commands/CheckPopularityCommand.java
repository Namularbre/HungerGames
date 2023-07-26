package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class CheckPopularityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            final int POPULARITY = TributeManager.getTribute(player).getPopularity();
            PlayerRawMessageSender.sendValidationMessage("Your popularity is " + POPULARITY, player);
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }
}
