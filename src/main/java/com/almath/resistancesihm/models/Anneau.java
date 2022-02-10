package com.almath.resistancesihm.models;

/**
 * The enum Anneau.
 */
public enum Anneau {
    /**
     * N 1 anneau.
     */
    N1(CouleurResistance.MARRON),
    /**
     * N 2 anneau.
     */
    N2(CouleurResistance.VERT),
    /**
     * N 3 anneau.
     */
    N3(CouleurResistance.ROUGE),
    /**
     * Mult anneau.
     */
    MULT(CouleurResistance.OR),
    /**
     * Toler anneau.
     */
    TOLER(CouleurResistance.ABSENT),
    /**
     * Temp anneau.
     */
    TEMP(CouleurResistance.ABSENT);

    private CouleurResistance valeurDepart;

    Anneau(CouleurResistance valeurDepart) {
        this.valeurDepart = valeurDepart;
    }

    /**
     * Gets valeur depart.
     *
     * @return the valeur depart
     */
    public CouleurResistance getValeurDepart() {
        return valeurDepart;
    }
}
