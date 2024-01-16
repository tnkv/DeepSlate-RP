package ru.tnkv.dsrp.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class YamlManager {

    private static FileConfiguration config;
    private static FileConfiguration lang;

    public static void initialize(File dataFolder){
        config = YamlConfiguration.loadConfiguration(new File(dataFolder, "/config.yml"));
        lang = YamlConfiguration.loadConfiguration(new File(dataFolder, "/lang.yml"));
    }
    public static String getMessage(String key) {
        return lang.getString(key, key);
    }
    public static Integer getRadius(String key) {
        return config.getInt(key, 100);
    }
}
