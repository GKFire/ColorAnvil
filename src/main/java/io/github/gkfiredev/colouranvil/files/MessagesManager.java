package io.github.gkfiredev.colouranvil.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MessagesManager {

    public static File messageFile;
    public static FileConfiguration cfg;

    public static void setupConfig(Plugin plugin) {
        if(!(plugin.getDataFolder().exists())) plugin.getDataFolder().mkdirs();
        messageFile = new File(plugin.getDataFolder(), "messages.yml");
        if(!(messageFile.exists())) {
            try {
                messageFile.createNewFile();
                loadConfig();
                cfg.set("prefix", "§6ColourAnvil§a> §r");
                cfg.set("error.command.permission", "§cYou do not have permission to use this command");
                cfg.set("error.command.invalid_argument", "Invalid Argument!");
                cfg.set("warn.banned_word", "§cYou cannot use a banned word!");
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig();
    }

    public static void loadConfig() {
        cfg = YamlConfiguration.loadConfiguration(messageFile);
    }

    public static void saveConfig() {
        try {
            cfg.save(messageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPrefix() {
        return cfg.getString("prefix");
    }

}
