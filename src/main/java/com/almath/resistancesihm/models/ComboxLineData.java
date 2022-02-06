package com.almath.resistancesihm.models;

import java.util.function.Function;

public class ComboxLineData<T> {
    private CouleurResistance couleurResistance;
    private Anneau anneau;
    private Function<T, String> dispValeurAssocie;
    private T valeurAssocie;
    private String colorName;

    public ComboxLineData(
            CouleurResistance couleurResistance,
            Anneau anneau,
            Function<T, String> dispValeurAssocie,
            T valeurAssocie,
            String colorName) {
        this.couleurResistance = couleurResistance;
        this.anneau = anneau;
        this.dispValeurAssocie = dispValeurAssocie;
        this.valeurAssocie = valeurAssocie;
        this.colorName = colorName;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", dispCouleur(), dispValeurAssocie());
    }

    public String dispValeurAssocie() {
        return valeurAssocie != null ? dispValeurAssocie.apply(valeurAssocie) : "";
    }

    public String dispCouleur() {
        return colorName;
    }

    public CouleurResistance getCouleurResistance() {
        return couleurResistance;
    }

    public T getValeurAssocie() {
        return valeurAssocie;
    }

    public Anneau getAnneau() {
        return anneau;
    }
}
