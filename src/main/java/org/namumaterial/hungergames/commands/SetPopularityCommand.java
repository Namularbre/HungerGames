package org.namumaterial.hungergames.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.TributeManager;

public class SetPopularityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {

            if (args.length != 1) {
                player.sendRawMessage(ChatColor.RED + "This command need one argument. /set_popularity [amount]");
                return true;
            }

            final String STRING_AMOUNT = args[0];
            final int AMOUNT = getPopularityAmount(STRING_AMOUNT);

            if (amountIsValid(AMOUNT)) {
                TributeManager.getTribute(player).setPopularity(AMOUNT);
                player.sendRawMessage(ChatColor.GOLD + "Set your popularity to " + STRING_AMOUNT);
            } else {
                player.sendRawMessage(ChatColor.RED + "The argument need to be an integer (Ex : 1, 2, 3...)");
            }
        } else {
            player.sendRawMessage(ChatColor.RED + "You don't have the permission to run this command");
        }

        return true;
    }

    private static boolean amountIsValid(int AMOUNT) {
        return AMOUNT >= 0;
    }

    private int getPopularityAmount(String stringAmount) {
        try {
            return Integer.parseInt(stringAmount);
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}
