package io.github.gkfiredev.colouranvil;

import io.github.gkfiredev.colouranvil.files.BannedWordsManager;
import io.github.gkfiredev.colouranvil.files.ColourConfigManager;
import io.github.gkfiredev.colouranvil.files.CustomColoursConfigManager;
import io.github.gkfiredev.colouranvil.files.MessagesManager;
import io.github.gkfiredev.colouranvil.implement.BaseArguments;
import io.github.gkfiredev.colouranvil.implement.BaseCustomColours;
import io.github.gkfiredev.colouranvil.implement.ColourCode;
import io.github.gkfiredev.colouranvil.listener.ColourAnvilListener;
import io.github.gkfiredev.colouranvil.manager.ColourAnvilCommand;
import io.github.gkfiredev.colouranvil.manager.UpdateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class ColorAnvil extends JavaPlugin {

    public static final Permission mainPermission = new Permission("ca.*");
    public static final Permission colourPermission = new Permission("ca.colour.*");


    @Override
    public void onEnable() {
        getLogger().info("Registering Base Arguments.");
        BaseArguments.registerBaseArguments();
        BaseCustomColours.registerCustomColours();
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
        getLogger().info("Release Type: DEV-Build");


        mainPermission.setDefault(PermissionDefault.OP);
        colourPermission.addParent(mainPermission,true);

        int status = UpdateManager.checkUpdate(this);
        if(status == -1) {
            this.getLogger().info(MessagesManager.getPrefix() + ChatColor.GOLD + "You are Currently Using a Dev Version of ColorAnvil!");
        } else if(status == 1) {
            this.getLogger().info(MessagesManager.getPrefix() + ChatColor.YELLOW + "There is a newer version of ColorAnvil available on Spigot!");
        } else if(status == 2) {
            this.getLogger().info(MessagesManager.getPrefix() + ChatColor.GREEN + "ColorAnvil is up to date!");
        }

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
}
