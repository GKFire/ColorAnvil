package io.github.gkfiredev.colouranvil.manager;

import io.github.gkfiredev.colouranvil.api.CustomColour;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomColoursManager {

    static ArrayList<CustomColour> customColourArrayList = new ArrayList<>();


    public static CustomColour getCustomColorFromCharacter(Character character) {
        for(CustomColour colour : customColourArrayList) {
            if(colour.getCharacter().equals(character)) return colour;
        }
        return null;
    }

    public static boolean registerToList(CustomColour colour) {
        for(CustomColour colourSection : customColourArrayList) {
            if(colourSection.getCharacter().equals(colour.getCharacter())) return false;
        }
        if(!colour.getHexValue().matches("#[a-fA-F0-9]{6}")) return false;
        customColourArrayList.add(colour);
        return true;
    }

    public static String implementCustomColors(String s) {
        if(Bukkit.getVersion().contains("1.16")) {
            for(CustomColour colour : customColourArrayList) {
                s = s.replaceAll("&" + colour.getCharacter().toString(), ChatColor.of(colour.getHexValue()) + "");
            }
        }

        return s;
    }

    public static List<CustomColour> getCustomColourList() {
        return new ArrayList<>(customColourArrayList);
    }

}
