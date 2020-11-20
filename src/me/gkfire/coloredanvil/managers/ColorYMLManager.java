package me.gkfire.coloredanvil.managers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import me.gkfire.coloredanvil.Main;
import net.md_5.bungee.api.ChatColor;

public class ColorYMLManager {
	
	public static File colorFile;
	public static FileConfiguration colorYML;
	public static Plugin plugin = Main.getPlugin(Main.class);
	
	public static HashMap<Character, String> colors;
	
	public static void setup() {
		if(!(plugin.getDataFolder().exists())) plugin.getDataFolder().mkdirs();
		colorFile = new File(plugin.getDataFolder(), "colors.yml");
		if(!(colorFile.exists())) {
			try {
				colorFile.createNewFile();
				colorYML = YamlConfiguration.loadConfiguration(colorFile);
				colors = new HashMap<Character, String>();
				colors.put('o', "#ed7b00");
				for(int i = 0; i < colors.size(); i++) {
					Character key = (Character) colors.keySet().toArray()[i];
					colorYML.set("Color." + key, colors.get(key));
				}
				colorYML.save(colorFile);
			}catch (IOException e) {
				plugin.getLogger().warning(ChatColor.RED + "Unable to Generate colors.yml File!");
			}
		}
		loadColors();
	}
	
	public static String format(String name) {
		if(Bukkit.getVersion().contains("1.16")) {
			for(int i = 0; i < name.length(); i++) {
				if(name.charAt(i) == '&') {
					if((i + 1) >= name.length()) break;
					Character chr = name.charAt(i +1);
					Set<Character> list = colors.keySet();
					for(Character base : list) {
						if(base.equals(chr)) {
							name = name.replace("&" + chr, ChatColor.of(colors.get(chr)) + "");
						}
					}
				}
			}
			
		}
		return name;
	}
	
	public static void loadColors() {
		colorYML = YamlConfiguration.loadConfiguration(colorFile);
		colors = new HashMap<Character, String>();
		Set<String> list = colorYML.getConfigurationSection("Color").getValues(false).keySet();
		for(String s : list) {
			Character chr = s.charAt(0);
			if(colors.containsKey(chr)) continue;
			colors.put(chr, colorYML.getString("Color." + chr));
		}
	}
}
