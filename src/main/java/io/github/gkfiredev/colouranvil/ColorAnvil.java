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
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class ColorAnvil extends JavaPlugin {




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
