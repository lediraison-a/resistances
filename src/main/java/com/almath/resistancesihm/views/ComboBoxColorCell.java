package com.almath.resistancesihm.views;

import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// http://www.java2s.com/Code/Java/JavaFX/customcellfactory.html
public  class ComboBoxColorCell<T> extends ListCell<ComboxLineData<T>> {
    private static final int RAYON_CERCLE_PREVIEW = 6;

    @Override
    protected void updateItem(ComboxLineData<T> item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null || empty) {
            setGraphic(null);
            return;
        }
        if(item.getCouleurResistance() != CouleurResistance.ABSENT) {
            final Circle circle; { circle = new Circle(RAYON_CERCLE_PREVIEW); }
            circle.setFill(Color.web(item.getCouleurResistance().getCouleurWeb()));
            circle.setStroke(Color.BLACK);
            setGraphic(circle);
            setText(item.toString());
        } else {
            setGraphic(null);
            setText("absent");
        }
    }
}
