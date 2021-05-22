package io.github.gkfiredev.colouranvil.files;

import io.github.gkfiredev.colouranvil.api.CustomColour;
import io.github.gkfiredev.colouranvil.manager.CustomColoursManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CustomColoursConfigManager {

    public static File customColourFile;
    public static FileConfiguration cfg;

    public static void setupConfig(Plugin plugin) {
        if(!(plugin.getDataFolder().exists())) plugin.getDataFolder().mkdirs();
        customColourFile = new File(plugin.getDataFolder(), "custom_colours.yml");
        if(!(customColourFile.exists())) {
            try {
                customColourFile.createNewFile();
                loadConfig();
                for(CustomColour colour : CustomColoursManager.getCustomColourList()) {
                    cfg.set("custom_colours." + colour.getCharacter(), colour.getHexValue());
                }
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig();
    }

    public static void loadConfig() {
        cfg = YamlConfiguration.loadConfiguration(customColourFile);
    }

    public static void saveConfig() {
        try {
            cfg.save(customColourFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getBannedWords() {
        return cfg.getStringList("banned_words");
    }

}
