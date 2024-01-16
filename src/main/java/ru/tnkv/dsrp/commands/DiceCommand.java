package ru.tnkv.dsrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.dsrp.Main;
import ru.tnkv.dsrp.utils.Style;
import ru.tnkv.dsrp.utils.YamlManager;

public class DiceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("global.not-player")));
            return true;
        }

        long diceResult;
        switch (args.length) {
            case 0:
                diceResult = (long) (Math.random() * 6) + 1;
                break;
            case 1:
                try {
                    if (Integer.parseInt(args[0]) > 0) {
                        diceResult = (long) (Math.random() * Long.parseLong(args[0])) + 1;
                        break;
                    }
                } catch (NumberFormatException ignored) {}
            default:
                player.sendMessage(Style.ampersand(YamlManager.getMessage("dice.incorrect-usage")));
                return true;
        }

        Main.sendMessageToNear(
                Style.ampersand(
                        YamlManager.getMessage("dice.message")
                                .replace("{player}", player.getName())
                                .replace("{result}", String.valueOf(diceResult))
                ),
                player,
                YamlManager.getRadius("radius.dice")
        );
        return true;
    }
}


