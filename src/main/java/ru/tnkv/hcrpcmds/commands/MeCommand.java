
package ru.tnkv.hcrpcmds.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Objects;

public class MeCommand implements CommandExecutor {
    private final FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("HypeCoreRolePlayCommands")).getDataFolder() + "/config.yml"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("me")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
                return true;
            }
            String action;
            if (args.length >= 1) {
                action = String.join(" ", args);
                for (Player near : Bukkit.getOnlinePlayers()) {
                    if (near.getWorld() == p.getWorld() && near.getLocation().distance(p.getLocation()) <= config.getInt("me-radius")) {
                        near.sendMessage(Component.text("* " + p.getName() + " " + action).color(TextColor.fromHexString("#d77bba")));
                    }
                }
                return true;
            }
            sender.sendMessage(Component.text("Используйте: /me Действие").color(TextColor.fromHexString("#bf616a")));
        }
        return true;
    }
}

