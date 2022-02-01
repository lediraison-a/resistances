package com.almath.resistancesihm.models;

import java.util.HashMap;

public class ConvertData {
    private HashMap<String, Integer> mapConvert;

    public ConvertData(HashMap<String, Integer> mapConvert) {
        this.mapConvert = mapConvert;
    }

    public ConvertData() {
        this.mapConvert =  new HashMap<String, Integer>() {{
            put("Nanoohm nΩ", 9);
            put("Microohm µΩ", 6);
            put("Milliohm mΩ", 3);
            put("Ohm Ω", 0);
            put("Kiloohm kΩ", -3);
            put("Mégaohm mΩ", -6);
            put("Gigaohm GΩ", -9);
        }};
    }

    public HashMap<String, Integer> getMapConvert() {
        return mapConvert;
    }

    public void setMapConvert(HashMap<String, Integer> mapConvert) {
        this.mapConvert = mapConvert;
    }
}
