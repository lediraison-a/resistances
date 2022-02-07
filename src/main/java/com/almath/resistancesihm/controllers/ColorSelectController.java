package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.utils.Constantes.SuperscriptAscii;
import com.almath.resistancesihm.views.ComboBoxColorCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Function;

public class ColorSelectController implements Initializable {

    private static class AnneauElement<T> {
        private ComboBox<ComboxLineData<T>> comboBox;
        private Label label;

        public AnneauElement(ComboBox<ComboxLineData<T>> comboBox, Label label) {
            this.comboBox = comboBox;
            this.label = label;
        }

        public ComboBox<ComboxLineData<T>> getComboBox() { return comboBox; }

        public Label getLabel() { return label; }
    }

    @FXML
    private Parent resistancePreview;
    @FXML
    private ResistancePreviewController resistancePreviewController;

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
    private Label lblN1, lblN2, lblN3, lblMultiplicateur, lblTolerance, lblTemp;

    private Map<Anneau, AnneauElement> anneauxData;

    @FXML
    private <T> void onChangeCombox(ActionEvent event) {
        var combox = (ComboBox<ComboxLineData<T>>) event.getSource();
        System.out.println(combox.getValue().toString());
        resistancePreviewController.updatePreview(
                combox.getValue().getAnneau(),
                combox.getValue().getCouleurResistance());
        anneauxData.get(combox.getValue().getAnneau()).getLabel().setText(combox.getValue().dispValeurAssocie());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resistancePreviewController.initialize(anneau -> {
            anneauxData.get(anneau).getComboBox().show();
            return null;
        });

        anneauxData = new HashMap<>(){{
            put(Anneau.N1, new AnneauElement(initialiserComboxe(comboxFirstColor,
                    CouleursAnneaux.COULEURS_ANNEAU_1,
                    Object::toString,
                    Anneau.N1, lblN1, resourceBundle), lblN1));
            put(Anneau.N2, new AnneauElement(initialiserComboxe(comboxSecondColor,
                    CouleursAnneaux.COULEURS_ANNEAU_2,
                    Object::toString,
                    Anneau.N2, lblN2, resourceBundle), lblN2));
            put(Anneau.N3, new AnneauElement(initialiserComboxe(comboxThirdColor,
                    CouleursAnneaux.COULEURS_ANNEAU_3,
                    Object::toString
                    , Anneau.N3, lblN3, resourceBundle), lblN3));
            put(Anneau.MULT, new AnneauElement(initialiserComboxe(comboxMultiplier,
                    CouleursAnneaux.COULEURS_ANNEAU_MULTIPLICATEUR,
                    (v) -> String.format(v < 0 ? "10\u207B%s" : "10%s", SuperscriptAscii.superscriptChars.get(Math.abs(v))),
                    Anneau.MULT, lblMultiplicateur, resourceBundle), lblMultiplicateur));
            put(Anneau.TOLER, new AnneauElement(initialiserComboxe(comboxTolerance,
                    CouleursAnneaux.COULEURS_ANNEAU_TOLERANCE,
                    (v) -> String.format("%.1f %%", v),
                    Anneau.TOLER, lblTolerance, resourceBundle), lblTolerance));
            put(Anneau.TEMP, new AnneauElement(initialiserComboxe(comboxTemp,
                    CouleursAnneaux.COULEURS_ANNEAU_TEMPERATURE,
                    (v) -> String.format("%d ppm", v),
                    Anneau.TEMP, lblTemp, resourceBundle), lblTemp));
        }};
    }

    private <T> ComboBox<ComboxLineData<T>> initialiserComboxe(
            ComboBox<ComboxLineData<T>> combox,
            Map<CouleurResistance, T> couleursAnneau,
            Function<T, String> dispValeurAssocie,
            Anneau anneau,
            Label label,
            ResourceBundle resourceBundle) {
        var combLines = combox.getItems();
        couleursAnneau.forEach((couleurResistance, valeurAssocie) -> {
            combLines.add(new ComboxLineData<T>(
                    couleurResistance,
                    anneau,
                    dispValeurAssocie,
                    valeurAssocie,
                    resourceBundle.getString("color." + couleurResistance.name().toLowerCase())));
        });
        combLines.sort(Comparator.comparingInt(tComboxLineData ->
                tComboxLineData.getCouleurResistance().getOrdre()));
        combox.setCellFactory(comboxLineDataListView -> new ComboBoxColorCell<>(false));
        combox.setButtonCell(new ComboBoxColorCell<>(true));
        combox.setItems(combLines);
        combox.setValue(combLines.filtered(tComboxLineData ->
                tComboxLineData.getCouleurResistance() == anneau.getValeurDepart()).get(0));
        label.setText(combox.getValue().dispValeurAssocie());
        resistancePreviewController.updatePreview(anneau, combox.getValue().getCouleurResistance());
        return combox;
    }

    public <T> T getComboxValue(Anneau anneau) {
        return ((ComboxLineData<T>) anneauxData.get(anneau).getComboBox().getValue()).getValeurAssocie();
    }

    public <T> String getNomResistance() {
        return  getComboxAbrev(Anneau.N1) +
                getComboxAbrev(Anneau.N2) +
                getComboxAbrev(Anneau.N3) +
                getComboxAbrev(Anneau.MULT) +
                getComboxAbrev(Anneau.TOLER) +
                getComboxAbrev(Anneau.TEMP);
    }

    private <T> String getComboxAbrev(Anneau anneau) {
        return ((ComboxLineData<T>) anneauxData.get(anneau).getComboBox().getValue()).getCouleurResistance().getAbrev();
    }
}
