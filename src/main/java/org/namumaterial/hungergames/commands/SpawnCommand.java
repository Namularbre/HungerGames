package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            player.teleport(HungerGames.arena.getCenter());
            PlayerRawMessageSender.sendValidationMessage("You are teleported to the middle of the arena", player);
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }
}
