package ru.tnkv.dsrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.dsrp.Main;
import ru.tnkv.dsrp.utils.Style;
import ru.tnkv.dsrp.utils.YamlManager;

import java.util.Random;

public class TryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("global.not-player")));
            return true;
        }

        String result = (Math.random() >= 0.5) ? YamlManager.getMessage("try.result-success") : YamlManager.getMessage("try.result-failure");
        String action = (args.length >= 1) ? String.join(" ", args) : YamlManager.getMessage("try.default-action");

        Main.sendMessageToNear(
                Style.ampersand(
                        YamlManager.getMessage("try.message")
                                .replace("{player}", player.getName())
                                .replace("{action}", action)
                                .replace("{result}", result)
                ),
                player,
                YamlManager.getRadius("radius.try")
        );
        return true;
    }
}


