package tech.gkfiredev.colouranvil.manager;

import tech.gkfiredev.colouranvil.ColorAnvil;
import tech.gkfiredev.colouranvil.api.CustomColour;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

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
        if(ColorAnvil.newerThan1_15) {
            for(CustomColour colour : customColourArrayList) {
                s = s.replaceAll("&" + colour.getCharacter().toString(), ChatColor.of(colour.getHexValue()) + "");
            }
        }

        return s;
    }

    public static List<CustomColour> getCustomColourList() {
        return customColourArrayList;
    }

}
