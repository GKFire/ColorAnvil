package io.github.gkfiredev.colouranvil.files;

import io.github.gkfiredev.colouranvil.implement.ColourCode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ColourConfigManager {

    public static File configFile;
    public static FileConfiguration cfg;

    public static void setupConfig(Plugin plugin) {
        if(!(plugin.getDataFolder().exists())) plugin.getDataFolder().mkdirs();
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if(!(configFile.exists())) {
            try {
                configFile.createNewFile();
                loadConfig();
                for(ColourCode code : ColourCode.getColourList()) {
                    cfg.set("color." + code.getNameID().toLowerCase(), true);
                }
                cfg.set("max_lore_count", 20);
                cfg.set("ban_words", true);
                cfg.set("disable_colour_permissions", true);
                cfg.set("disable_lore_permissions", true);
                cfg.set("check_update", true);
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig();
    }

    public static void loadConfig() {
        cfg = YamlConfiguration.loadConfiguration(configFile);
    }

    public static void saveConfig() {
        try {
            cfg.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isColourEnabled(ColourCode colour) {
        return cfg.getBoolean("color." + colour.getNameID().toLowerCase());
    }




}
