package com.almath.resistancesihm.controllers;

import com.almath.resistancesihm.App;
import com.almath.resistancesihm.models.Anneau;
import com.almath.resistancesihm.utils.CalculResistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class PrimaryController {

    // https://github.com/joffrey-bion/javafx-themes/blob/master/css/modena_dark.css
    private static final String DARKSTYLE = "/styles/Modena_dark.css";

    @FXML
    private TextField labelCalculer;
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


}
