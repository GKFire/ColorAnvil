package tech.gkfiredev.colouranvil.files;

import tech.gkfiredev.colouranvil.api.CustomColour;
import tech.gkfiredev.colouranvil.manager.CustomColoursManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Set;

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
                cfg.set("custom_colors.o", "#ed7b00");
                cfg.set("custom_colors.l", "#7ba123");
                cfg.set("custom_colors.y", "#c918a0");
                cfg.set("custom_colors.t", "#13f295");
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loadConfig();
        loadCustomColours();
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

    public static void loadCustomColours() {
        CustomColoursManager.getCustomColourList().clear();
        Set<String> values = cfg.getConfigurationSection("custom_colors").getValues(false).keySet();
        for(String s : values) {
            Character character = s.charAt(0);
            CustomColour colour = new CustomColour(character, cfg.getString("custom_colors." + character));
            colour.register();
        }
    }

}
