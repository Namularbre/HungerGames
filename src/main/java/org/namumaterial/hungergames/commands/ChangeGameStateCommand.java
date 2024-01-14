package org.namumaterial.hungergames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.namumaterial.hungergames.managers.HungerGameStateManager;
import org.namumaterial.hungergames.managers.KitManager;
import org.namumaterial.hungergames.utils.PlayerRawMessageSender;

import java.util.ArrayList;
import java.util.List;

public class ChangeGameStateCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            PlayerRawMessageSender.sendErrorMessage("This command need one argument. It is used like this : /change_state [state]", player);
            return true;
        }

        if (!player.isOp()) {
            PlayerRawMessageSender.sendNoCommandPermissionMessage(player);
            return true;
        }

        final String HG_STATE = args[0];

        HungerGameStateManager.State state = HungerGameStateManager.stateFromString(HG_STATE);

        if (state != null) {
            HungerGameStateManager.setCurrentStep(state);
            PlayerRawMessageSender.sendValidationMessage("Done", player);
        } else {
            PlayerRawMessageSender.sendErrorMessage("Unknown state", player);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();

            completions.add("not_started");
            completions.add("starting");
            completions.add("playing");
            completions.add("ended");

            String currentInput = args[0].toLowerCase();
            completions.removeIf(s -> !s.toLowerCase().startsWith(currentInput));

            return completions;
        }
        return new ArrayList<>();
    }
}
