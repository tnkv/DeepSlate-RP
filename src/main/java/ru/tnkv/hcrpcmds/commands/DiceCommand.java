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

public class DiceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
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
                p.sendMessage(Component.text("Используйте: /dice (Необязательно: МаксимальноеЗначение)").color(TextColor.fromHexString("#bf616a")));
                return true;
        }
        Main.sendMessageToNear(
                Component.text(p.getName() + " кинул кости и выбил ").color(TextColor.fromHexString("#d77bba")).append(Component.text(diceResult).color(TextColor.fromHexString("#a3be8c")).decorate(TextDecoration.BOLD)),
                p,
                Main.config.getInt("radius.dice")
        );
        return true;
    }
}


