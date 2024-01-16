package ru.tnkv.dsrp;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tnkv.dsrp.commands.DiceCommand;
import ru.tnkv.dsrp.commands.MeCommand;
import ru.tnkv.dsrp.commands.TodoCommand;
import ru.tnkv.dsrp.commands.TryCommand;
import ru.tnkv.dsrp.utils.YamlManager;

import java.util.logging.Level;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);
        this.saveResource("lang.yml", false);

        YamlManager.initialize(this.getDataFolder());

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
