package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.TributeManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;
import org.namumaterial.hungergames.utils.Tribute;

import java.util.Collection;

public class TeammateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            addTeammate(player, args[0]);
        } else if (args.length == 0) {
            Tribute tribute = TributeManager.getTribute(player);
            Collection<Player> teammates = tribute.getTeammates();
            teammatesToString(teammates, player);
        } else {
            PlayerRawMessageSender.sendErrorMessage("You need to give one username", player);
        }

        return true;
    }

    private void teammatesToString(Collection<Player> teammates, Player player) {
        if (teammates.isEmpty()) {
            PlayerRawMessageSender.sendErrorMessage("The is no one in your team", player);
            return;
        }

        StringBuilder teammatesString = new StringBuilder();
        for (Player teammate: teammates) {
            teammatesString.append(teammate.getName()).append("\n");
        }

        PlayerRawMessageSender.sendInformationMessage(teammatesString.toString(), player);
    }

    private void addTeammate(Player player, String teammateName) {
        if (player.getName().equals(teammateName)) {
            PlayerRawMessageSender.sendErrorMessage("You can't be your own teammate", player);
            return;
        }

        if (isNotAlreadyATeammate(player, teammateName)) {
            boolean finded = false;

            for (Player alivePlayer: TributeManager.getAlivePlayers()) {
                if (alivePlayer.getName().equals(teammateName)) {
                    Tribute senderTribute = TributeManager.getTribute(player);
                    senderTribute.addTeammates(alivePlayer);
                    finded = true;
                    PlayerRawMessageSender.sendValidationMessage("Player added to team", player);
                }
            }

            if (!finded) {
                PlayerRawMessageSender.sendErrorMessage("Player not found", player);
            }
        } else {
            PlayerRawMessageSender.sendErrorMessage("Player is already your teammate", player);
        }
    }

    private boolean isNotAlreadyATeammate(Player player, String teammateName) {
        Tribute tribute = TributeManager.getTribute(player);

        for (Player teammate: tribute.getTeammates()) {
            if (teammate.getName().equals(teammateName)) {
                return false;
            }
        }

        return true;
    }
}
