
package ru.tnkv.dsrp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.dsrp.Main;
import ru.tnkv.dsrp.utils.Style;
import ru.tnkv.dsrp.utils.YamlManager;

public class MeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("global.not-player")));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(Style.ampersand(YamlManager.getMessage("me.incorrect-usage")));
            return true;
        }

        Main.sendMessageToNear(
                Style.ampersand(
                        YamlManager.getMessage("me.message")
                                .replace("{player}", player.getName())
                                .replace("{action}", String.join(" ", args))
                ),
                player,
                YamlManager.getRadius("radius.me")
        );
        return true;
    }
}

