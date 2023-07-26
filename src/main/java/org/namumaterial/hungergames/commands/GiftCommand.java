package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.tasks.GiftTasks;

public class GiftCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            GiftTasks giftTasks = new GiftTasks();
            giftTasks.giveGiftToPlayer(player);
        } else {
            player.sendRawMessage(ChatColor.RED + "You don't have permission to run this command");
        }

        return true;
    }
}
