package com.almath.resistancesihm.models;

public enum CouleurResistance {
    NOIR("BK", 0, "#000000"),
    MARRON("BN", 1, "#944a00"),
    ROUGE("RD", 2, "#ff0000"),
    ORANGE("OG", 3, "#ff8000"),
    JAUNE("YE", 4, "#ffff00"),
    VERT("GN", 5, "#00ff00"),
    BLEU("BU", 6, "#0000ff"),
    VIOLET("VT", 7, "#9000ff"),
    GRIS("GY", 8, "#808080"),
    BLANC("WH", 9, "#ffffff"),
    OR("GD", 11, "#d4af35"),
    ARGENT("SR", 12, "#bfbfbf"),
    ABSENT("", -1, "#ffffff");

    private String abrev, couleurWeb;
    private int ordre;

    CouleurResistance(String abrev, int ordre, String couleurWeb) {
        this.abrev = abrev;
        this.ordre = ordre;
        this.couleurWeb = couleurWeb;
    }

    public String getAbrev() {
        return abrev;
    }

    public String getCouleurWeb() {
        return couleurWeb;
    }

    public int getOrdre() {
        return ordre;
    }
}
