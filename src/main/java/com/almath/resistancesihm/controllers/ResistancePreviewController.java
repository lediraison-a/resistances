package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;

import javax.swing.event.MenuDragMouseListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * The type Resistance preview controller.
 * this controller is used to manage the resistor preview
 */
public class ResistancePreviewController {

    /**
     * The Select prev.
     */
    private Function<Anneau, Void> selectPrev;

    /**
     * The R 1.
     */
    @FXML
    private Rectangle r1, /**
     * The R 2.
     */
    r2, /**
     * The R 3.
     */
    r3, /**
     * The R mult.
     */
    rMult, /**
     * The R toler.
     */
    rToler, /**
     * The R temp.
     */
    rTemp;

    /**
     * The Anneaux preview map.
     */
    /* This map associate a Rectangle Object to a ring */
    private Map<Anneau, Rectangle> anneauxPreviewMap;

    /**
     * Initialize.
     *
     * @param selectPrev a function to call when the user click on a ring
     */
    public void initialize(Function<Anneau, Void> selectPrev) {
        this.selectPrev = selectPrev;
        anneauxPreviewMap = new HashMap<>(){{
            put(Anneau.N1, r1);
            put(Anneau.N2, r2);
            put(Anneau.N3, r3);
            put(Anneau.MULT, rMult);
            put(Anneau.TOLER, rToler);
            put(Anneau.TEMP, rTemp);
        }};

        anneauxPreviewMap.forEach((anneau, rectangle) -> {
            setRectangle(rectangle);
            unselectRectangle(anneau);
        });
    }

    /**
     * Sets rectangle.
     *
     * @param rectangle the rectangle
     */
    private void setRectangle(Rectangle rectangle) {
        rectangle.setOnMouseClicked(mouseEvent ->
                onClickPreview(mouseEvent.getSource()));
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setStrokeWidth(2);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.getStrokeDashArray().addAll(1.0, 8.0);
    }

    /**
     * Update the preview.
     *
     * @param anneau            the anneau
     * @param couleurResistance the couleur resistance
     */
    public void updatePreview(Anneau anneau, CouleurResistance couleurResistance) {
        // get the correct rectangle associated with a ring and set a proper color
        anneauxPreviewMap.get(anneau).setFill(couleurResistance == CouleurResistance.ABSENT ?
                Color.TRANSPARENT :
                Color.web(couleurResistance.getCouleurWeb()));
    }

    /**
     * when the user click on ring, we call the selectPrev function with the correct ring as parameter
     *
     * @param source the source
     */
    public void onClickPreview(Object source) {
        anneauxPreviewMap.forEach((anneau, rectangle) -> {
            if(Objects.equals(rectangle, source)) {
                selectPrev.apply(anneau);
            }
        });
    }

    /**
     * Select rectangle.
     * add a dashed stroke outline to the correct rectangle
     *
     * @param anneau the anneau
     */
    public void selectRectangle(Anneau anneau) {
        anneauxPreviewMap.get(anneau).setStroke(Color.BLACK);
    }

    /**
     * Unselect rectangle.
     * remove the dashed stroke outline of the correct rectangle
     *
     * @param anneau the anneau
     */
    public void unselectRectangle(Anneau anneau) {
        anneauxPreviewMap.get(anneau).setStroke(Color.TRANSPARENT);
    }

}
