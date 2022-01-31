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
        if(valeurAssocie == null) {
            return "absent";
        }
        return String.format("%s - %s", couleurResistance.name().toLowerCase(), dispValeurAssocie.apply(valeurAssocie));
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
