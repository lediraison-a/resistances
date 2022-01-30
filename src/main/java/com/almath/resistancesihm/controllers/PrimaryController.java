package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.CalculResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.utils.CouleursResistanceUtils;
import com.almath.resistancesihm.views.ComboBoxColorCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class PrimaryController {

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
    private Parent resistancePreview;
    @FXML
    private ResistancePreviewController resistancePreviewController;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private <T> void onChangeCombox(ActionEvent event) {
        var combox = (ComboBox<ComboxLineData<T>>) event.getSource();
        System.out.println(combox.getValue().toString());
        resistancePreviewController.updatePreview(
                combox.getValue().getAnneau(),
                combox.getValue().getCouleurResistance());
    }

    @FXML
    private void runCalculer(ActionEvent event) {
        var n1 = comboxFirstColor.getValue().getValeurAssocie();
        var n2 = comboxSecondColor.getValue().getValeurAssocie();
        var n3 = comboxThirdColor.getValue().getValeurAssocie();
        var multiplier = comboxMultiplier.getValue().getValeurAssocie();
        var nbCouleursCalcul = CouleursResistanceUtils.getNbCouleursCalcul(comboxThirdColor.getValue().getCouleurResistance());
        var resistance = CalculResistance.getResistance(nbCouleursCalcul, n1, n2, n3, multiplier);
        var tolerance = comboxTolerance.getValue().getValeurAssocie();
        var temp = comboxTemp.getValue().getValeurAssocie();

        System.out.printf("\n resistance : %f", resistance);
        System.out.printf("\n tolerance : %f", tolerance);
    }

    public void initialize() {
        initialiserComboxes();
    }

    private void initialiserComboxes() {
        initialiserComboxe(comboxFirstColor, CouleursAnneaux.COULEURS_ANNEAU_1, Object::toString, Anneau.N1);
        initialiserComboxe(comboxSecondColor, CouleursAnneaux.COULEURS_ANNEAU_2, Object::toString, Anneau.N2);
        initialiserComboxe(comboxThirdColor, CouleursAnneaux.COULEURS_ANNEAU_3, Object::toString, Anneau.N3);
        initialiserComboxe(comboxMultiplier, CouleursAnneaux.COULEURS_ANNEAU_MULTIPLICATEUR, (v) -> String.format("10^%d", v), Anneau.MULT);
        initialiserComboxe(comboxTolerance, CouleursAnneaux.COULEURS_ANNEAU_TOLERANCE, (v) -> String.format("%.1f%%", v), Anneau.TOLER);
        initialiserComboxe(comboxTemp, CouleursAnneaux.COULEURS_ANNEAU_TEMPERATURE, (v) -> String.format("%d ppm", v), Anneau.TEMP);
    }
    private <T> void initialiserComboxe(ComboBox<ComboxLineData<T>> combox, Map<CouleurResistance, T> couleursAnneau, Function<T, String> dispValeurAssocie, Anneau anneau) {
        ObservableList<ComboxLineData<T>> combLines = combox.getItems();
        couleursAnneau.forEach((couleurResistance, valeurAssocie) -> {
            combLines.add(new ComboxLineData<T>(couleurResistance, anneau, dispValeurAssocie, valeurAssocie));
        });
        combLines.sort(Comparator.comparingInt(tComboxLineData -> tComboxLineData.getCouleurResistance().getOrdre()));
        combox.setCellFactory(comboxLineDataListView -> new ComboBoxColorCell<>());
        combox.setButtonCell(new ComboBoxColorCell<>());
        combox.setItems(combLines);
        combox.setValue(combLines.filtered(tComboxLineData -> tComboxLineData.getCouleurResistance() == anneau.getValeurDepart()).get(0));
        resistancePreviewController.updatePreview(anneau, combox.getValue().getCouleurResistance());
    }
}
