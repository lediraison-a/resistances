package com.almath.resistancesihm.models;

/**
 * The enum Couleur resistance.
 * This enum represent a color selectable for a ring.
 * The enum contains also data associated with the color :
 * abbreviation of the color, order of the color in the color list, the web color
 *
 * https://codepen.io/dannyfritz/pen/DLmeMP
 * this codpen was used to get the html code of the colors. converted from hsla to hex
 *
 */
public enum CouleurResistance {
    /**
     * Noir couleur resistance.
     */
    NOIR("BK", 0, "#000000"),
    /**
     * Marron couleur resistance.
     */
    MARRON("BN", 1, "#944a00"),
    /**
     * Rouge couleur resistance.
     */
    ROUGE("RD", 2, "#ff0000"),
    /**
     * Orange couleur resistance.
     */
    ORANGE("OG", 3, "#ff8000"),
    /**
     * Jaune couleur resistance.
     */
    JAUNE("YE", 4, "#ffff00"),
    /**
     * Vert couleur resistance.
     */
    VERT("GN", 5, "#00ff00"),
    /**
     * Bleu couleur resistance.
     */
    BLEU("BU", 6, "#0000ff"),
    /**
     * Violet couleur resistance.
     */
    VIOLET("VT", 7, "#9000ff"),
    /**
     * Gris couleur resistance.
     */
    GRIS("GY", 8, "#808080"),
    /**
     * Blanc couleur resistance.
     */
    BLANC("WH", 9, "#ffffff"),
    /**
     * Or couleur resistance.
     */
    OR("GD", 11, "#d4af35"),
    /**
     * Argent couleur resistance.
     */
    ARGENT("SR", 12, "#bfbfbf"),
    /**
     * Absent couleur resistance.
     */
    ABSENT("", -1, "#ffffff");

    /**
     * The Abrev.
     */
    private String abrev, /**
     * Couleur web couleur resistance.
     */
    couleurWeb;
    /**
     * The Ordre.
     */
    private int ordre;

    /**
     * Instantiates a new Couleur resistance.
     *
     * @param abrev      the abrev
     * @param ordre      the ordre
     * @param couleurWeb the couleur web
     */
    CouleurResistance(String abrev, int ordre, String couleurWeb) {
        this.abrev = abrev;
        this.ordre = ordre;
        this.couleurWeb = couleurWeb;
    }

    /**
     * Gets abrev.
     *
     * @return the abrev
     */
    public String getAbrev() {
        return abrev;
    }

    /**
     * Gets couleur web.
     *
     * @return the couleur web
     */
    public String getCouleurWeb() {
        return couleurWeb;
    }

    /**
     * Gets ordre.
     *
     * @return the ordre
     */
    public int getOrdre() {
        return ordre;
    }
}
