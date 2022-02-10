package com.almath.resistancesihm.models;

/**
 * The enum Anneau.
 * This enum represent a ring of the resistor. There are 6 ring on the resistor
 * this enum contains the default color for a ring.
 */
public enum Anneau {
    /**
     * N 1 anneau. The first significant number
     */
    N1(CouleurResistance.MARRON),
    /**
     * N 2 anneau. The second significant number
     */
    N2(CouleurResistance.VERT),
    /**
     * N 3 anneau. The third significant number
     */
    N3(CouleurResistance.ROUGE),
    /**
     * Mult anneau. The multiplier
     */
    MULT(CouleurResistance.OR),
    /**
     * Toler anneau. The tolerance
     */
    TOLER(CouleurResistance.ABSENT),
    /**
     * Temp anneau. the temperature
     */
    TEMP(CouleurResistance.ABSENT);

    /**
     * The Valeur depart.
     */
    private CouleurResistance valeurDepart;

    /**
     * Instantiates a new Anneau.
     *
     * @param valeurDepart the valeur depart
     */
    Anneau(CouleurResistance valeurDepart) {
        this.valeurDepart = valeurDepart;
    }

    /**
     * Gets valeur depart.
     *
     * @return the valeur depart, the default value of the ring.
     */
    public CouleurResistance getValeurDepart() {
        return valeurDepart;
    }
}
