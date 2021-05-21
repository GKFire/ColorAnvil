package io.github.gkfiredev.colouranvil.implement;

import io.github.gkfiredev.colouranvil.ColorAnvil;
import io.github.gkfiredev.colouranvil.api.Argument;
import io.github.gkfiredev.colouranvil.files.BannedWordsManager;
import io.github.gkfiredev.colouranvil.files.ColourConfigManager;
import io.github.gkfiredev.colouranvil.files.MessagesManager;
import io.github.gkfiredev.colouranvil.manager.CustomColoursManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BaseArguments {

    public static void registerBaseArguments() {
        Argument lore = new Argument("l") {
            @Override
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String arg, String renameText) {
                if (!result.hasItemMeta()) return result;
                if(!ColourConfigManager.cfg.getBoolean("disable_lore_permissions") && !player.hasPermission("ca.colour.lore") && !player.hasPermission("ca.colour.*") && !player.isOp()) return result;
                ItemMeta meta = result.getItemMeta();
                Integer loreSlot = (meta.getLore() == null ? 0 : meta.getLore().size());
                if(arg != null) {
                    arg = arg.replaceAll("[^0-9]", "");
                    if(!arg.equals("")) {
                        int limit = ColourConfigManager.cfg.getInt("max_lore_count");
                        loreSlot = (Integer.valueOf(arg) > limit ? limit : Integer.valueOf(arg));
                    }
                }
                List<String> lore = (meta.getLore() == null ? new ArrayList<String>() : meta.getLore());
                while (lore.size() < loreSlot +1) {
                    lore.add("");
                }

                for(String word : BannedWordsManager.getBannedWords()) {
                    if(renameText.toLowerCase().contains(word.toLowerCase())) {
                        renameText = MessagesManager.cfg.getString("warn.banned_word");
                        meta.setDisplayName(renameText);
                        result.setItemMeta(meta);
                        return result;
                    }
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
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String arg, String renameText) {
                if(player.hasPermission("ca.unbreak") || player.hasPermission("ca.*") || player.isOp()) {
                    ItemMeta meta = result.getItemMeta();
                    meta.setUnbreakable(!(meta.isUnbreakable()));
                    meta.setDisplayName((input.getItemMeta().getDisplayName() == null ? input.getType().name() : input.getItemMeta().getDisplayName()));
                    result.setItemMeta(meta);

                }
                return result;
            }
        };
        unbreakable.register();
        Argument removeLore = new Argument("r") {
            @Override
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String arg, String renameText) {
                if(player.hasPermission("ca.colour.lore") || player.hasPermission("ca.colour.*") || player.isOp()) {
                    if(arg != null) {
                        arg = arg.replaceAll("[^0-9]", "");
                        if(arg.equals("")) return result;
                        if(result.hasItemMeta()) {
                            ItemMeta meta = result.getItemMeta();
                            meta.setDisplayName((input.getItemMeta().getDisplayName() == null ? input.getType().name() : input.getItemMeta().getDisplayName()));


                            if(result.getItemMeta().hasLore()) {
                                List<String> lore = meta.getLore();
                                int crop = Integer.valueOf(arg);
                                while (lore.size() > crop) {
                                    lore.remove(lore.size() -1);
                                }
                                meta.setLore(lore);
                                result.setItemMeta(meta);
                            }
                        }

                    }
                }
                return result;
            }
        };
        removeLore.register();
        Argument itemFlags = new Argument("if") {
            @Override
            public ItemStack onExecute(Player player, ItemStack input, ItemStack result, String arg, String renameText) {
                if(player.hasPermission("ca.itemflag") || player.hasPermission("ca.*") || player.isOp()) {
                    if(arg != null) {
                        ItemMeta meta = result.getItemMeta();
                        meta.setDisplayName((input.getItemMeta().getDisplayName() == null ? input.getType().name() : input.getItemMeta().getDisplayName()));


                        arg = arg.replaceAll("[^0-9]", "");
                        if(arg.equals("")) return result;
                        int selection = Integer.valueOf(arg);
                        ItemFlag[] values = ItemFlag.values();
                        if(selection >= values.length) return result;
                        if(meta.hasItemFlag(values[selection])) {
                            meta.removeItemFlags(values[selection]);
                        } else {
                            meta.addItemFlags(values[selection]);
                        }
                        result.setItemMeta(meta);

                    }
                }
                return result;
            }
        };
        itemFlags.register();
    }




}
