package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.CouleurResistance;
import com.almath.resistancesihm.utils.Constantes.CouleursAnneaux;
import com.almath.resistancesihm.utils.Constantes.SuperscriptAscii;
import com.almath.resistancesihm.views.ComboBoxColorCell;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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

/**
 * The type Color select controller.
 * This controller manage the selection all things related to color selection.
 */
public class ColorSelectController implements Initializable {

    /**
     * The type Anneau element.
     * This class represent a ring
     *
     * @param <T> the type parameter
     */
    private static class AnneauElement<T> {
        /**
         * The Combo box of the ring
         */
        private ComboBox<ComboxLineData<T>> comboBox;
        /**
         * The Label of the ring.
         */
        private Label label;

        /**
         * Instantiates a new Anneau element.
         *
         * @param comboBox the combo box
         * @param label    the label
         */
        public AnneauElement(ComboBox<ComboxLineData<T>> comboBox, Label label) {
            this.comboBox = comboBox;
            this.label = label;
        }

        /**
         * Gets combo box.
         *
         * @return the combo box
         */
        public ComboBox<ComboxLineData<T>> getComboBox() { return comboBox; }

        /**
         * Gets label.
         *
         * @return the label
         */
        public Label getLabel() { return label; }
    }

    /**
     * The Resistance preview Fxml element.
     */
    @FXML
    private Parent resistancePreview;
    /**
     * The Resistance preview controller.
     */
    @FXML
    private ResistancePreviewController resistancePreviewController;

    /**
     * All comboboxes with an associated value of type Integer
     */
    @FXML
    private ComboBox<ComboxLineData<Integer>> comboxFirstColor, comboxSecondColor, comboxThirdColor, comboxMultiplier, comboxTemp;

    /**
     * The Combox tolerance, with an associated value of type Double.
     */
    @FXML
    private ComboBox<ComboxLineData<Double>> comboxTolerance;

    /**
     * The Labels displaying the values of the ring.
     */
    @FXML
    private Label lblN1, lblN2, lblN3, lblMultiplicateur, lblTolerance, lblTemp;

    /**
     * The Anneaux data.
     * This map give a label and a combobox for a given ring
     */
    private Map<Anneau, AnneauElement> anneauxData;

    /**
     * On change combox.
     * This function is called when a combobox change value
     *
     * @param <T>   the type parameter
     * @param event the event
     */
    @FXML
    private <T> void onChangeCombox(ActionEvent event) {
        // get the good combobox
        var combox = (ComboBox<ComboxLineData<T>>) event.getSource();
        // updating the preview accordingly
        resistancePreviewController.updatePreview(
                combox.getValue().getAnneau(),
                combox.getValue().getCouleurResistance());
        // Display the value in the label
        anneauxData.get(combox.getValue().getAnneau()).getLabel().setText(combox.getValue().dispValeurAssocie());
    }

    /**
     * Initialize.
     *
     * @param url            the url
     * @param resourceBundle the resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the function to be called when the user click on a preview rectangle
        resistancePreviewController.initialize(anneau -> {
            anneauxData.get(anneau).getComboBox().show();
            return null;
        });

        // Initialize the anneauData Map.
        // Initialize each combobox with the good values.
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

    /**
     * Initialiser comboxe combo box.
     * This function initialize a combobox
     *
     * @param <T>               the type parameter (Integer or Double)
     * @param combox            the combox, the combobox Object
     * @param couleursAnneau    the couleurs anneau, the colors available for this ring
     * @param dispValeurAssocie the disp valeur associe, the function to display the associated value in a good format
     * @param anneau            the anneau, the ring of this combobox
     * @param label             the label, the label to display the value
     * @param resourceBundle    the resource bundle, the ressources (contain the color names)
     * @return the combo box
     */
    private <T> ComboBox<ComboxLineData<T>> initialiserComboxe(
            ComboBox<ComboxLineData<T>> combox,
            Map<CouleurResistance, T> couleursAnneau,
            Function<T, String> dispValeurAssocie,
            Anneau anneau,
            Label label,
            ResourceBundle resourceBundle) {
        var combLines = combox.getItems();
        couleursAnneau.forEach((couleurResistance, valeurAssocie) -> {
            // foreach color, add a nex ComboxLineData
            combLines.add(new ComboxLineData<T>(
                    couleurResistance,
                    anneau,
                    dispValeurAssocie,
                    valeurAssocie,
                    resourceBundle.getString("color." + couleurResistance.name().toLowerCase())));
        });
        // sort the list by color order
        combLines.sort(Comparator.comparingInt(tComboxLineData ->
                tComboxLineData.getCouleurResistance().getOrdre()));
        // set the view for the combobox list cell and button cell
        combox.setCellFactory(comboxLineDataListView -> new ComboBoxColorCell<>(false));
        combox.setButtonCell(new ComboBoxColorCell<>(true));
        // set the combobox items
        combox.setItems(combLines);
        // set the combobox initial value
        combox.setValue(combLines.filtered(tComboxLineData ->
                tComboxLineData.getCouleurResistance() == anneau.getValeurDepart()).get(0));
        // set onHiding and onShowing to call rectangle selection
        combox.setOnHiding(event -> resistancePreviewController.unselectRectangle(anneau));
        combox.setOnShowing(event -> resistancePreviewController.selectRectangle(anneau));
        // set the label text to initial value
        label.setText(combox.getValue().dispValeurAssocie());
        // set the preview to the initial color
        resistancePreviewController.updatePreview(anneau, combox.getValue().getCouleurResistance());
        return combox;
    }

    /**
     * Gets combox value.
     *
     * @param <T>    the type parameter
     * @param anneau the anneau
     * @return the combox value
     */
    public <T> T getComboxValue(Anneau anneau) {
        return ((ComboxLineData<T>) anneauxData.get(anneau).getComboBox().getValue()).getValeurAssocie();
    }

    /**
     * Gets nom resistance.
     * Append all abreviation of the selected colors
     *
     * @param <T> the type parameter
     * @return the nom resistance
     */
    public <T> String getNomResistance() {
        return  getComboxAbrev(Anneau.N1) +
                getComboxAbrev(Anneau.N2) +
                getComboxAbrev(Anneau.N3) +
                getComboxAbrev(Anneau.MULT) +
                getComboxAbrev(Anneau.TOLER) +
                getComboxAbrev(Anneau.TEMP);
    }

    /**
     * Gets combox abrev.
     * get the abreviation for a given ring.
     *
     * @param <T>    the type parameter
     * @param anneau the anneau
     * @return the combox abrev
     */
    private <T> String getComboxAbrev(Anneau anneau) {
        return ((ComboxLineData<T>) anneauxData.get(anneau).getComboBox().getValue()).getCouleurResistance().getAbrev();
    }
}
