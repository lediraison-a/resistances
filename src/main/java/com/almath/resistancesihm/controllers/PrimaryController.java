package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.CalculResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.views.ComboBoxColorCell;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;


import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
    private TextField labelCalculer;
    @FXML
    private Label lblN1, lblN2, lblN3, lblMultiplicateur, lblTolerance, lblTemp;
    @FXML
    private Parent rootPane, resistancePreview;
    @FXML
    private ResistancePreviewController resistancePreviewController;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    private Map<Anneau, Label> anneauxLabels;

    @FXML
    private <T> void onChangeCombox(ActionEvent event) {
        var combox = (ComboBox<ComboxLineData<T>>) event.getSource();
        System.out.println(combox.getValue().toString());
        resistancePreviewController.updatePreview(
                combox.getValue().getAnneau(),
                combox.getValue().getCouleurResistance());

        anneauxLabels.get(combox.getValue().getAnneau()).setText(combox.getValue().dispValeurAssocie());
    }

    @FXML
    private void runCalculer(ActionEvent event) {
        var n1 = comboxFirstColor.getValue().getValeurAssocie();
        var n2 = comboxSecondColor.getValue().getValeurAssocie();
        var n3 = comboxThirdColor.getValue().getValeurAssocie();
        var multiplier = comboxMultiplier.getValue().getValeurAssocie();
        var resistance = CalculResistance.getResistance(n1, n2, n3, multiplier);
        var tolerance = comboxTolerance.getValue().getValeurAssocie();
        var temp = comboxTemp.getValue().getValeurAssocie();

        System.out.printf("\n resistance : %f", resistance);
        System.out.printf("\n tolerance : %f", tolerance);
        labelCalculer.setText(String.format("%.2f", resistance));
    }

    public void initialize() {
        anneauxLabels = new HashMap<>(){{
            put(Anneau.N1, lblN1);
            put(Anneau.N2, lblN2);
            put(Anneau.N3, lblN3);
            put(Anneau.MULT, lblMultiplicateur);
            put(Anneau.TOLER, lblTolerance);
            put(Anneau.TEMP, lblTemp);
        }};
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
        combox.setCellFactory(comboxLineDataListView -> new ComboBoxColorCell<>(false));
        combox.setButtonCell(new ComboBoxColorCell<>(true));
        combox.setItems(combLines);
        combox.setValue(combLines.filtered(tComboxLineData -> tComboxLineData.getCouleurResistance() == anneau.getValeurDepart()).get(0));
        anneauxLabels.get(anneau).setText(combox.getValue().dispValeurAssocie());
        resistancePreviewController.updatePreview(anneau, combox.getValue().getCouleurResistance());
    }

    @FXML
    private void switchThemeMode(ActionEvent event) {
        // https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
        var darkStyle = Objects.requireNonNull(getClass().getResource("/styles/Modena_dark.css")).toExternalForm();
        if(((CheckBox) event.getSource()).isSelected()) {
            rootPane.getStylesheets().add(darkStyle);
        } else {
            rootPane.getStylesheets().remove(darkStyle);
        }
    }
}
