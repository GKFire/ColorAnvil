package me.gkfire.coloredanvil;

import java.io.File;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.gkfire.coloredanvil.managers.CAUpdateManager;
import me.gkfire.coloredanvil.managers.ColorYMLManager;
import me.gkfire.coloredanvil.managers.WordConfigManager;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	FileConfiguration config;
	File cfile;
	
	public static String prefix = Utils.chat("&eColorAnvil&f> ");
	
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		cfile = new File(getDataFolder(), "config.yml");
		WordConfigManager.setup();
		WordConfigManager.loadConfig();
		ColorYMLManager.setup();
		getServer().getPluginManager().registerEvents(new ColorAnvil(this), this);
		getServer().getPluginManager().registerEvents(this, this);

		getCommand("ca").setExecutor(new Commands(this));
		getCommand("ca").setTabCompleter(new Commands(this));
		
		
		getLogger().log(Level.INFO, "-----[" + getDescription().getName() + "]-----");
		getLogger().log(Level.INFO, "");
		getLogger().log(Level.INFO, "Author:" + getDescription().getAuthors());
		getLogger().log(Level.INFO, "Version:" + getDescription().getVersion());
		getLogger().log(Level.INFO, "Follow Me on Spigot!");
		getLogger().log(Level.INFO, "Subscribe on Youtube!");
		getLogger().log(Level.INFO, "Follow Me on Twitch!");
		getLogger().log(Level.INFO, "");
		CAUpdateManager.checkUpdate(Main.getPlugin(Main.class));
		if(CAUpdateManager.update == 0) {
			getLogger().log(Level.INFO, "Unable to Connect To Spigot. Is Spigot Down? or is checking updates disabled?");
		} else if(CAUpdateManager.update == -1) {
			getLogger().log(Level.INFO, "This is a Dev Version. REASON: Version is greater than current release version!");
		} else if(CAUpdateManager.update == 1) {
			getLogger().log(Level.INFO, "There is a newer version of ColorAnvil available on Spigot!");
		} else if(CAUpdateManager.update == 2) {
			getLogger().log(Level.INFO, "ColorAnvil is up to date!");
		}
		
		getLogger().log(Level.INFO, "");
		getLogger().log(Level.INFO, "-----[" + getDescription().getName() + "]-----");
	}
	
	
	@EventHandler
	public void onAdminJoin(PlayerJoinEvent ev) {
		Player p = ev.getPlayer();
		if(p.isOp()) {
			if(CAUpdateManager.update == -1) {
				p.sendMessage(prefix + ChatColor.GOLD + "You are Currently Using a Dev Version of ColorAnvil!");
			} else if(CAUpdateManager.update == 1) {
				p.sendMessage(prefix + ChatColor.YELLOW + "There is a newer version of ColorAnvil available on Spigot!");
			} else if(CAUpdateManager.update == 2) {
				p.sendMessage(prefix + ChatColor.GREEN + "ColorAnvil is up to date!");
			}
		}
	}
}
