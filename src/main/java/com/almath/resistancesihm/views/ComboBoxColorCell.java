package com.almath.resistancesihm.views;

import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The type Combo box color cell.
 * This class build the cell view of the combobox
 * It calls the functions of the Combobox Model (ComboxLineData)
 * heavily inspired from http://www.java2s.com/Code/Java/JavaFX/customcellfactory.html
 *
 * @param <T> the type parameter, Integer or Double
 */
public  class ComboBoxColorCell<T> extends ListCell<ComboxLineData<T>> {

    private static final int RAYON_CERCLE_PREVIEW = 6;

    private boolean isComboxBtn;

    /**
     * Instantiates a new Combo box color cell.
     *
     * @param isComboxBtn boolean value
     *                    true when it is the current combox displayed
     */
    public ComboBoxColorCell(boolean isComboxBtn) {
        this.isComboxBtn = isComboxBtn;
    }

    /**
     * Update item.
     *
     * @param item  the item to be updated
     * @param empty is the item empty or not
     */
    @Override
    protected void updateItem(ComboxLineData<T> item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
            return;
        }
        var txt = isComboxBtn ? item.dispCouleur() : item.toString();
        if(item.getCouleurResistance() != CouleurResistance.ABSENT) {
            final Circle circle; { circle = new Circle(RAYON_CERCLE_PREVIEW); }
            circle.setFill(Color.web(item.getCouleurResistance().getCouleurWeb()));
            circle.setStroke(Color.BLACK);
            setGraphic(circle);
            setText(txt);
        } else {
            setGraphic(null);
            setText(txt);
        }
    }
}
