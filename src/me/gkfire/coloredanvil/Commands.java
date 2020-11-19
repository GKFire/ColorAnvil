package me.gkfire.coloredanvil;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Commands implements CommandExecutor, TabCompleter {
	
	private Main plugin;
	
	public Commands(Main pl) {
		plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
				if(args.length == 1) {
					switch(args[0]) {
					case "list":
						if(p.hasPermission("ca.use") || plugin.getConfig().getBoolean("disable_Permissions")) {
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-------&c&lColors&7-------"));
							if(plugin.getConfig().getBoolean("DARK_RED") == true) {
								p.sendMessage("&4 = " + ChatColor.DARK_RED + "Dark Red");
							}
							if(plugin.getConfig().getBoolean("RED") == true) {
								p.sendMessage("&c = " + ChatColor.RED + "Red");
							}
							if(plugin.getConfig().getBoolean("GOLD") == true) {
								p.sendMessage("&6 = " + ChatColor.GOLD + "Gold");
							}
							if(plugin.getConfig().getBoolean("YELLOW") == true) {
								p.sendMessage("&e = " + ChatColor.YELLOW + "Yellow");
							}
							if(plugin.getConfig().getBoolean("DARK_GREEN") == true) {
								p.sendMessage("&2 = " + ChatColor.DARK_GREEN + "Dark Green");
							}
							if(plugin.getConfig().getBoolean("GREEN") == true) {
								p.sendMessage("&a = " + ChatColor.GREEN + "Green");
							}
							if(plugin.getConfig().getBoolean("AQUA") == true) {
								p.sendMessage("&b = " + ChatColor.AQUA + "Aqua");
							}
							if(plugin.getConfig().getBoolean("DARK_AQUA") == true) {
								p.sendMessage("&3 = " + ChatColor.DARK_AQUA + "Dark Aqua");
							}
							if(plugin.getConfig().getBoolean("DARK_BLUE") == true) {
								p.sendMessage("&1 = " + ChatColor.DARK_BLUE + "Dark Blue");
							}
							if(plugin.getConfig().getBoolean("BLUE") == true) {
								p.sendMessage("&9 = " + ChatColor.BLUE + "Blue");
							}
							if(plugin.getConfig().getBoolean("LIGHT_PURPLE") == true) {
								p.sendMessage("&d = " + ChatColor.LIGHT_PURPLE + "Light Purple");
							}
							if(plugin.getConfig().getBoolean("DARK_PURPLE") == true) {
								p.sendMessage("&5 = " + ChatColor.DARK_PURPLE + "Dark Purple");
							}
							if(plugin.getConfig().getBoolean("WHITE") == true) {
								p.sendMessage("&f = " + ChatColor.WHITE + "White");
							}
							if(plugin.getConfig().getBoolean("GRAY") == true) {
								p.sendMessage("&7 = " + ChatColor.GRAY + "Gray");
							}
							if(plugin.getConfig().getBoolean("DARK_GRAY") == true) {
								p.sendMessage("&8 = " + ChatColor.DARK_GRAY + "Dark Gray");
							}
							if(plugin.getConfig().getBoolean("BLACK") == true) {
								p.sendMessage("&0 = " + ChatColor.BLACK + "Black");
							}
							p.sendMessage("");
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-------&c&lFonts&7-------"));
							if(plugin.getConfig().getBoolean("BOLD") == true) {
								p.sendMessage("&L = " + ChatColor.BOLD + "Bold");
							}
							if(plugin.getConfig().getBoolean("ITALIC") == true) {
								p.sendMessage("&O = " + ChatColor.ITALIC + "Italic");
							}
							if(plugin.getConfig().getBoolean("UNDERLINE") == true) {
								p.sendMessage("&N = " + ChatColor.UNDERLINE + "UnderLine");
							}
							if(plugin.getConfig().getBoolean("STRIKE") == true) {
								p.sendMessage("&M = " + ChatColor.STRIKETHROUGH + "Strike");
							}
							if(plugin.getConfig().getBoolean("MAGIC") == true) {
								p.sendMessage("&K = " + ChatColor.MAGIC + "Random" + ChatColor.RESET + " Random");
							}
							p.sendMessage("&R Resets all color codes before this");
							p.sendMessage("");
						} else {
							p.sendMessage(ChatColor.RED + "You do not have permission To execute this Command!");
						}
						break;
					case "info":
						p.sendMessage(Main.prefix + ChatColor.WHITE + "ColorAnvil was developed by RavenRXW (aka GKFire)");
						p.sendMessage(Main.prefix + ChatColor.WHITE + "You can find updates to ColorAnvil at:");
						TextComponent spigot = new TextComponent("Spigot");
						spigot.setColor(ChatColor.GOLD);
						spigot.setBold(true);
						spigot.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/coloranvil.65062/"));
						spigot.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go To ColorAnvil's Spigot Page").color(ChatColor.GOLD).create()));
						p.spigot().sendMessage(spigot);
						TextComponent git = new TextComponent("Github");
						git.setColor(ChatColor.GRAY);
						git.setBold(true);
						git.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/FlareRXW/ColorAnvil"));
						git.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Go To ColorAnvil's Github Page").color(ChatColor.GRAY).create()));
						p.spigot().sendMessage(git);
						p.sendMessage(Main.prefix + ChatColor.WHITE + "ColorAnvil Version: " + plugin.getDescription().getVersion());
						break;
					case "reload":
						if(p.hasPermission("ca.configreload")) {
							try {
								plugin.reloadConfig();
								p.sendMessage(Main.prefix + ChatColor.GREEN + "ColorAnvil Config Reloaded!");
							} catch (Exception e) {
								p.sendMessage(Main.prefix + ChatColor.RED + "An Error Happend When Trying To Reload The Config.");
							}
							try {
								WordConfigManager.loadConfig();
							} catch (Exception e) {
								p.sendMessage(Main.prefix + "An Error Happend When Trying To Reload words.yml");
							}
						} else {
							p.sendMessage(Main.prefix + ChatColor.RED + "You Do Not Have Permission To Reload The Configs");
						}
						break;
					default:
						p.sendMessage(Main.prefix + ChatColor.RED + "Invalid Usage!! Valid Usages: list, reload");
						break;
					}
				} else {
					p.sendMessage(Main.prefix + ChatColor.GREEN + "Usage: /ca list");
				}

		}
		return true;
	}
	
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> tab = null;
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("ca.configreload") || p.isOp()) {
				tab = Arrays.asList("list", "reload" , "info");
			} else {
				tab = Arrays.asList("list", "info");
			}
		} else {
			tab = Arrays.asList("list", "reload");
		}
		List<String> tlist = Lists.newArrayList();
		if(args.length == 1) {
			for(String i : tab) {
				if(i.toLowerCase().startsWith(args[0].toLowerCase())) tlist.add(i);
			}
			return tlist;
		}
		
		
		return null;
	}
}
