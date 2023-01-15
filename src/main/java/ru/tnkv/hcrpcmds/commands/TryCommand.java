package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.tnkv.hcrpcmds.Main;

public class TryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
            return true;
        }

        Main.sendMessageToNear(
                Component.text(p.getName() + " попытался " + ((args.length >= 1) ? String.join(" ", args) : "сделать что-то") + ", и у него это ").color(TextColor.fromHexString("#d77bba")).append((Math.random() >= 0.5) ? Component.text("получилось.").color(TextColor.fromHexString("#a3be8c")).decorate(TextDecoration.BOLD) : Component.text("не получилось.").color(TextColor.fromHexString("#bf616a")).decorate(TextDecoration.BOLD)),
                p,
                Main.config.getInt("radius.try")
        );
        return true;
    }
}


