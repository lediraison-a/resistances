package com.almath.resistancesihm.views;

import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// http://www.java2s.com/Code/Java/JavaFX/customcellfactory.html
public  class ComboBoxColorCell<T> extends ListCell<ComboxLineData<T>> {
    private static final int RAYON_CERCLE_PREVIEW = 6;

    private boolean isComboxBtn;

    public ComboBoxColorCell(boolean isComboxBtn) {
        this.isComboxBtn = isComboxBtn;
    }

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
