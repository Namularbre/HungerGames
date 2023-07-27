package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.HungerGames;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

public class ArenaRadiusCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            if (args.length == 0) {
                showArenaRadius(player);
            } else if (args.length == 1) {
                setArenaRadius(args[0], player);
            } else {
                PlayerRawMessageSender.sendErrorMessage("Invalid number of arguments. use : /arenaradius [number] or /arenaradius", player);
            }
        } else {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
        }

        return true;
    }

    private void showArenaRadius(Player player) {
        final double CURRENT_RADIUS = HungerGames.arena.getRadius();
        PlayerRawMessageSender.sendInformationMessage("Arena radius is " + CURRENT_RADIUS, player);
    }

    private void setArenaRadius(String value, Player player) {
        int radius = stringToInt(value);

        if (radius >= HungerGames.arena.getEndRadius()) {
            HungerGames.arena.setRadius(radius);
            PlayerRawMessageSender.sendValidationMessage("Worked", player);
        } else {
            PlayerRawMessageSender.sendErrorMessage("The number must be higher are equal to " + HungerGames.arena.getEndRadius(), player);
        }
    }

    private int stringToInt(String string) {
        final int INVALID_STRING = -1;
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException exception) {
            return INVALID_STRING;
        }
    }

}
