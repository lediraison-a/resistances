package com.almath.resistancesihm.utils;

public class CalculResistance {

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
