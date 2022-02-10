package com.almath.resistancesihm.utils.Constantes;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Convert data.
 */
public class ConvertData {
    /**
     * The constant CONVERT_DATA.
     */
    public static final Map<String, Integer> CONVERT_DATA = new HashMap<>() {{
        put("Nanoohm nΩ", 9);
        put("Microohm µΩ", 6);
        put("Milliohm mΩ", 3);
        put("Ohm Ω", 0);
        put("Kiloohm kΩ", -3);
        put("Mégaohm mΩ", -6);
        put("Gigaohm GΩ", -9);
    }};
}
