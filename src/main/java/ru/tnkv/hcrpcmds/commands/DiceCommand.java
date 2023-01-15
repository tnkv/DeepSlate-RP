package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class DiceCommand implements CommandExecutor {
    private final FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("HypeCoreRolePlayCommands")).getDataFolder() + "/config.yml"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("dice")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
                return true;
            }
            long diceLong;
            switch (args.length) {
                case 0:
                    diceLong = randomNumber(6);
                    break;
                case 1:
                        try {
                            if (Integer.parseInt(args[0]) > 0) {
                                diceLong = randomNumber(Long.parseLong(args[0]));
                                break;
                            }
                        } catch (NumberFormatException ignored) {}
                default:
                    p.sendMessage(Component.text("Используйте: /dice (Необязательно: МаксимальноеЗначение)").color(TextColor.fromHexString("#bf616a")));
                    return true;
            }
            for (Player near : Bukkit.getOnlinePlayers()) {
                if (near.getWorld() == p.getWorld() && near.getLocation().distance(p.getLocation()) <= config.getInt("dice-radius")) {
                    near.sendMessage(Component.text(p.getName() + " кинул кости и выбил ").color(TextColor.fromHexString("#d77bba")).append(
                            Component.text(diceLong).color(TextColor.fromHexString("#a3be8c")).decorate(TextDecoration.BOLD)));
                }
            }
        }
        return true;
    }

    public long randomNumber(long range) {
        return (long) (Math.random() * range) + 1;
    }
}


