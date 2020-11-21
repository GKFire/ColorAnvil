package io.github.gkfire.coloranvil;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.gkfire.coloranvil.managers.ColorYMLManager;
import io.github.gkfire.coloranvil.managers.WordConfigManager;

public class ColorAnvil implements Listener {
	private Main plugin;
	
	public ColorAnvil(Main pl) {
		plugin = pl;
	}
	
	@EventHandler
	public void onPlayerRenameItem(PrepareAnvilEvent e){
		Player p = (Player) e.getView().getPlayer();
		if(e.getResult() != null && e.getResult().hasItemMeta() && e.getInventory().getRenameText() != ""){
			if(e.getInventory().getRenameText().startsWith("(l)")) {
				if(p.hasPermission("ca.uselore") || plugin.getConfig().getBoolean("disable_Permissions")) {
					ItemStack result = e.getResult();
					ItemMeta resultMeta = result.getItemMeta();
					ItemStack input = e.getInventory().getItem(0);
					if(input.hasItemMeta() && input.getItemMeta().hasDisplayName()){
						String itemName = input.getItemMeta().getDisplayName();
						resultMeta.setDisplayName(itemName);
					}
					
					String loreColored = e.getInventory().getRenameText().replaceFirst("(l)", "");
					loreColored = loreColored.replace("()", "");
					
					loreColored = ColorYMLManager.format(loreColored);
					loreColored = Utils.checkColorsEnabled(loreColored, plugin, p);
					if(plugin.getConfig().getBoolean("banWords")) {
						ArrayList<String> words = WordConfigManager.getWords();
						String test = new String(loreColored.toUpperCase());
						for(String word : words) {
							if(test.contains(word)) {
								loreColored = plugin.getConfig().getString("banWordName");
								break;
							}
						}
					}
					

					ArrayList<String> lore = new ArrayList<String>();
					if(input.hasItemMeta() && input.getItemMeta().hasLore()){
						lore.addAll(input.getItemMeta().getLore());
					}
					lore.add(loreColored);
					resultMeta.setLore(lore);
					result.setItemMeta(resultMeta);
					return;
				}

			} else if(e.getInventory().getRenameText().startsWith("(l")) {
				if(p.hasPermission("ca.getLoreSection") || plugin.getConfig().getBoolean("disable_Permissions")) {
					ItemStack result = e.getResult();
					String rename = e.getInventory().getRenameText();
					if(!(rename.contains(")"))) return;
					String start = rename.substring(rename.indexOf("(l"), rename.indexOf(")") + 1);
					start = start.replace("(l", "");
					start = start.replace(")", "");
					start = start.replaceAll("[^0123456789]", "");
					if(start.equals("")) return;
					int section = Integer.valueOf(start);
					List<String> lore;
					ItemMeta meta = result.getItemMeta();
					if(meta.hasLore()) {
						lore = meta.getLore();
					} else {
						lore = new ArrayList<String>();
					}
					while(lore.size() < section) {
						lore.add("");
					}
					String finish = rename;
					start = rename.substring(rename.indexOf("(l"), rename.indexOf(")") + 1);
					finish = finish.replace(start, "");
					if(section <= 0) return;
					
					finish = ColorYMLManager.format(finish);
					finish = Utils.checkColorsEnabled(finish, plugin, p);
					if(plugin.getConfig().getBoolean("banWords")) {
						ArrayList<String> words = WordConfigManager.getWords();
						String test = new String(finish.toUpperCase());
						for(String word : words) {
							if(test.contains(word)) {
								finish = plugin.getConfig().getString("banWordName");
								break;
							}
						}
					}
					
					lore.set(section-1 , finish);
					meta.setLore(lore);
					ItemStack input = e.getInventory().getItem(0);
					if(input.hasItemMeta() && input.getItemMeta().hasDisplayName()){
						String itemName = input.getItemMeta().getDisplayName();
						meta.setDisplayName(itemName);
					}
					result.setItemMeta(meta);
					return;
				}

			} else if (e.getInventory().getRenameText().startsWith("(r")) {
				if(p.hasPermission("ca.setLoreRange") || plugin.getConfig().getBoolean("disable_Permissions")) {
					ItemStack result = e.getResult();
					String rename = e.getInventory().getRenameText();
					if(!(rename.contains(")"))) return;
					String start = rename.substring(rename.indexOf("(r"), rename.indexOf(")") + 1);
					start = start.replace("(r", "");
					start = start.replace(")", "");
					start = start.replaceAll("[^0123456789]", "");
					if(start.equals("")) return;
					int section = Integer.valueOf(start);
					List<String> lore;
					ItemMeta meta = result.getItemMeta();
					if(meta.hasLore()) {
						lore = meta.getLore();
					} else {
						return;
					}
					ItemStack input = e.getInventory().getItem(0);
					if(input.hasItemMeta() && input.getItemMeta().hasDisplayName()){
						String itemName = input.getItemMeta().getDisplayName();
						meta.setDisplayName(itemName);
					}

					while (lore.size() > section) {
						lore.remove(lore.size() -1);
					}
					meta.setLore(lore);
					result.setItemMeta(meta);
					return;
				}

			} else if (e.getInventory().getRenameText().startsWith("(if")) {
				if(p.hasPermission("ca.useItemFlags") || plugin.getConfig().getBoolean("disable_Permissions") == true) {
					ItemStack result = e.getResult();
					String rename = e.getInventory().getRenameText();
					if(!(rename.contains(")"))) return;
					String start = rename.substring(rename.indexOf("(if"), rename.indexOf(")") + 1);
					start = start.replace("(if", "");
					start = start.replace(")", "");
					start = start.replaceAll("[^0123456789]", "");
					if(start.equals("")) return;
					int value = Integer.valueOf(start);
					ItemMeta meta = result.getItemMeta();
					ItemStack input = e.getInventory().getItem(0);
					if(input.hasItemMeta() && input.getItemMeta().hasDisplayName()){
						String itemName = input.getItemMeta().getDisplayName();
						meta.setDisplayName(itemName);
					}

					for(ItemFlag flag : ItemFlag.values()) {
						if(flag.ordinal() == value) {
							if(input.getItemMeta().getItemFlags().contains(flag)) {
								meta.removeItemFlags(flag);
							} else {
								meta.addItemFlags(flag);
							}
						}
					}


					result.setItemMeta(meta);
					return;
				}

			} else if (e.getInventory().getRenameText().startsWith("(unbreak)")) {
				if(p.hasPermission("ca.unbreak") || plugin.getConfig().getBoolean("disable_Permissions")) {
					ItemStack result = e.getResult();
					ItemMeta meta = result.getItemMeta();
					ItemStack input = e.getInventory().getItem(0);
					if(input.hasItemMeta() && input.getItemMeta().hasDisplayName()){
						String itemName = input.getItemMeta().getDisplayName();
						meta.setDisplayName(itemName);
					}
					if(input.getItemMeta().isUnbreakable()) {
						meta.setUnbreakable(false);
					} else {
						meta.setUnbreakable(true);
					}
					result.setItemMeta(meta);
					return;
					
					
				}
			} else if(p.hasPermission("ca.use") || plugin.getConfig().getBoolean("disable_Permissions")) {
				ItemStack result = e.getResult();
				ItemMeta resultMeta = result.getItemMeta();

				String nameColored = e.getInventory().getRenameText();
				
				nameColored = ColorYMLManager.format(nameColored);
				nameColored = Utils.checkColorsEnabled(nameColored, plugin, p);
				if(plugin.getConfig().getBoolean("banWords")) {
					ArrayList<String> words = WordConfigManager.getWords();
					String test = new String(nameColored.toUpperCase());
					for(String word : words) {
						if(test.contains(word)) {
							nameColored = plugin.getConfig().getString("banWordName");
							break;
						}
					}
				}
				resultMeta.setDisplayName(nameColored);
				result.setItemMeta(resultMeta);
				return;
			}
		}
	}

}