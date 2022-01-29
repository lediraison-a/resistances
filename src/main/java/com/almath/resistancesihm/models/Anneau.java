package com.almath.resistancesihm.models;

public enum Anneau {
    N1(CouleurResistance.MARRON),
    N2(CouleurResistance.VERT),
    N3(CouleurResistance.ROUGE),
    MULT(CouleurResistance.OR),
    TOLER(CouleurResistance.ABSENT),
    TEMP(CouleurResistance.ABSENT);

    private CouleurResistance valeurDepart;

    Anneau(CouleurResistance valeurDepart) {
        this.valeurDepart = valeurDepart;
    }

    public CouleurResistance getValeurDepart() {
        return valeurDepart;
    }
}
