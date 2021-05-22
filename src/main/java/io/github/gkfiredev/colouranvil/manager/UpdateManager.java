package io.github.gkfiredev.colouranvil.manager;

import io.github.gkfiredev.colouranvil.files.ColourConfigManager;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UpdateManager {

    public static int update = 0;

    /*
     * -1 == Dev Version
     * 0 == Unable To check
     * 1 == Requires Update
     * 2 == Up to date
     */


    public static int checkUpdate(Plugin plugin) {
        if(ColourConfigManager.cfg.getBoolean("check_update")) {
            String version = versionCheck();
            if(version != null) {
                String currentV = plugin.getDescription().getVersion();
                String currentVMod = currentV.replaceAll("[^0-9]", "");
                String versionMod = version.replaceAll("[^0-9]", "");
                if(currentVMod.length() > 1) {
                    currentVMod = currentVMod.substring(0, 1) + "." + currentVMod.substring(1);
                }
                if(versionMod.length() > 1) {
                    versionMod = versionMod.substring(0, 1) + "." + versionMod.substring(1);
                }
                float versionF = Float.parseFloat(versionMod);
                float currentF = Float.parseFloat(currentVMod);

                if(versionF > currentF) {
                    UpdateManager.update = 1;
                    return UpdateManager.update;
                } else if(versionF < currentF) {
                    UpdateManager.update = -1;
                    return UpdateManager.update;
                } else {
                    UpdateManager.update = 2;
                    return UpdateManager.update;
                }
            } else {
                UpdateManager.update = 0;
                return UpdateManager.update;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
