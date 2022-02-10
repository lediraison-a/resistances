package com.almath.resistancesihm.utils;

/**
 * The type Calcul resistance.
 * this class contains a static function to compute the resistor value
 */
public class CalculResistance {

    /**
     * Gets resistance.
     *
     * @param n1             the n 1 first significant number
     * @param n2             the n 2 second significant number
     * @param n3             the n 3 third significant number
     * @param multiplicateur the multiplier
     * @return the resistance
     */
    public static double getResistance (
            Integer n1,
            Integer n2,
            Integer n3,
            Integer multiplicateur) {
        var chiffre = 0;
        // different calculus if 2 or 3 significant number.
        if(n3 != null) {
            chiffre = (n1 * 100) + (n2 * 10) + n3;
        } else {
            chiffre = (n1 * 10) + n2;
        }

        // 3 first ring * multiplier
        return chiffre * Math.pow(10.0, multiplicateur);
    }
}
