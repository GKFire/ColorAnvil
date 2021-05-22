package io.github.gkfiredev.colouranvil.implement;

import io.github.gkfiredev.colouranvil.api.CustomColour;

public class BaseCustomColours {

    public static void registerCustomColours() {
        new CustomColour('v', "#3b8700").register();
        new CustomColour('t', "#33fff8").register();
    }

}
