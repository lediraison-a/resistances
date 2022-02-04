package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.CouleurResistance;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class ResistancePreviewController {

    @FXML
    private Rectangle r1, r2, r3, rMult, rToler, rTemp;

    private Map<Anneau, Rectangle> anneauxPreviewMap;

    public void initialize() {
        anneauxPreviewMap = new HashMap<>(){{
            put(Anneau.N1, r1);
            put(Anneau.N2, r2);
            put(Anneau.N3, r3);
            put(Anneau.MULT, rMult);
            put(Anneau.TOLER, rToler);
            put(Anneau.TEMP, rTemp);
        }};
    }

    public void updatePreview(Anneau anneau, CouleurResistance couleurResistance) {
        var rectangle = anneauxPreviewMap.get(anneau);
        if(couleurResistance == CouleurResistance.ABSENT) {
            rectangle.setVisible(false);
        } else {
            rectangle.setVisible(true);
            rectangle.setFill(Color.web(couleurResistance.getCouleurWeb()));
        }
    }


}
