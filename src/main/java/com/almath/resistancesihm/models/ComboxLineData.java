package com.almath.resistancesihm.models;

import java.util.function.Function;

/**
 * The type Combox line data.
 * this class is used as a model for the Comboboxes. It represents a cell of a Combobox;
 *
 * @param <T> the type parameter, Integer or Double for the tolerance ring
 */
public class ComboxLineData<T> {
    /**
     * The Couleur resistance.
     */
    private CouleurResistance couleurResistance;
    /**
     * The Anneau.
     */
    private Anneau anneau;
    /**
     * The Disp valeur associe.
     */
    private Function<T, String> dispValeurAssocie;
    /**
     * The Valeur associe.
     */
    private T valeurAssocie;
    /**
     * The Color name.
     */
    private String colorName;

    /**
     * Instantiates a new Combox line data.
     *
     * @param couleurResistance the couleur resistance associated with this cell
     * @param anneau            the anneau (ring) of this cell
     * @param dispValeurAssocie a function used to display the associated value in a good format
     * @param valeurAssocie     associated value of this cell (Double or Integer)
     * @param colorName         the color name (used for localisation)
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

    /**
     * To string string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return String.format("%s - %s", dispCouleur(), dispValeurAssocie());
    }

    /**
     * Disp valeur associe string.
     * make a call to the dispValeurAssocie function
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
