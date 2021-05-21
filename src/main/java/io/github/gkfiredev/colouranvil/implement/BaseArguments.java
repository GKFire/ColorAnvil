package io.github.gkfiredev.colouranvil.implement;

import io.github.gkfiredev.colouranvil.ColorAnvil;
import io.github.gkfiredev.colouranvil.api.Argument;
import io.github.gkfiredev.colouranvil.files.ColourConfigManager;
import io.github.gkfiredev.colouranvil.manager.CustomColoursManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BaseArguments {

    public static void registerBaseArguments() {
        Argument lore = new Argument("l") {
            @Override
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String args, String renameText) {
                if (!result.hasItemMeta()) return result;
                ItemMeta meta = result.getItemMeta();
                Integer loreSlot = (meta.getLore() == null ? 0 : meta.getLore().size());

                if(args != null) {
                    args.replaceAll("[^0-9]", "");
                    if(!args.equals("")) {
                        int limit = ColourConfigManager.cfg.getInt("max_lore_count");
                        loreSlot = (Integer.valueOf(args) > limit ? limit : Integer.valueOf(args));
                    }
                }
                List<String> lore = (meta.getLore() == null ? new ArrayList<String>() : meta.getLore());
                while (lore.size() < loreSlot +1) {
                    lore.add("");
                }
                String colorText = renameText;
                colorText = ColorAnvil.implementColours(player, colorText);
                colorText = CustomColoursManager.implementCustomColors(colorText);
                lore.set(loreSlot, colorText);
                meta.setLore(lore);
                meta.setDisplayName((input.getItemMeta().getDisplayName() == null ? input.getType().name() : input.getItemMeta().getDisplayName()));
                result.setItemMeta(meta);

                return result;
            }
        };
        lore.register();

        Argument unbreakable = new Argument("unbreak") {
            @Override
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String args, String renameText) {
                if(player.hasPermission("ca.unbreak") || player.hasPermission("ca.*")) {
                    ItemMeta meta = result.getItemMeta();
                    meta.setUnbreakable(!(meta.isUnbreakable()));
                    meta.setDisplayName((input.getItemMeta().getDisplayName() == null ? input.getType().name() : input.getItemMeta().getDisplayName()));
                    result.setItemMeta(meta);

                }
                return result;
            }
        };
        unbreakable.register();
    }




}
