package tech.gkfiredev.colouranvil.manager;

import tech.gkfiredev.colouranvil.ColorAnvil;
import tech.gkfiredev.colouranvil.api.CustomColour;
import tech.gkfiredev.colouranvil.files.BannedWordsManager;
import tech.gkfiredev.colouranvil.files.ColourConfigManager;
import tech.gkfiredev.colouranvil.files.CustomColoursConfigManager;
import tech.gkfiredev.colouranvil.files.MessagesManager;
import tech.gkfiredev.colouranvil.implement.ColourCode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColourAnvilCommand implements CommandExecutor, TabCompleter {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length > 0) {
            switch (args[0]) {
                case "list":
                    if(sender instanceof Player) {
                        Player pl = (Player) sender;
                        pl.sendMessage("§7---------§c§lColour Codes§r§7---------");
                        for(ColourCode colourCode : ColourCode.getColourList()) {
                            if(!ColourConfigManager.isColourEnabled(colourCode)) continue;
                            pl.sendMessage("&" + colourCode.getCharacter() + "   -   " + "§" + colourCode.getCharacter() + "SAMPLE TEXT");
                        }
                        pl.sendMessage("§7---------§c§lCustom Colours§r§7---------");
                        for(CustomColour colourCode : CustomColoursManager.getCustomColourList()) {
                            pl.sendMessage("&" + colourCode.getCharacter() + "   -   " + CustomColoursManager.implementCustomColors("&" + colourCode.getCharacter() + "SAMPLE TEXT"));
                        }

                    } else {
                        sender.sendMessage("Only players can execute this command.");
                    }
                    break;
                case "reload":
                    if(sender instanceof Player) {
                        Player pla = (Player) sender;
                        if(!pla.hasPermission("ca.*")) {
                            pla.sendMessage(MessagesManager.getPrefix() + MessagesManager.cfg.getString("error.command.permission"));
                            return true;
                        }
                    }
                    ColourConfigManager.loadConfig();
                    MessagesManager.loadConfig();
                    CustomColoursConfigManager.loadConfig();
                    CustomColoursConfigManager.loadCustomColours();
                    BannedWordsManager.loadConfig();
                    sender.sendMessage(MessagesManager.getPrefix() + "§e All configs have been reloaded!");
                    break;
                case "about":
                    if(sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage(MessagesManager.getPrefix() + "§fColourAnvil is developed by GKFire");
                        player.sendMessage(MessagesManager.getPrefix() + "§fServer local version is " + ColorAnvil.getPlugin(ColorAnvil.class).getDescription().getVersion());
                    } else {
                        sender.sendMessage("ColourAnvil version: " + ColorAnvil.getPlugin(ColorAnvil.class).getDescription().getVersion());
                        sender.sendMessage("Developed By: " + ColorAnvil.getPlugin(ColorAnvil.class).getDescription().getAuthors());
                    }
                    break;
            }
        }



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length == 1) {
            ArrayList<String> options = new ArrayList<String>(Arrays.asList("list", "reload", "about"));
            if(sender instanceof Player) {
                if(!((Player) sender).hasPermission("ca.*")) options.remove(1);
            }
            List<String> tab = new ArrayList<>();
            for(String s : options) {
                if(s.toLowerCase().startsWith(args[0].toLowerCase())) tab.add(s);
            }
            return tab;
        }

        return null;
    }
}
