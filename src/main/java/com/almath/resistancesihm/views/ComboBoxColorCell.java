package com.almath.resistancesihm.views;

import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The type Combo box color cell.
 * This class build the cell view of the combobox
 * It call the functions of the Combobox Model (ComboxLineData)
 * heavily inspired from http://www.java2s.com/Code/Java/JavaFX/customcellfactory.html
 *
 * @param <T> the type parameter, Integer or Double
 */
public  class ComboBoxColorCell<T> extends ListCell<ComboxLineData<T>> {
    /**
     * The constant RAYON_CERCLE_PREVIEW.
     */
    private static final int RAYON_CERCLE_PREVIEW = 6;

    /**
     * The Is combox btn.
     */
    private boolean isComboxBtn;

    /**
     * Instantiates a new Combo box color cell.
     *
     * @param isComboxBtn the is combox btn
     */
    public ComboBoxColorCell(boolean isComboxBtn) {
        this.isComboxBtn = isComboxBtn;
    }

    /**
     * Update item.
     *
     * @param item  the item
     * @param empty the empty
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
