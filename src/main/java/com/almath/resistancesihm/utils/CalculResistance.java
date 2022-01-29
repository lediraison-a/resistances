package com.almath.resistancesihm.utils;

import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;

public class CalculResistance {

    public static final int MIN_NB_COULEURS = 3;
    public static final int MAX_NB_COULEURS = 5;

    public static double getResistance (
            int nbCouleursCalcul,
            int n1,
            int n2,
            int n3,
            int multiplicateur) {

        // la valeur des 3 premiers anneaux
        int chiffre = 0;
        // pour le calcul des 3 premiers anneaux
        double nbChffreMult = 1;

        // Si il y'a plus de 3 anneaux sur la resistance, on ajoute la valeur du 3 eme annneau au chiffre
        if(nbCouleursCalcul > 3) {
            chiffre += n3;
            nbChffreMult = 10;
        }
        // valeur des deux premiers anneaux
        chiffre += (n1 * (nbChffreMult * 10)) + (n2 * (nbChffreMult));

        // 3 premiers anneaux x multiplicateur
        return chiffre * Math.pow(10.0, multiplicateur);
    }
}
