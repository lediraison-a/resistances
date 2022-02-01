package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.models.ComboxLineData;
import com.almath.resistancesihm.models.ConvertData;
import com.almath.resistancesihm.utils.CalculResistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.*;

public class PrimaryController {

    // https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
    private static final String DARKSTYLE = "/styles/Modena_dark.css";

    private Double valeurOhm;

    @FXML
    private TextField labelCalculer;
    @FXML
    private ComboBox<String> comboxConvert;
    @FXML
    private Parent rootPane, colorSelect;
    @FXML
    public ColorSelectController colorSelectController;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void runCalculer(ActionEvent event) {
        var n1 = colorSelectController.<Integer>getComboxValue(Anneau.N1);
        var n2 = colorSelectController.<Integer>getComboxValue(Anneau.N2);
        var n3 = colorSelectController.<Integer>getComboxValue(Anneau.N3);
        var multiplier = colorSelectController.<Integer>getComboxValue(Anneau.MULT);
        var resistance = CalculResistance.getResistance(n1, n2, n3, multiplier);
        var tolerance = colorSelectController.<Double>getComboxValue(Anneau.TOLER);
        var temp = colorSelectController.<Integer>getComboxValue(Anneau.TEMP);

        System.out.printf("\n resistance : %f", resistance);
        System.out.printf("\n tolerance : %f", tolerance);
        labelCalculer.setText(String.format("%.2f", resistance));
        valeurOhm = resistance;
    }

    @FXML
    private void switchThemeMode(ActionEvent event) {
        var darkStyle = Objects.requireNonNull(getClass().getResource(DARKSTYLE)).toExternalForm();
        if(((CheckBox) event.getSource()).isSelected()) {
            rootPane.getStylesheets().add(darkStyle);
        } else {
            rootPane.getStylesheets().remove(darkStyle);
        }
    }

    @FXML
    public void onChangeCombox(ActionEvent actionEvent) {
        if (comboxConvert == actionEvent.getSource()) {
            ConvertData convertData = new ConvertData();
            var valeurCombox = comboxConvert.getValue(); // la valeur de la combox box après avoir été modifée (le texte)
            var valeurPuissance = convertData.getMapConvert().get(valeurCombox); // la valeur de la puissance

            var currentText = labelCalculer.getText();
            currentText = currentText.replace(',', '.');
            System.out.println(currentText);

            var newValue = valeurOhm * Math.pow(10, valeurPuissance);

            var newText = String.valueOf(newValue);
            System.out.println(newText);
            labelCalculer.setText(newText);
        }
    }

    public void initialize() {
        valeurOhm = Double.parseDouble(labelCalculer.getText());
        ConvertData convertData = new ConvertData();
        HashMap<String, Integer> map = convertData.getMapConvert();
        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        ObservableList<String> list = FXCollections.observableArrayList(sortedMap.keySet());
        comboxConvert.setItems(list);
        comboxConvert.getSelectionModel().select(3);
    }
}
