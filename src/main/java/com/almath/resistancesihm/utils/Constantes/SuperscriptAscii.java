package com.almath.resistancesihm.utils.Constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Superscript ascii.
 * this class contains a static map that give a superscript ascii from an integer number.
 * this is used to display power numbers.
 */
public class SuperscriptAscii {

    /**
     * The constant superscriptChars map.
     */
    public static final Map<Integer, String> superscriptChars = new HashMap<>() {{
        put(0, "\u2070");
        put(1, "\u00B9");
        put(2, "\u00B2");
        put(3, "\u00B3");
        put(4, "\u2074");
        put(5, "\u2075");
        put(6, "\u2076");
        put(7, "\u2077");
        put(8, "\u2078");
        put(9, "\u2079");
    }};
}
