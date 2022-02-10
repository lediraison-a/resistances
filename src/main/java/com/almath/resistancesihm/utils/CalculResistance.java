package com.almath.resistancesihm.utils;

/**
 * The type Calcul resistance.
 */
public class CalculResistance {

    /**
     * Gets resistance.
     *
     * @param n1             the n 1
     * @param n2             the n 2
     * @param n3             the n 3
     * @param multiplicateur the multiplicateur
     * @return the resistance
     */
    public static double getResistance (
            Integer n1,
            Integer n2,
            Integer n3,
            Integer multiplicateur) {
        var chiffre = 0;
        if(n3 != null) {
            chiffre = (n1 * 100) + (n2 * 10) + n3;
        } else {
            chiffre = (n1 * 10) + n2;
        }

        // 3 premiers anneaux x multiplicateur
        return chiffre * Math.pow(10.0, multiplicateur);
    }
}
