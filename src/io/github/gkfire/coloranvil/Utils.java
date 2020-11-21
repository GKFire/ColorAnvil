package io.github.gkfire.coloranvil;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class Utils {
	
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}


	public static String checkColorsEnabled(String loreColored, Plugin plugin, Player p) {
		if(plugin.getConfig().getBoolean("DARK_RED") == true) {
			if(p.hasPermission("ca.use.DARK_RED") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&4", "§4");
			}
		}
		if(plugin.getConfig().getBoolean("RED") == true) {
			if(p.hasPermission("ca.use.RED") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&c", "§c");
				loreColored = loreColored.replaceAll("&C", "§c");
			}
		}
		if(plugin.getConfig().getBoolean("GOLD") == true) {
			if(p.hasPermission("ca.use.GOLD") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&6", "§6");
			}
		}
		if(plugin.getConfig().getBoolean("YELLOW") == true) {
			if(p.hasPermission("ca.use.YELLOW") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&e", "§e");
				loreColored = loreColored.replaceAll("&E", "§e");
			}
		}
		if(plugin.getConfig().getBoolean("DARK_GREEN") == true) {
			if(p.hasPermission("ca.use.DARK_GREEN") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&2", "§2");
			}
		}
		if(plugin.getConfig().getBoolean("GREEN") == true) {
			if(p.hasPermission("ca.use.GREEN") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&a", "§a");
				loreColored = loreColored.replaceAll("&A", "§a");
			}
		}
		if(plugin.getConfig().getBoolean("AQUA") == true) {
			if(p.hasPermission("ca.use.AQUA") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&b", "§b");
				loreColored = loreColored.replaceAll("&B", "§b");
			}
		}
		if(plugin.getConfig().getBoolean("DARK_AQUA") == true) {
			if(p.hasPermission("ca.use.DARK_AQUA") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&3", "§3");
			}
		}
		if(plugin.getConfig().getBoolean("DARK_BLUE") == true) {
			if(p.hasPermission("ca.use.DARK_BLUE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&1", "§1");
			}
		}
		if(plugin.getConfig().getBoolean("BLUE") == true) {
			if(p.hasPermission("ca.use.BLUE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&9", "§9");
			}
		}
		if(plugin.getConfig().getBoolean("LIGHT_PURPLE") == true) {
			if(p.hasPermission("ca.use.LIGHT_PURPLE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&d", "§d");
				loreColored = loreColored.replaceAll("&D", "§d");
			}
		}
		if(plugin.getConfig().getBoolean("DARK_PURPLE") == true) {
			if(p.hasPermission("ca.use.DARK_PURPLE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&5", "§5");
			}
		}
		if(plugin.getConfig().getBoolean("WHITE") == true) {
			if(p.hasPermission("ca.use.WHITE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&f", "§f");
				loreColored = loreColored.replaceAll("&F", "§f");
			}
		}
		if(plugin.getConfig().getBoolean("GRAY") == true) {
			if(p.hasPermission("ca.use.GRAY") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&7", "§7");
			}
		}
		if(plugin.getConfig().getBoolean("DARK_GRAY") == true) {
			if(p.hasPermission("ca.use.DARK_GRAY") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&8", "§8");
			}
		}
		if(plugin.getConfig().getBoolean("BLACK") == true) {
			if(p.hasPermission("ca.use.BLACK") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&0", "§0");
			}
		}
		if(plugin.getConfig().getBoolean("BOLD") == true) {
			if(p.hasPermission("ca.use.BOLD") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&l", "§l");
				loreColored = loreColored.replaceAll("&L", "§l");
			}
		}
		if(plugin.getConfig().getBoolean("ITALIC") == true) {
			if(p.hasPermission("ca.use.ITALIC") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&o", "§o");
				loreColored = loreColored.replaceAll("&O", "§o");
			}
		}
		if(plugin.getConfig().getBoolean("UNDERLINE") == true) {
			if(p.hasPermission("ca.use.UNDERLINE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&n", "§n");
				loreColored = loreColored.replaceAll("&N", "§n");
			}
		}
		if(plugin.getConfig().getBoolean("STRIKE") == true) {
			if(p.hasPermission("ca.use.STRIKE") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&m", "§m");
				loreColored = loreColored.replaceAll("&M", "§m");
			}
		}
		if(plugin.getConfig().getBoolean("MAGIC") == true) {
			if(p.hasPermission("ca.use.MAGIC") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&k", "§k");
				loreColored = loreColored.replaceAll("&K", "§k");
			}
		}
		if(plugin.getConfig().getBoolean("RESET") == true) {
			if(p.hasPermission("ca.use.RESET") || p.hasPermission("ca.use.*") || plugin.getConfig().getBoolean("disable_Permissions")) {
				loreColored = loreColored.replaceAll("&r", "§r");
				loreColored = loreColored.replaceAll("&R", "§r");
			}
		}
		
		return loreColored;
	}
}
