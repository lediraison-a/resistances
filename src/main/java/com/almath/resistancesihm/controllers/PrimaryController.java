package com.almath.resistancesihm.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.CalculResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.utils.CouleursResistanceUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {

    @FXML
    private ComboBox<ComboxLineData<Integer>>
            comboxFirstColor,
            comboxSecondColor,
            comboxThirdColor,
            comboxMultiplier,
            comboxTemp;
    @FXML
    private ComboBox<ComboxLineData<Double>> comboxTolerance;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void runCalculer(ActionEvent event) {
        CouleurResistance c1 = comboxFirstColor.getValue().getCouleurResistance();
        CouleurResistance c2 = comboxSecondColor.getValue().getCouleurResistance();
        CouleurResistance c3 = comboxThirdColor.getValue().getCouleurResistance();
        CouleurResistance multiplierCol = comboxMultiplier.getValue().getCouleurResistance();
        CouleurResistance toleranceCol = comboxTolerance.getValue().getCouleurResistance();
        int nbCouleursCalcul = CouleursResistanceUtils.getNbCouleursCalcul(c3);
        var resistance = CalculResistance.getResistance(nbCouleursCalcul, c1, c2, c3, multiplierCol);
        var tolerance = CalculResistance.getTolerance(toleranceCol);

        System.out.printf("\n resistance : %f", resistance);
        System.out.printf("\n tolerance : %f", tolerance);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initialiserComboxes();
    }

    private void initialiserComboxes() {
        initialiserComboxe(comboxFirstColor, CouleursAnneaux.COULEURS_ANNEAU_1, Object::toString);
        initialiserComboxe(comboxSecondColor, CouleursAnneaux.COULEURS_ANNEAU_2, Object::toString);
        initialiserComboxe(comboxThirdColor, CouleursAnneaux.COULEURS_ANNEAU_3, Object::toString);
        initialiserComboxe(comboxMultiplier, CouleursAnneaux.COULEURS_ANNEAU_MULTIPLICATEUR, (v) -> String.format("10^%d", v));
        initialiserComboxe(comboxTolerance, CouleursAnneaux.COULEURS_ANNEAU_TOLERANCE, (v) -> String.format("%.1f%%", v));
        initialiserComboxe(comboxTemp, CouleursAnneaux.COULEURS_ANNEAU_TEMPERATURE, (v) -> String.format("%d ppm", v));
    }
    private <T> void initialiserComboxe(ComboBox<ComboxLineData<T>> combox, Map<CouleurResistance, T> m, Function<T, String> getValue) {
        ObservableList<ComboxLineData<T>> combLines = combox.getItems();
        m.forEach((couleurResistance, val) -> {
            combLines.add(new ComboxLineData(couleurResistance, getValue, val));
        });
        combLines.sort(Comparator.comparingInt(tComboxLineData -> CouleursAnneaux.COULEURS_ORDRE.get(tComboxLineData.getCouleurResistance())));
        combox.setItems(combLines);
        combox.setValue(combox.getItems().get(0));
    }
}
