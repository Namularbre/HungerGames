package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.PlayerManager;
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

            if (hasNoArguments(args)) {
                showOwnPopularity(player);
            } else {
                showOtherPopularity(args, player);
            }
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }

    private static boolean hasNoArguments(String[] args) {
        return args.length == 0;
    }

    private static void showOtherPopularity(String[] args, Player player) {
        final String PLAYER_NAME = args[0];

        for (Player onlinePlayer: PlayerManager.getAlivePlayers()) {
            if (onlinePlayer.getName().equals(PLAYER_NAME)) {
                final int POPULARITY = TributeManager.getTribute(onlinePlayer).getPopularity();
                PlayerRawMessageSender.sendValidationMessage(PLAYER_NAME + "'s popularity is " + POPULARITY, player);
                return;
            }
        }

        PlayerRawMessageSender.sendErrorMessage("Player not found or not alive", player);
    }

    private static void showOwnPopularity(Player player) {
        final int POPULARITY = TributeManager.getTribute(player).getPopularity();
        PlayerRawMessageSender.sendValidationMessage("Your popularity is " + POPULARITY, player);
    }
}
