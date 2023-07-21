package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.items.ItemManager;

public class PoisonousSwordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.isOp()) {
            player.getInventory().addItem(ItemManager.poisonousSword);
        } else {
            sender.sendMessage("Only op player can use this command");
        }

        return true;
    }
}
