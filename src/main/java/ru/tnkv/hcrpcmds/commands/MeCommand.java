
package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.hcrpcmds.Main;

public class MeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
            return true;
        }

        if (args.length >= 1) {
            Main.sendMessageToNear(
                    Component.text("* " + p.getName() + " " + String.join(" ", args)).color(TextColor.fromHexString("#d77bba")),
                    p,
                    Main.config.getInt("radius.me")
            );
            return true;
        }
        sender.sendMessage(Component.text("Используйте: /me Действие").color(TextColor.fromHexString("#bf616a")));
        return true;
    }
}

