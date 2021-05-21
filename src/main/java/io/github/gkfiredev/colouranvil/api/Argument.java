package io.github.gkfiredev.colouranvil.api;

import io.github.gkfiredev.colouranvil.manager.Arguments;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Argument {

    private String prefix;

    public Argument(String prefix) {
        this.prefix = prefix;
    }

    public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String args, String renameText) {
        return result;
    }

    public String getPrefix() {
        return prefix;
    }


    public final void register() {
        for (Argument argument : Arguments.arguments) {
            if(argument.getPrefix().equals(this.getPrefix())) return;
        }
        Arguments.arguments.add(this);
    }

}
