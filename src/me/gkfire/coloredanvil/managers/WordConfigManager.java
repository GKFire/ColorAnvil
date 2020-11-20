package me.gkfire.coloredanvil.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.gkfire.coloredanvil.Main;
import me.gkfire.coloredanvil.Utils;

public class WordConfigManager {
	
	public static FileConfiguration Wordcfg;
	public static File WordFile;
	public static Plugin plugin = Main.getPlugin(Main.class);

	public static void setup() {
		if(!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}

		WordFile = new File(plugin.getDataFolder(), "words.yml");
		if(!WordFile.exists()) {
			try {
				WordFile.createNewFile();
				Wordcfg = YamlConfiguration.loadConfiguration(WordFile);
				List<String> list = Arrays.asList("Crap", "Shit");
				Wordcfg.set("Words", list);
				saveConfig();
			} catch (IOException e) {
				Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cERROR: Could Not Create File words.yml"));
			}
		}
	}
	public static void saveConfig() {
		try {
			Wordcfg.save(WordFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Unable to save words.yml");
		}
	}
	public static void loadConfig() {
		setup();
		Wordcfg = YamlConfiguration.loadConfiguration(WordFile);
	}

	public static ArrayList<String> getWords() {
		ArrayList<String> words = new ArrayList<String>();
		for (String word : Wordcfg.getStringList("Words")) {
			words.add(word.toUpperCase());
		}
		return words;
	}
}
