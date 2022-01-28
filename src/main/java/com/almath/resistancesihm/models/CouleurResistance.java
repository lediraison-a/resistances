package com.almath.resistancesihm.models;

public enum CouleurResistance {
    NOIR("BK"),
    MARRON("BN"),
    ROUGE("RD"),
    ORANGE("OG"),
    JAUNE("YE"),
    VERT("GN"),
    BLEU("BU"),
    VIOLET("VT"),
    GRIS("GY"),
    BLANC("WH"),
    OR("GD"),
    ARGENT("SR"),
    ABSENT("");

    private String abrev;

    CouleurResistance(String abrev) {
        this.abrev = abrev;
    }

    public String getAbrev() {
        return abrev;
    }
}
