package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class CheckArenaRadiusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            final double CURRENT_RADIUS = HungerGames.arena.getRadius();
            PlayerRawMessageSender.sendInformationMessage("Arena radius is " + CURRENT_RADIUS, player);
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }
}
