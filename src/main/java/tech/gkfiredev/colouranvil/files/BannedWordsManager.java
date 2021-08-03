package tech.gkfiredev.colouranvil.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/*

This is to manage all words in a config to prevent players from using said words.
The purpose of this is to make family-friendly servers more family-friendly.

 */




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
