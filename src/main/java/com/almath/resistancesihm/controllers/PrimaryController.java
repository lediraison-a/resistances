package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
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
    }

    public void initialize() {
        HashMap<String, Integer> map = new HashMap<String, Integer>() {{
            put("Nanoohm nΩ", 9);
            put("Microohm µΩ", 6);
            put("Milliohm mΩ", 3);
            put("Ohm Ω", 1);
            put("Kiloohm kΩ", -3);
            put("Mégaohm mΩ", -6);
            put("Gigaohm GΩ", -9);
        }};
        HashMap<String, Integer> sortedMap = new LinkedHashMap();
        map.entrySet().
                stream().
                sorted(Map.Entry.comparingByValue()).
                forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        System.out.println(sortedMap);

        ObservableList<String> list = FXCollections.observableArrayList(sortedMap.keySet());
        comboxConvert.setItems(list);
        comboxConvert.getSelectionModel().select(3);
    }
}
