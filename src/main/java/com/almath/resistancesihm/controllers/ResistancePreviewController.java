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

public class ResistancePreviewController {

    private Function<Anneau, Void> selectPrev;

    @FXML
    private Rectangle r1, r2, r3, rMult, rToler, rTemp;

    private Map<Anneau, Rectangle> anneauxPreviewMap;

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

    private void setRectangle(Rectangle rectangle) {
        rectangle.setOnMouseClicked(mouseEvent ->
                onClickPreview(mouseEvent.getSource()));
        rectangle.setStroke(Color.TRANSPARENT);
        rectangle.setStrokeWidth(2);
        rectangle.setStrokeType(StrokeType.OUTSIDE);
        rectangle.getStrokeDashArray().addAll(1.0, 8.0);
    }

    public void updatePreview(Anneau anneau, CouleurResistance couleurResistance) {
        anneauxPreviewMap.get(anneau).setFill(couleurResistance == CouleurResistance.ABSENT ?
                Color.TRANSPARENT :
                Color.web(couleurResistance.getCouleurWeb()));
    }
    
    public void onClickPreview(Object source) {
        anneauxPreviewMap.forEach((anneau, rectangle) -> {
            if(Objects.equals(rectangle, source)) {
                selectPrev.apply(anneau);
            }
        });
    }

    public void selectRectangle(Anneau anneau) {
        anneauxPreviewMap.get(anneau).setStroke(Color.BLACK);
    }

    public void unselectRectangle(Anneau anneau) {
        anneauxPreviewMap.get(anneau).setStroke(Color.TRANSPARENT);
    }

}
