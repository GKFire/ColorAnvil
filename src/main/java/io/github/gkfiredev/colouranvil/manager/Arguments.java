package io.github.gkfiredev.colouranvil.manager;

import io.github.gkfiredev.colouranvil.api.Argument;

import java.util.ArrayList;

public class Arguments {

    public static ArrayList<Argument> arguments = new ArrayList<Argument>();

    public static Argument getArgument(String prefix) {
        for(Argument arg : arguments) {
            if(arg.getPrefix().equals(prefix)) return arg;
        }
        return null;
    }



}
