package io.github.gkfire.coloranvil.extras;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.plugin.Plugin;

import io.github.gkfire.coloranvil.Utils;
import io.github.gkfire.coloranvil.managers.ColorYMLManager;
import io.github.gkfire.coloranvil.managers.WordConfigManager;

public class ColorSign implements Listener {
	
	private Plugin plugin;
	
	public ColorSign(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void SignEdit(SignChangeEvent ev) {
		Player p = ev.getPlayer();
		for(int i = 0; i < ev.getLines().length; i++) {
			String line = ev.getLine(i);
			line = ColorYMLManager.format(line);
			line = Utils.checkColorsEnabled(line, plugin, p);
			if(plugin.getConfig().getBoolean("banWords")) {
				ArrayList<String> words = WordConfigManager.getWords();
				String test = new String(line.toUpperCase());
				for(String word : words) {
					if(test.contains(word)) {
						line = plugin.getConfig().getString("banWordName");
						break;
					}
				}
			}
			ev.setLine(i, line);
		}
	}
	
}
