package io.github.gkfiredev.colouranvil.api;

import io.github.gkfiredev.colouranvil.manager.CustomColoursManager;

public class CustomColour {

    private final Character character;
    private String hexValue;

    public CustomColour(Character character, String hexValue) {
        this.character = character;
        this.hexValue = hexValue;
    }

    public Character getCharacter() {
        return character;
    }

    public String getHexValue() {
        return hexValue;
    }

    public final void register() {
        CustomColoursManager.registerToList(this);
    }

}
