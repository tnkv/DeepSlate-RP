package ru.tnkv.hcrpcmds;

import org.bukkit.plugin.java.JavaPlugin;

import ru.tnkv.hcrpcmds.commands.DiceCommand;
import ru.tnkv.hcrpcmds.commands.MeCommand;
import ru.tnkv.hcrpcmds.commands.TodoCommand;
import ru.tnkv.hcrpcmds.commands.TryCommand;

import java.io.File;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        File folder = new File(this.getDataFolder() + "/");
        if(!folder.exists()) {
            folder.mkdir();
        }
        File config_file = new File(this.getDataFolder() + "/config.yml");
        if(!config_file.exists()) {
            this.saveResource("config.yml", true);
        }

        this.getCommand("dice").setExecutor(new DiceCommand());
        this.getCommand("try").setExecutor(new TryCommand());
        this.getCommand("me").setExecutor(new MeCommand());
        this.getCommand("todo").setExecutor(new TodoCommand());
    }

    @Override
    public void onDisable() {

    }
}
