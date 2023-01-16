package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.hcrpcmds.Main;
import java.util.Objects;

public class TodoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
            return true;
        }

        String[] arguments = String.join(" ", args).split("[*]", 2);
        if (args.length >= 1 && arguments.length >= 2 && !Objects.equals(arguments[1], "")) {
            Main.sendMessageToNear(
                    Component.text(p.getName() + " ").color(TextColor.fromHexString("#d77bba")).append(Component.text(arguments[0]).color(TextColor.fromHexString("#ECEFF4"))).append(Component.text(", сказав: ").color(TextColor.fromHexString("#d77bba"))).append(Component.text(arguments[1]).color(TextColor.fromHexString("#ECEFF4"))),
                    p,
                    Main.config.getInt("radius.todo")
            );
            return true;
        }
        sender.sendMessage(Component.text("Используйте: /todo СделалЧто-то*Сказав").color(TextColor.fromHexString("#bf616a")));
        return true;
    }
}
