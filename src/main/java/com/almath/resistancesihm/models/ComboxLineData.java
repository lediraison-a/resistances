package com.almath.resistancesihm.models;

import java.util.function.Function;

/**
 * The type Combox line data.
 *
 * @param <T> the type parameter
 */
public class ComboxLineData<T> {
    private CouleurResistance couleurResistance;
    private Anneau anneau;
    private Function<T, String> dispValeurAssocie;
    private T valeurAssocie;
    private String colorName;

    /**
     * Instantiates a new Combox line data.
     *
     * @param couleurResistance the couleur resistance
     * @param anneau            the anneau
     * @param dispValeurAssocie the disp valeur associe
     * @param valeurAssocie     the valeur associe
     * @param colorName         the color name
     */
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

    /**
     * Disp valeur associe string.
     *
     * @return the string
     */
    public String dispValeurAssocie() {
        return valeurAssocie != null ? dispValeurAssocie.apply(valeurAssocie) : "";
    }

    /**
     * Disp couleur string.
     *
     * @return the string
     */
    public String dispCouleur() {
        return colorName;
    }

    /**
     * Gets couleur resistance.
     *
     * @return the couleur resistance
     */
    public CouleurResistance getCouleurResistance() {
        return couleurResistance;
    }

    /**
     * Gets valeur associe.
     *
     * @return the valeur associe
     */
    public T getValeurAssocie() {
        return valeurAssocie;
    }

    /**
     * Gets anneau.
     *
     * @return the anneau
     */
    public Anneau getAnneau() {
        return anneau;
    }
}
