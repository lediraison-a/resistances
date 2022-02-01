package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.views.ComboBoxColorCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ColorSelectController {

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

    public void initialize() {
        anneauxData = new HashMap<>(){{
            put(Anneau.N1, new AnneauElement(initialiserComboxe(comboxFirstColor,
                    CouleursAnneaux.COULEURS_ANNEAU_1,
                    Object::toString,
                    Anneau.N1, lblN1), lblN1));
            put(Anneau.N2, new AnneauElement(initialiserComboxe(comboxSecondColor,
                    CouleursAnneaux.COULEURS_ANNEAU_2,
                    Object::toString,
                    Anneau.N2, lblN2), lblN2));
            put(Anneau.N3, new AnneauElement(initialiserComboxe(comboxThirdColor,
                    CouleursAnneaux.COULEURS_ANNEAU_3,
                    Object::toString
                    , Anneau.N3, lblN3), lblN3));
            put(Anneau.MULT, new AnneauElement(initialiserComboxe(comboxMultiplier,
                    CouleursAnneaux.COULEURS_ANNEAU_MULTIPLICATEUR,
                    (v) -> String.format("10^%d", v),
                    Anneau.MULT, lblMultiplicateur), lblMultiplicateur));
            put(Anneau.TOLER, new AnneauElement(initialiserComboxe(comboxTolerance,
                    CouleursAnneaux.COULEURS_ANNEAU_TOLERANCE,
                    (v) -> String.format("%.1f %%", v),
                    Anneau.TOLER, lblTolerance), lblTolerance));
            put(Anneau.TEMP, new AnneauElement(initialiserComboxe(comboxTemp,
                    CouleursAnneaux.COULEURS_ANNEAU_TEMPERATURE,
                    (v) -> String.format("%d ppm", v),
                    Anneau.TEMP, lblTemp), lblTemp));
        }};
    }

    private <T> ComboBox<ComboxLineData<T>> initialiserComboxe(
            ComboBox<ComboxLineData<T>> combox,
            Map<CouleurResistance, T> couleursAnneau,
            Function<T, String> dispValeurAssocie,
            Anneau anneau,
            Label label) {
        var combLines = combox.getItems();
        couleursAnneau.forEach((couleurResistance, valeurAssocie) -> {
            combLines.add(new ComboxLineData<T>(couleurResistance, anneau, dispValeurAssocie, valeurAssocie));
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
}
