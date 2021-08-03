package tech.gkfiredev.colouranvil.api;

import tech.gkfiredev.colouranvil.manager.Arguments;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/*
    V1.0

    Arguments is the system that i created to allow players to customize their items further with ease to both user and developer (myself).

    There is currently no way of allowing other plugins to use this as no maven repository exists with this plugin for others to import.

 */

public class Argument {

    private String prefix;

    public Argument(String prefix) {
        this.prefix = prefix;
    }


    // Executes when the AnvilListener finds the prefix in the name.
    public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String arg, String renameText) {
        return result;
    }

    public String getPrefix() {
        return prefix;
    }

    /*
     Registers this instance to the Arguments list. This is to help reduce duplicates or arguments with the same prefix.

     This function is set to 'final' to prevent alterations to it to bypass the registration.
     */
    public final void register() {
        for (Argument argument : Arguments.arguments) {
            if(argument.getPrefix().equals(this.getPrefix())) return;
        }
        Arguments.arguments.add(this);
    }

}
