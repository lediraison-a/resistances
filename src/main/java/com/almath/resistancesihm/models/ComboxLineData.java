package com.almath.resistancesihm.models;

import java.util.function.Function;

public class ComboxLineData<T> {
    private CouleurResistance couleurResistance;
    private Function<T, String> toStringFunc;
    private T value;

    public ComboxLineData(CouleurResistance couleurResistance, Function<T, String> toStringFunc, T value) {
        this.couleurResistance = couleurResistance;
        this.toStringFunc = toStringFunc;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", couleurResistance.name().toLowerCase(), toStringFunc.apply(value));
    }

    public CouleurResistance getCouleurResistance() {
        return couleurResistance;
    }
}
