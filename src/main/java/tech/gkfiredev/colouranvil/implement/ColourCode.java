package tech.gkfiredev.colouranvil.implement;

import tech.gkfiredev.colouranvil.ColorAnvil;
import tech.gkfiredev.colouranvil.files.ColourConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;

public class ColourCode {

    static ArrayList<ColourCode> colours = new ArrayList<>();

    private Character character;
    private Permission permission;
    private String nameID;


    public ColourCode(Character character, String nameID) {
        this.character = character;
        this.permission = new Permission("ca.colour." + nameID.toLowerCase());
        this.permission.addParent(ColorAnvil.colourPermission, true);
        this.nameID = nameID.toLowerCase();
        this.register();
    }


    public Character getCharacter() {
        return character;
    }

    public Permission getPermission() {
        return permission;
    }

    public String getNameID() {
        return nameID;
    }


    private final boolean register() {
        for(ColourCode colour : colours) {
            if(colour.getCharacter().equals(this.getCharacter())) return false;
        }
        colours.add(this);
        return true;
    }


    public boolean hasPermission(Player pla) {
        return (pla.hasPermission(this.getPermission()) || pla.hasPermission("ca.colour.*") || pla.isOp() || ColourConfigManager.cfg.getBoolean("disable_colour_permissions"));
    }

    public static ArrayList<ColourCode> getColourList() {
        return colours;
    }



}
