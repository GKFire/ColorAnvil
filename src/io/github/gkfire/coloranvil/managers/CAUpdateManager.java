package io.github.gkfire.coloranvil.managers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.bukkit.plugin.Plugin;

public class CAUpdateManager {
	
	public static int update = 0;
	
	/* 
	 * -1 == Dev Version
	 * 0 == Unable To check
	 * 1 == Requires Update
	 * 2 == Up to date
	 */
	
	
	public static int checkUpdate(Plugin plugin) {
		if(plugin.getConfig().getBoolean("CheckForUpdates")) {
			String version = versionCheck();
			if(version != null) {
				String currentV = plugin.getDescription().getVersion();
				String currentVMod = currentV.replaceAll("[^0-9]", "");
				String versionMod = version.replaceAll("[^0-9]", "");
				if(currentVMod.length() > 1) {
					currentVMod = new String(currentVMod.substring(0, 1) + "." + currentVMod.substring(1));
				}
				if(versionMod.length() > 1) {
					versionMod = new String(versionMod.substring(0, 1) + "." + versionMod.substring(1));
				}
				float versionF = Float.parseFloat(versionMod);
				float currentF = Float.parseFloat(currentVMod);
				
				if(versionF > currentF) {
					CAUpdateManager.update = 1;
					return CAUpdateManager.update;
				} else if(versionF < currentF) {
					CAUpdateManager.update = -1;
					return CAUpdateManager.update;
				} else {
					CAUpdateManager.update = 2;
					return CAUpdateManager.update;
				}
			} else {
				CAUpdateManager.update = 0;
				return CAUpdateManager.update;
			}
			
		}
		return update;
	}
	
	
	@SuppressWarnings("resource")
	public static String versionCheck() {
		try {
			InputStream inputstream = new URL("https://api.spigotmc.org/legacy/update.php?resource=65062").openStream();
			Scanner scanner = new Scanner(inputstream);
			if(scanner.hasNext()) {
				return scanner.next();
			}
			return null;
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
		
	}
}
