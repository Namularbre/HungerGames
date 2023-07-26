package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class SetPopularityCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {

            if (args.length != 1) {
                PlayerRawMessageSender.sendErrorMessage("This command need one argument. /set_popularity [amount]", player);
                return true;
            }

            final String STRING_AMOUNT = args[0];
            final int AMOUNT = getPopularityAmount(STRING_AMOUNT);

            if (amountIsValid(AMOUNT)) {
                TributeManager.getTribute(player).setPopularity(AMOUNT);
                PlayerRawMessageSender.sendValidationMessage("Set your popularity to " + STRING_AMOUNT, player);
            } else {
                PlayerRawMessageSender.sendErrorMessage("The argument need to be an integer (Ex : 1, 2, 3...)", player);
            }
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }

    private static boolean amountIsValid(int AMOUNT) {
        return AMOUNT >= 0;
    }

    private int getPopularityAmount(String stringAmount) {
        final int INVALID_VALUE = -1;
        try {
            return Integer.parseInt(stringAmount);
        } catch (NumberFormatException exception) {
            return INVALID_VALUE;
        }
    }
}
