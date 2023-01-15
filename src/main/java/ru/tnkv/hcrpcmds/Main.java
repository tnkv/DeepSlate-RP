package ru.tnkv.hcrpcmds;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tnkv.hcrpcmds.commands.DiceCommand;
import ru.tnkv.hcrpcmds.commands.MeCommand;
import ru.tnkv.hcrpcmds.commands.TodoCommand;
import ru.tnkv.hcrpcmds.commands.TryCommand;
import java.io.File;
import java.util.logging.Level;

public final class Main extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);

        config = YamlConfiguration.loadConfiguration(new File(this.getDataFolder() + "/config.yml"));

        this.getCommand("dice").setExecutor(new DiceCommand());
        this.getCommand("try").setExecutor(new TryCommand());
        this.getCommand("me").setExecutor(new MeCommand());
        this.getCommand("todo").setExecutor(new TodoCommand());

        this.getLogger().log(Level.INFO, "Включено.");
    }

    @Override
    public void onDisable() {
        this.getLogger().log(Level.INFO, "Выключено.");
    }

    public static void sendMessageToNear(Component message, Player player, int radius) {
        for (Player near : Bukkit.getOnlinePlayers()) {
            if (near.getWorld() == player.getWorld() && near.getLocation().distance(player.getLocation()) <= radius) {
                near.sendMessage(message);
            }
        }
    }
}
