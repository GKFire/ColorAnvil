package io.github.gkfiredev.colouranvil.files;

import io.github.gkfiredev.colouranvil.implement.ColourCode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BannedWordsManager {


    public static File wordFile;
    public static FileConfiguration cfg;

    public static void setupConfig(Plugin plugin) {
        if(!(plugin.getDataFolder().exists())) plugin.getDataFolder().mkdirs();
        wordFile = new File(plugin.getDataFolder(), "banned_words.yml");
        if(!(wordFile.exists())) {
            try {
                wordFile.createNewFile();
                loadConfig();
                cfg.set("banned_words", Arrays.asList("sample", "words", "to", "alter"));
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig();
    }

    public static void loadConfig() {
        cfg = YamlConfiguration.loadConfiguration(wordFile);
    }

    public static void saveConfig() {
        try {
            cfg.save(wordFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getBannedWords() {
        return cfg.getStringList("banned_words");
    }

}
