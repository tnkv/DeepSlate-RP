package ru.tnkv.dsrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.dsrp.Main;
import ru.tnkv.dsrp.utils.Style;
import ru.tnkv.dsrp.utils.YamlManager;

import java.util.Objects;

public class TodoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("global.not-player")));
            return true;
        }

        String[] arguments = String.join(" ", args).split("[*]", 2);
        if (args.length < 1 || arguments.length < 2 || Objects.equals(arguments[1], "")) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("todo.incorrect-usage")));
            return true;
        }

        Main.sendMessageToNear(
                Style.ampersand(
                        YamlManager.getMessage("todo.message")
                                .replace("{player}", player.getName())
                                .replace("{action}", arguments[0])
                                .replace("{phrase}", arguments[1])
                ),
                player,
                YamlManager.getRadius("radius.todo")
        );

        return true;

    }
}
