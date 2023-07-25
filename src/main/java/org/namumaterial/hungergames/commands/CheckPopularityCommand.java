package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.TributeManager;

public class CheckPopularityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            final int POPULARITY = TributeManager.getTribute(player).getPopularity();
            player.sendRawMessage(ChatColor.GOLD + "Your popularity is " + POPULARITY);
        } else {
            player.sendRawMessage(ChatColor.RED + "You don't have the permission to use this command");
        }

        return true;
    }
}
