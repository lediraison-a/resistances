package com.almath.resistancesihm.utils.Constantes;

import com.almath.resistancesihm.models.CouleurResistance;

import java.util.HashMap;
import java.util.Map;

public class CouleursAnneaux {

    public final static Map<CouleurResistance, Integer> COULEURS_ANNEAU_1 = new HashMap<>() {{
        put(CouleurResistance.NOIR, 0);
        put(CouleurResistance.MARRON, 1);
        put(CouleurResistance.ROUGE, 2);
        put(CouleurResistance.ORANGE, 3);
        put(CouleurResistance.JAUNE, 4);
        put(CouleurResistance.VERT, 5);
        put(CouleurResistance.BLEU, 6);
        put(CouleurResistance.VIOLET, 7);
        put(CouleurResistance.GRIS, 8);
        put(CouleurResistance.BLANC, 9);
    }};

    public final static Map<CouleurResistance, Integer> COULEURS_ANNEAU_2 = new HashMap<>() {{
        put(CouleurResistance.NOIR, 0);
        put(CouleurResistance.MARRON, 1);
        put(CouleurResistance.ROUGE, 2);
        put(CouleurResistance.ORANGE, 3);
        put(CouleurResistance.JAUNE, 4);
        put(CouleurResistance.VERT, 5);
        put(CouleurResistance.BLEU, 6);
        put(CouleurResistance.VIOLET, 7);
        put(CouleurResistance.GRIS, 8);
        put(CouleurResistance.BLANC, 9);
    }};

    public final static Map<CouleurResistance, Integer> COULEURS_ANNEAU_3 = new HashMap<>() {{
        put(CouleurResistance.NOIR, 0);
        put(CouleurResistance.MARRON, 1);
        put(CouleurResistance.ROUGE, 2);
        put(CouleurResistance.ORANGE, 3);
        put(CouleurResistance.JAUNE, 4);
        put(CouleurResistance.VERT, 5);
        put(CouleurResistance.BLEU, 6);
        put(CouleurResistance.VIOLET, 7);
        put(CouleurResistance.GRIS, 8);
        put(CouleurResistance.BLANC, 9);
        put(CouleurResistance.ABSENT, null);
    }};

    public final static Map<CouleurResistance, Integer> COULEURS_ANNEAU_MULTIPLICATEUR = new HashMap<>() {{
        put(CouleurResistance.NOIR, 0);
        put(CouleurResistance.MARRON, 1);
        put(CouleurResistance.ROUGE, 2);
        put(CouleurResistance.ORANGE, 3);
        put(CouleurResistance.JAUNE, 4);
        put(CouleurResistance.VERT, 5);
        put(CouleurResistance.BLEU, 6);
        put(CouleurResistance.VIOLET, 7);
        put(CouleurResistance.GRIS, 8);
        put(CouleurResistance.BLANC, 9);
        put(CouleurResistance.OR, -1);
        put(CouleurResistance.ARGENT, -2);
    }};

    public final static Map<CouleurResistance, Double> COULEURS_ANNEAU_TOLERANCE = new HashMap<>() {{
        put(CouleurResistance.NOIR, 20.0);
        put(CouleurResistance.MARRON, 1.0);
        put(CouleurResistance.ROUGE, 2.0);
        put(CouleurResistance.VERT, 0.5);
        put(CouleurResistance.BLEU, 0.25);
        put(CouleurResistance.VIOLET, 0.1);
        put(CouleurResistance.GRIS, 0.05);
        put(CouleurResistance.OR, 5.0);
        put(CouleurResistance.ARGENT, 10.0);
        put(CouleurResistance.ABSENT, 20.0);
    }};

    public final static Map<CouleurResistance, Integer> COULEURS_ANNEAU_TEMPERATURE = new HashMap<>() {{
        put(CouleurResistance.NOIR, 200);
        put(CouleurResistance.MARRON, 100);
        put(CouleurResistance.ROUGE, 50);
        put(CouleurResistance.ORANGE, 25);
        put(CouleurResistance.JAUNE, 15);
        put(CouleurResistance.ABSENT, null);
    }};

}