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

public class TryCommand implements CommandExecutor {
    private final FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("HypeCoreRolePlayCommands")).getDataFolder() + "/config.yml"));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (command.getName().equalsIgnoreCase("try")) {
            if (!(sender instanceof Player p)) {
                sender.sendMessage(Component.text("Эта команда доступна только для игроков!").color(TextColor.fromHexString("#bf616a")));
                return true;
            }
            final boolean isSuccess = getRandomBoolean();
            String action = "сделать что-то";
            if (args.length >= 1) {
                action = String.join(" ", args);
            }
            for (Player near : Bukkit.getOnlinePlayers()) {
                if (near.getWorld() == p.getWorld() && near.getLocation().distance(p.getLocation()) <= config.getInt("try-radius")) {
                    near.sendMessage(Component.text(p.getName() + " попытался " + action + ", и у него это ").color(TextColor.fromHexString("#d77bba")).append(
                            isSuccess ? Component.text("получилось.").color(TextColor.fromHexString("#a3be8c")).decorate(TextDecoration.BOLD) : Component.text("не получилось.").color(TextColor.fromHexString("#bf616a")).decorate(TextDecoration.BOLD)));
                }
            }
        }
        return true;
    }

    private boolean getRandomBoolean() {
        return Math.random() >= 0.5;
    }
}


