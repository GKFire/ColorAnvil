package tech.gkfiredev.colouranvil.listener;

import tech.gkfiredev.colouranvil.ColorAnvil;
import tech.gkfiredev.colouranvil.api.Argument;
import tech.gkfiredev.colouranvil.files.BannedWordsManager;
import tech.gkfiredev.colouranvil.files.MessagesManager;
import tech.gkfiredev.colouranvil.manager.Arguments;
import tech.gkfiredev.colouranvil.manager.CustomColoursManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ColourAnvilListener implements Listener {

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent ev) {
        Player pla = (Player) ev.getView().getPlayer();
        AnvilInventory inv = ev.getInventory();
        String rename = inv.getRenameText();
        ItemStack input = inv.getItem(0);
        ItemStack result = ev.getResult();
        if(result == null) return;

        StringBuilder builder = new StringBuilder(rename);
        /*

        What this does is check the Rename text for a prefix value, for instance "(ls) This would show up in lore"
        it will check if a ( is the first char of the String, then check if there is a closing bracket as well.

        Then it will attempt to only get what is in the brackets. so "ls"

        Next it will check if there is a : inside, this is to separate the prefix from the argument
        It will then attempt to create a String list with the 2 separate values; "ls:32" => ["ls","32"]

        Then it will figure out which Argument has that prefix, then it will call the arguments execution and pass in the ItemStack and the argument.


         */
        if(builder.indexOf("(") == 0){
             if(builder.indexOf(")") != -1) { // The StringBuilder will return -1 if the character is not found in the string.
                String prefix = builder.substring(builder.indexOf("(") + 1, builder.indexOf(")"));
                rename = rename.substring(rename.indexOf(")") + 1);
                ItemStack item = null;
                if(prefix.indexOf(":") != -1) {
                    String[] values = prefix.split(":");

                    if(values.length == 0) return;
                    if(Arguments.getArgument(values[0]) != null) {
                        item = Arguments.getArgument(values[0]).onExecute(pla, input, result, (values.length == 1 ? null : values[1]), rename);
                    }
                } else {
                    if(Arguments.getArgument(prefix) != null) {
                        Argument argument = Arguments.getArgument(prefix);
                        item = argument.onExecute(pla, input, result, null, rename);
                    }
                }
                if(item != null) {
                    ev.setResult(item);
                }
                return;
            }
        }
        ItemMeta meta = result.getItemMeta();
        for(String word : BannedWordsManager.getBannedWords()) {
            if(rename.toLowerCase().contains(word.toLowerCase())) {
                rename = MessagesManager.cfg.getString("warn.banned_word");
                meta.setDisplayName(rename);
                result.setItemMeta(meta);
                ev.setResult(result);
                return;
            }
        }
        String colorName = rename;
        colorName = CustomColoursManager.implementCustomColors(colorName);
        colorName = ColorAnvil.implementColours(pla, colorName);
        meta.setDisplayName(colorName);
        result.setItemMeta(meta);
        ev.setResult(result);

    }

}
