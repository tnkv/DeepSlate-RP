package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class TodoCommand implements CommandExecutor {
    private final FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("HypeCoreRolePlayCommands")).getDataFolder() + "/config.yml"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("todo")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
                return true;
            }
            String[] arguments = String.join(" ", args).split("[*]", 2);
            if (args.length >= 1 && arguments.length >= 2 && !Objects.equals(arguments[1], "")) {
                for (Player near : Bukkit.getOnlinePlayers()) {
                    if (near.getWorld() == p.getWorld() && near.getLocation().distance(p.getLocation()) <= config.getInt("me-radius")) {
                        near.sendMessage(Component.text(p.getName() + " ").color(TextColor.fromHexString("#d77bba")).append(
                                Component.text(arguments[0]).color(TextColor.fromHexString("#ECEFF4"))).append(
                                Component.text(", сказав: ").color(TextColor.fromHexString("#d77bba"))).append(
                                Component.text(arguments[1]).color(TextColor.fromHexString("#ECEFF4"))));
                    }
                }
                return true;
            }
            sender.sendMessage(Component.text("Используйте: /todo СделалЧто-то*Сказав").color(TextColor.fromHexString("#bf616a")));
        }
        return true;
    }
}
