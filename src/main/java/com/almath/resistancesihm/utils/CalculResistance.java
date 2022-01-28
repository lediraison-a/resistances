package com.almath.resistancesihm.utils;

import com.almath.resistancesihm.utils.Constantes.CouleurResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;

public class CalculResistance {

    public static final int MIN_NB_COULEURS = 3;
    public static final int MAX_NB_COULEURS = 5;

    public static double getResistance (
            int nbCouleurs,
            CouleurResistance c1,
            CouleurResistance c2,
            CouleurResistance c3,
            CouleurResistance cMultiplicateur) {

        // la valeur des 3 premiers anneaux
        int chiffre = 0;
        // pour le calcul des 3 premiers anneaux
        double nbChffreMult = 1;


        // Si il y'a plus de 3 anneaux sur la resistance, on ajoute la valeur du 3 eme annneau au chiffre
        if(nbCouleurs > 3) {
            chiffre += CouleursAnneaux.COULEURS_ANNEAU_3.get(c3);
            nbChffreMult = 10;
        }

        // valeur des deux premiers anneaux
        chiffre += (CouleursAnneaux.COULEURS_ANNEAU_1.get(c1) * (nbChffreMult * 10)) + (CouleursAnneaux.COULEURS_ANNEAU_2.get(c2) * (nbChffreMult));

        // valeur anneau multiplicateur
        double multiplicateur = Math.pow(10.0, CouleursAnneaux.COULEURS_ANNEAU_MULTIPLICATEUR.get(cMultiplicateur));

        // 3 premiers anneaux x multiplicateur
        return chiffre * multiplicateur;
    }

    public static double getTolerance(CouleurResistance cTolerance) {
        return CouleursAnneaux.COULEURS_ANNEAU_TOLERANCE.get(cTolerance);
    }
}
