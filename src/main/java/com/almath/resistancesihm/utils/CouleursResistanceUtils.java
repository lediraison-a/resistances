package com.almath.resistancesihm.utils;

import com.almath.resistancesihm.models.CouleurResistance;

public class CouleursResistanceUtils {

    public static int getNbCouleursCalcul(CouleurResistance c3) {
        if(c3 != CouleurResistance.ABSENT) {
            return 3;
        } else return 4;
    }
}
