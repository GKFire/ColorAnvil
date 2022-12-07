package tech.gkfiredev.colouranvil;

import org.bukkit.Bukkit;
import tech.gkfiredev.colouranvil.files.BannedWordsManager;
import tech.gkfiredev.colouranvil.files.ColourConfigManager;
import tech.gkfiredev.colouranvil.files.CustomColoursConfigManager;
import tech.gkfiredev.colouranvil.files.MessagesManager;
import tech.gkfiredev.colouranvil.implement.BaseArguments;
import tech.gkfiredev.colouranvil.implement.ColourCode;
import tech.gkfiredev.colouranvil.listener.ColourAnvilListener;
import tech.gkfiredev.colouranvil.manager.ColourAnvilCommand;
import tech.gkfiredev.colouranvil.manager.UpdateManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

/*

Registry Manager mostly.

 */



public final class ColorAnvil extends JavaPlugin {

    public static final Permission mainPermission = new Permission("ca.*");
    public static final Permission colourPermission = new Permission("ca.colour.*");


    public static boolean newerThan1_15 = false;

    @Override
    public void onEnable() {

        getLogger().info("Registering Base Arguments.");
        BaseArguments.registerBaseArguments();
        registerColors();
        getLogger().info("Setting up Config Files..");
        ColourConfigManager.setupConfig(this);
        BannedWordsManager.setupConfig(this);
        CustomColoursConfigManager.setupConfig(this);
        MessagesManager.setupConfig(this);
        getLogger().info("Setting up Listener...");
        getServer().getPluginManager().registerEvents(new ColourAnvilListener(), this);
        getCommand("ca").setExecutor(new ColourAnvilCommand());
        getCommand("ca").setTabCompleter(new ColourAnvilCommand());
        getLogger().info("Determining if server can use Custom Colors....");
        if (newerThan1_15()) {
            getLogger().info("Custom Colors are active");
            newerThan1_15 = true;
        } else {
            getLogger().warning("Server Version is too old for Custom Colors");
        }
        mainPermission.setDefault(PermissionDefault.OP);
        colourPermission.addParent(mainPermission,true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }




    public static String implementColours(Player pla, String s) {
        for(ColourCode colour : ColourCode.getColourList()) {
            if(colour.hasPermission(pla) && ColourConfigManager.isColourEnabled(colour)) s = s.replaceAll("&" + colour.getCharacter().toString(), "§" + colour.getCharacter().toString());
        }
        return s;
    }


    private static void registerColors() {
        for(ChatColor color : ChatColor.values()) {
            ColourCode colourCode = new ColourCode(color.getChar(), color.name());
        }
    }

    public static boolean newerThan1_15() {
        String buffer = Bukkit.getVersion();
        int i = 0;
        for (; i < buffer.length(); i ++) {
            if (buffer.charAt(i) == '.') break;
        }
        if (i >= buffer.length()) return false;

        int j = i;
        for (; j < buffer.length(); j++) {
            char ch = buffer.charAt(j);
            if (ch == ')' || ch == '-') break;
        }
        if (j >= buffer.length()) return false;
        String versionS = buffer.substring(i + 1, j);
        int version = Integer.parseInt(versionS);
        if (version <= 15) return false;
        return true;
    }
}
