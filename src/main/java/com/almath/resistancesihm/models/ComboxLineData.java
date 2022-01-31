package com.almath.resistancesihm.models;

import java.util.function.Function;

public class ComboxLineData<T> {
    private CouleurResistance couleurResistance;
    private Anneau anneau;
    private Function<T, String> dispValeurAssocie;
    private T valeurAssocie;

    public ComboxLineData(CouleurResistance couleurResistance, Anneau anneau, Function<T, String> dispValeurAssocie, T valeurAssocie) {
        this.couleurResistance = couleurResistance;
        this.anneau = anneau;
        this.dispValeurAssocie = dispValeurAssocie;
        this.valeurAssocie = valeurAssocie;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", dispCouleur(), dispValeurAssocie());
    }

    public String dispValeurAssocie() {
        return valeurAssocie != null ? dispValeurAssocie.apply(valeurAssocie) : "";
    }

    public String dispCouleur() {
        return couleurResistance.name().toLowerCase();
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
